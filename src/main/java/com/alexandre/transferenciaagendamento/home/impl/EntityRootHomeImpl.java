package com.alexandre.transferenciaagendamento.home.impl;

import com.alexandre.transferenciaagendamento.SpringContextHolder;
import com.alexandre.transferenciaagendamento.home.EntityRootHome;
import com.alexandre.transferenciaagendamento.model.bean.EntityRootBean;
import com.alexandre.transferenciaagendamento.model.entity.EntityRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(propagation = Propagation.MANDATORY)
public abstract class EntityRootHomeImpl <E extends EntityRoot, B extends EntityRootBean, R extends JpaRepository<E, Long>> implements
        EntityRootHome<E, B> {

    @Autowired
    protected ApplicationContext applicationContext;

    protected R repository;

    EntityRootHomeImpl(){
        Class<?>[] typeArgs = GenericTypeResolver.resolveTypeArguments(getClass(), EntityRootHomeImpl.class);
        Class<R> repositoryClazz = (Class<R>) typeArgs[2]; // E=0, B=1, R=2
        this.repository = SpringContextHolder.getBean(repositoryClazz);
    }

    public List<B> entitiesToBeans(Collection<E> entities){
        return entities.stream()
                .map(this::entityToBean)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<B> findAllBeans(){
        return entitiesToBeans(findAll());
    }

    public List<E> findAll(){
        return repository.findAll();
    }

    public void save(E entity) {
        repository.save(entity);
    }

    public B entityToSimpleBean(E entity, B bean){
        bean.setId(entity.getId());
        return bean;

    }



}
