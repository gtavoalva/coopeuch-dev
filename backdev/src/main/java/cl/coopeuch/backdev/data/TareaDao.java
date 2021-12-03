package cl.coopeuch.backdev.data;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.coopeuch.backdev.model.Tarea;

@Repository
public interface TareaDao extends JpaRepository<Tarea, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE #{#entityName} t SET t.identificador = ?1, t.descripcion = ?2, t.fechaCreacion = ?3, t.vigente = ?4 WHERE identificador = ?5")
    void editarTarea(Long identificador, String descripcion, Date fechaCreacion, Boolean vigente, Long idTarea);
    
}
