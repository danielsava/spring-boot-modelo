package bpe.base;

import bpe.base.BaseEntity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class BaseDto<E extends BaseEntity> implements Serializable {


    public Long id;

    public Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public Class<E> getClasseEntidade() {
        return (Class<E>) ( (ParameterizedType) this.getClass().getGenericSuperclass() ).getActualTypeArguments()[0];
    }

}
