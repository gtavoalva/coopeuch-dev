package cl.coopeuch.backdev.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

import cl.coopeuch.backdev.common.view.View;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "COO_TAREA")
@Getter @Setter @ToString
public class Tarea {

    @Id
    @Column(name = "IDENTIFICADOR", nullable = false)
    @JsonView(View.Simple.class)
    private Long identificador;
    
    @Column(name = "DESCRIPCION")
    @JsonView(View.Simple.class)
    private String descripcion;
    
    @Column(name = "FECHA_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
    @JsonView(View.Simple.class)
    private Date fechaCreacion;

    @Column(name = "VIGENTE")
    @JsonView(View.Simple.class)
    private Boolean vigente;
}
