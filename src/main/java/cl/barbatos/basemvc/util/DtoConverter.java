package cl.barbatos.basemvc.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {
    public static <D, T> D convertToDto(T entity, Class<D> dtoClass) {
        D dto = BeanUtils.instantiateClass(dtoClass);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static <T, D> T convertToEntity(D dto, Class<T> entityClass) {
        T entity = BeanUtils.instantiateClass(entityClass);
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
