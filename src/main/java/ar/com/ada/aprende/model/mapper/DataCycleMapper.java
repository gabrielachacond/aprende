package ar.com.ada.aprende.model.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

/**
 *
 * @param <D> Respresent DTO class
 * @param <E> Respresent Entity class
 */
public interface DataCycleMapper<D, E> {
    E toEntity(D dto, @Context CycleAvoidingMappingContext context);

    List<E> toEntity(List<D> dtoList, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    D toDto(E entity, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    List<D> toDto(List<E> entityList, @Context CycleAvoidingMappingContext context);
}
