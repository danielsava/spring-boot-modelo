package bpe.rest;


import bpe.entity.Funcionalidade;
import bpe.exception.ResourceNotFoundException;
import bpe.repository.FuncionalidadeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;




/**
 *
 *  Exemplos de anotações segurança para utilizar nos métodos
 *   - @PreAuthorize("hasRole('USER_READ_PRIVILEGE')")
 *   - @PreAuthorize("isAuthenticated()")
 *
 *
 * Exemplos de Anotações Rest
 *  - @GetMapping("tasks")
 *  - @GetMapping(value = "/stream/tasks/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
 *  - @RequestMapping(value = "/password", method = RequestMethod.PUT)
 *
 *
 */



@RestController
@RequestMapping("/funcionalidades")
public class FuncionalidadeRest {


    @Autowired
    private FuncionalidadeDao dao;

    @GetMapping
    public List<Funcionalidade> findAll() {
        return dao.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Funcionalidade> findById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Funcionalidade e = dao.findById(id).orElseThrow(() -> new ResourceNotFoundException("findById : entidade não encontrada para o 'id' " + id));
        return ResponseEntity.ok().body(e);
    }

    @PostMapping
    public ResponseEntity<Funcionalidade> create(@Valid @RequestBody Funcionalidade e) {
        return ResponseEntity.ok(dao.save(e));
    }

    @PutMapping
    public ResponseEntity<Funcionalidade> update(@Valid @RequestBody Funcionalidade e) throws ResourceNotFoundException {
        if(e.id == null)
            throw new ResourceNotFoundException("Update : id não pode ser null");
        if(!dao.existsById(e.id))
            throw new ResourceNotFoundException("Update : Entidade com 'id' " + e.id + " não existe" );
        return ResponseEntity.ok(dao.save(e));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        if(!dao.existsById(id))
            throw new ResourceNotFoundException("Delete: Entidade 'id' " + id + " não existe");
        dao.deleteById(id);
    }

}
