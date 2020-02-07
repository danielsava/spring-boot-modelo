package bpe.auth.repository;

import bpe.auth.entity.ContaUsuario;
import bpe.base.SpringJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaUsuarioDao extends SpringJpaRepository<ContaUsuario> {

    ContaUsuario findByLogin(String login);

    void deleteByLogin(String login);

}
