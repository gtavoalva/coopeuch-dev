package cl.coopeuch.backdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.coopeuch.backdev.model.Tarea;
import cl.coopeuch.backdev.service.TareaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/tarea")
public class TareaController {

    @Autowired
    private TareaService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Tarea>> listar() {
        log.info("listar");
        List<Tarea> listado = null;
        try {
            listado = service.listar();
        } catch (RuntimeException rException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(listado);
        }

        log.info("listar ok: " + listado.size());
        return ResponseEntity.ok(listado);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> agregar(@RequestBody Tarea tarea) {
        log.info("agregar");
        try {
            if (tarea != null && tarea.getIdentificador() != null && tarea.getIdentificador().intValue() > 0
                    && tarea.getDescripcion().trim().length() > 0 && tarea.getFechaCreacion() != null
                    && tarea.getVigente() != null) {
                service.agregar(tarea);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Campos Identificador, descripción, fecha creación y vigente, son obligatorios");
            }
        } catch (RuntimeException rException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(rException.getMessage());
        }

        log.info("agregar ok");
        return ResponseEntity.ok("Tarea creada exitosamente");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> editar(@RequestBody Tarea tarea, @PathVariable("id") Long idTarea) {
        log.info("editar");
        try {
            if (idTarea == null || idTarea <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe indicar el ID de la tarea a modificar");
            }
            if (tarea != null && tarea.getDescripcion().trim().length() > 0 && tarea.getFechaCreacion() != null
                    && tarea.getVigente() != null) {
                service.editar(tarea, idTarea);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Campos descripción, fecha creación y vigente, son obligatorios");
            }
        } catch (RuntimeException rException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rException.getMessage());
        }

        log.info("editar ok");
        return ResponseEntity.ok("Tarea editada exitosamente");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remover(@PathVariable("id") Long idTarea) {
        log.info("remover");

        if (idTarea == null || idTarea <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe indicar el ID de la tarea a modificar");
        }

        try {
            service.remover(idTarea);
        } catch (RuntimeException rException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rException.getMessage());
        }

        log.info("remover ok");
        return ResponseEntity.ok("Tarea eliminada exitosamente");
    }
}
