package com.alexandre.transferenciaagendamento.home;

import com.alexandre.transferenciaagendamento.model.bean.EntityRootBean;
import com.alexandre.transferenciaagendamento.model.entity.EntityRoot;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;


@Component
public interface EntityRootHome <E extends EntityRoot, B extends EntityRootBean>  {

    B entityToBean(E entity);
    List<B> entitiesToBeans(Collection<E> entities);
    B entityToSimpleBean(E entity, B bean);

    List<E> findAll();
    List<B> findAllBeans();

    void save(E entity);
}
