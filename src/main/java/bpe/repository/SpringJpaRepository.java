package bpe.repository;

import bpe.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringJpaRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

}
