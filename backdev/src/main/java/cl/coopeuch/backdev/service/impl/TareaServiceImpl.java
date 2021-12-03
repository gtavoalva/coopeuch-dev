package cl.coopeuch.backdev.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.coopeuch.backdev.data.TareaDao;
import cl.coopeuch.backdev.model.Tarea;
import cl.coopeuch.backdev.service.TareaService;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaDao repository;

    @Override
    public List<Tarea> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(rollbackOn = { RuntimeException.class, Exception.class })
    public void agregar(Tarea tarea) {
        if (repository.existsById(tarea.getIdentificador())) {
            throw new RuntimeException("Tarea con el identificador " + tarea.getIdentificador() + ", ya existe");
        } else {
            repository.save(tarea);
        }
    }

    @Override
    @Transactional(rollbackOn = { RuntimeException.class, Exception.class })
    public void editar(Tarea tarea, Long idTarea) {
        Optional<Tarea> tDb = repository.findById(idTarea);
        if (tDb.isPresent()) {
            repository.editarTarea(tarea.getIdentificador(), tarea.getDescripcion(), tarea.getFechaCreacion(), tarea.getVigente(), idTarea);
        } else {
            throw new RuntimeException("Tarea no encontrada");
        }
    }

    @Override
    @Transactional(rollbackOn = { RuntimeException.class, Exception.class })
    public void remover(Long idTarea) {
        Optional<Tarea> tDb = repository.findById(idTarea);
        if (tDb.isPresent()) {
            Tarea tarea = tDb.get();
            tarea.setVigente(false);

            repository.save(tarea);
        } else {
            throw new RuntimeException("Tarea no encontrada");
        }
    }
}
