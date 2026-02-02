package dev.lxzx.service;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseService<T, ID extends Serializable> {
    @Autowired
    JpaRepository<T, ID> repository;
    JpaSpecificationExecutor<T> executor;

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;
    protected JPAQueryFactory queryFactory;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
        executor = (JpaSpecificationExecutor) repository;
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public T update(T entity) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object id = PropertyUtils.getProperty(entity, "id");
        if (id != null) {
            return repository.save(entity);
        } else {
            log.error("No ID attached for entity");
            throw new IllegalAccessException();
        }
    }

    public T queryById(ID id) {
        return repository.findById(id).orElse(null);
    }

    protected static void recurseDir(String path) throws IOException {
        Path realPath = Path.of(path).toAbsolutePath();
        // Will this work on Windows?
        Path toCreate = Path.of("/");
        Iterator<Path> iterator = realPath.iterator();
        while (iterator.hasNext()) {
            // Path.resolve() might be platform dependent here?
            toCreate = toCreate.resolve(iterator.next());
            if (!Files.isDirectory(toCreate)) {
                log.info("Creating dir " + toCreate.toString());
                Files.createDirectory(toCreate);
            }
        }
    }
}
