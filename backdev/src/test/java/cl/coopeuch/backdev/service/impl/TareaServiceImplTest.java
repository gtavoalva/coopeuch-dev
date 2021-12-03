package cl.coopeuch.backdev.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cl.coopeuch.backdev.data.TareaDao;
import cl.coopeuch.backdev.model.Tarea;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TareaServiceImplTest {

    @Autowired
    private TareaDao repository;

    @Test
    void testAgregar() {
        Tarea t = new Tarea();
        t.setIdentificador(1234L);
        t.setDescripcion("Tarea 1");
        t.setFechaCreacion(new Date());
        t.setVigente(true);

        Tarea tsaved = null;

        if (repository.existsById(t.getIdentificador())) {
            throw new RuntimeException("Tarea con el identificador " + t.getIdentificador() + ", ya existe");
        } else {
            tsaved = repository.save(t);
        }

        assertNotNull(tsaved);
    }

    @Test
    void testAgregarRepetido() {

        assertThrows(RuntimeException.class, () -> {
            Tarea t1 = new Tarea();
            t1.setIdentificador(1234L);
            t1.setDescripcion("Tarea 1");
            t1.setFechaCreacion(new Date());
            t1.setVigente(true);

            repository.save(t1);

            Tarea t2 = new Tarea();
            t2.setIdentificador(1234L);

            if (repository.existsById(t2.getIdentificador())) {
                throw new RuntimeException("Tarea con el identificador " + t2.getIdentificador() + ", ya existe");
            } else {
                repository.save(t2);
            }
        });
    }

    @Test
    void testEditar() {
        long idTarea = 1234L;

        Tarea t1 = new Tarea();
        t1.setIdentificador(idTarea);
        t1.setDescripcion("Tarea 1");
        t1.setVigente(true);

        repository.save(t1);

        long idTarea2 = 12L;

        Tarea t2 = new Tarea();
        t2.setIdentificador(idTarea2);
        t2.setDescripcion("Tarea 2");
        t2.setVigente(true);

        Optional<Tarea> tDb = repository.findById(idTarea);
        if (tDb.isPresent()) {
            repository.editarTarea(t2.getIdentificador(), t2.getDescripcion(), t2.getFechaCreacion(), t2.getVigente(),
                    idTarea);
        } else {
            throw new RuntimeException("Tarea no encontrada");
        }

        Optional<Tarea> tMod = repository.findById(idTarea2);

        assertEquals(t2.toString(), tMod.get().toString());
    }

    @Test
    void testEditarNoEncontrado() {
        assertThrows(RuntimeException.class, () -> {
            long idTarea = 1234L;

            Tarea t2 = new Tarea();
            t2.setIdentificador(12L);
            t2.setDescripcion("Tarea 2");
            t2.setVigente(true);

            Optional<Tarea> tDb = repository.findById(idTarea);
            if (tDb.isPresent()) {
                repository.editarTarea(t2.getIdentificador(), t2.getDescripcion(), t2.getFechaCreacion(),
                        t2.getVigente(), idTarea);
            } else {
                throw new RuntimeException("Tarea no encontrada");
            }
        });
    }

    @Test
    void testListar() {
        List<Tarea> listado = repository.findAll();

        assertNotNull(listado);
    }

    @Test
    void testRemover() {
        long idTarea = 1234L;

        Tarea t1 = new Tarea();
        t1.setIdentificador(idTarea);
        t1.setDescripcion("Tarea 1");
        t1.setFechaCreacion(new Date());
        t1.setVigente(true);

        repository.save(t1);

        Optional<Tarea> tDb = repository.findById(idTarea);
        if (tDb.isPresent()) {
            Tarea tarea = tDb.get();
            tarea.setVigente(false);

            repository.save(tarea);
        } else {
            throw new RuntimeException("Tarea no encontrada");
        }

        assertFalse(repository.findById(idTarea).get().getVigente());
    }

    @Test
    void testRemoverNoEncontrado() {

        assertThrows(RuntimeException.class, () -> {
            long idTarea = 1234L;
            Optional<Tarea> tDb = repository.findById(idTarea);
            if (tDb.isPresent()) {
                Tarea tarea = tDb.get();
                tarea.setVigente(false);

                repository.save(tarea);
            } else {
                throw new RuntimeException("Tarea no encontrada");
            }
        });
    }
}
