DROP TABLE IF EXISTS coo_tarea CASCADE;

CREATE TABLE coo_tarea
(
   identificador   bigint         NOT NULL,
   descripcion     varchar(255),
   fecha_creacion  timestamp,
   vigente         boolean
);

ALTER TABLE coo_tarea
   ADD CONSTRAINT coo_tarea_pkey
   PRIMARY KEY (identificador);

COMMIT;
