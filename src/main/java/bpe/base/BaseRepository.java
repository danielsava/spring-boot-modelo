package bpe.base;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public abstract class BaseRepository {


    @PersistenceContext
    private EntityManager entityManager;


}
