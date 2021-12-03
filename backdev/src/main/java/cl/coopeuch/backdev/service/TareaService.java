package cl.coopeuch.backdev.service;

import java.util.List;

import cl.coopeuch.backdev.model.Tarea;

public interface TareaService {

    List<Tarea> listar();
    void agregar(Tarea tarea);
    void editar(Tarea tarea, Long idTarea);
    void remover(Long idTarea);
}
