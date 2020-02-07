package bpe.repository;

import bpe.base.SpringJpaRepository;
import bpe.entity.Funcionalidade;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionalidadeDao extends SpringJpaRepository<Funcionalidade> { // CrudRepository<Funcionalidade, Long> {


    Funcionalidade findById(long id);


    // SQL Nativo 1
    //@Query(value = "select p.* from pessoa p where p.nome = ?1", nativeQuery = true)
    //Pessoa buscarPessoaPorNomeExato(String nomeCompleto);

    // SQL Nativo 2
    //@Query(value = "select p.* from pessoa p where p.data_de_nascimento between ?1 and ?2", nativeQuery = true)
    //List<Pessoa> buscarPessoasComDataDeNascimentoNoIntervalo(LocalDate de, LocalDate ate);

}
