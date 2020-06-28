package ar.com.ada.aprende.component.security;


import ar.com.ada.aprende.model.dto.security.JwtUserDetails;
import ar.com.ada.aprende.model.entity.security.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("jwtAuthProvider")
public class JwtAuthProvider {

    @Value("${auth.jwt.secret.seed}")
    private String secretSeed;

    @Value("${auth.jwt.expiration.time}")
    private Integer expirationTime;

    @Value("${auth.jwt.type}")
    private String authJwtType;

    public static final String CLAIM_KEY_USERNAME = "sud";
    public static final String CLAIM_KEY_CREATED = "create_at";

    public JwtUserDetails createJwtUserDetails(User user) {
        Set<SimpleGrantedAuthority> authoritySet = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toSet());

        return new JwtUserDetails(
                user.getId(),
                user.getUsername(), user.getPassword(),
                user.getFirstName(), user.getLastName(), user.getEmail(),
                authoritySet, user.getEnabled()
        );
    }

    public String doGenerateToken(UserDetails userDetails) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) userDetails;

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, jwtUserDetails.getUsername());
        claims.put("first_name", jwtUserDetails.getFirstName());
        claims.put("last_name", jwtUserDetails.getLastName());
        claims.put("roles", jwtUserDetails.getAuthorities());
        claims.put(CLAIM_KEY_CREATED, new Date());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(jwtUserDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secretSeed)
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expirationTime);
    }

    public boolean isBearer(String authorization) {
        return authorization != null &&
                authorization.startsWith(authJwtType) &&
                authorization.split("\\.").length == 3;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUserDetails user = (JwtUserDetails) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            token = token.trim().replace(authJwtType, "");
            username = getClaimsOfToken(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        Date expirationDate;
        try {
            token = token.trim().replace(authJwtType, "");
            expirationDate = getClaimsOfToken(token).getExpiration();
        } catch (Exception e) {
            expirationDate = null;
        }
        return expirationDate;
    }

    public Claims getClaimsOfToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims;
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretSeed)
                .parseClaimsJws(token)
                .getBody();
    }
}
