-- Crear la base de datos UNY
CREATE DATABASE UNY;
USE UNY;

-- Crear la tabla ALUMNO
CREATE TABLE ALUMNO (
    ID INT(5) NOT NULL AUTO_INCREMENT,
    Nombre VARCHAR(30) NOT NULL,
    Apellido VARCHAR(35) NOT NULL,
    Dirección VARCHAR(75) NOT NULL,
    Email VARCHAR(45) DEFAULT NULL,
    Fecha_Nacimiento DATE,
    Estado VARCHAR(70) NOT NULL,
    Municipio VARCHAR(15) DEFAULT NULL,
    PRIMARY KEY (ID)
);

-- Crear la tabla HISTORICO_ALUMNO
CREATE TABLE HISTORICO_ALUMNO (
    IDALUM INT(5) NOT NULL,
    Nombre VARCHAR(30) NOT NULL,
    Apellido VARCHAR(35) NOT NULL,
    Estado VARCHAR(70) NOT NULL,
    Usuario_Activo VARCHAR(40),
    Fecha_Ingreso DATE,
    Hora_Registro TIME,
    PRIMARY KEY (IDALUM)
);

-- Crear la tabla HISTORICO_OPERACIONES
CREATE TABLE HISTORICO_OPERACIONES (
    ID_BORRADO INT(5) NOT NULL,
    Nombre_BORRADO VARCHAR(30) NOT NULL,
    Apellido_BORRADO VARCHAR(35) NOT NULL,
    Usuario_Activo VARCHAR(40),
    Fecha_Borrado DATE,
    Hora_Borrado TIME,
    PRIMARY KEY (ID_BORRADO)
);

-- Crear el disparador para AFTER INSERT en la tabla ALUMNO
DELIMITER //
CREATE TRIGGER after_insert_alumno
AFTER INSERT ON ALUMNO
FOR EACH ROW
BEGIN
    INSERT INTO HISTORICO_ALUMNO (IDALUM, Nombre, Apellido, Estado, Usuario_Activo, Fecha_Ingreso, Hora_Registro)
    VALUES (NEW.ID, NEW.Nombre, NEW.Apellido, NEW.Estado, CURRENT_USER(), CURDATE(), CURTIME());
END;
//
DELIMITER ;

-- Crear el procedimiento almacenado ingresarAlumno
DELIMITER //
CREATE PROCEDURE ingresarAlumno(
    IN p_ID INT,
    IN p_nombre VARCHAR(30),
    IN p_apellido VARCHAR(35),
    IN p_dirección VARCHAR(75),
    IN p_email VARCHAR(45),
    IN p_fecha_nacimiento DATE,
    IN p_estado VARCHAR(70),
    IN p_municipio VARCHAR(15)
)
BEGIN
    INSERT INTO ALUMNO (ID, Nombre, Apellido, Dirección, Email, Fecha_Nacimiento, Estado, Municipio)
    VALUES (p_ID, p_nombre, p_apellido, p_dirección, p_email, p_fecha_nacimiento, p_estado, p_municipio);
END;
//
DELIMITER ;

-- Insertar 10 registros en la tabla ALUMNO usando el procedimiento almacenado
CALL ingresarAlumno(NULL, 'Juan', 'Perez', 'Calle 10 entre carreras 28 y 27', 'juan.perez@example.com', '1990-01-01', 'Lara', 'Iribarren');
CALL ingresarAlumno(NULL, 'Maria', 'Lopez', 'Calle Urdaneta', 'maria.lopez@example.com', '1991-02-01', 'Zulia', 'Maracaibo');
CALL ingresarAlumno(NULL, 'Carlos', 'Gomez', 'Calle Miranda', 'carlos.gomez@example.com', '1992-03-01', 'Mérida', 'Libertador');
CALL ingresarAlumno(NULL, 'Ana', 'Martinez', 'Calle Sucre', 'ana.martinez@example.com', '1993-04-01', 'Táchira', 'San Cristóbal');
CALL ingresarAlumno(NULL, 'Luis', 'Hernandez', 'Calle Páez', 'luis.hernandez@example.com', '1994-05-01', 'Aragua', 'Girardot');
CALL ingresarAlumno(NULL, 'Jose', 'Garcia', 'Calle Carabobo', 'jose.garcia@example.com', '1995-06-01', 'Miranda', 'Chacao');
CALL ingresarAlumno(NULL, 'Laura', 'Fernandez', 'Calle Boyacá', 'laura.fernandez@example.com', '1996-07-01', 'Anzoátegui', 'Simón Bolívar');
CALL ingresarAlumno(NULL, 'Pedro', 'Diaz', 'Calle Falcón', 'pedro.diaz@example.com', '1997-08-01', 'Lara', 'Iribarren');
CALL ingresarAlumno(NULL, 'Sofia', 'Ramirez', 'Calle Piar', 'sofia.ramirez@example.com', '1998-09-01', 'Monagas', 'Maturín');
CALL ingresarAlumno(NULL, 'David', 'Sanchez', 'Calle Zamora', 'david.sanchez@example.com', '1999-10-01', 'Barinas', 'Barinas');

-- Visualizar los registros insertados en la tabla ALUMNO
SELECT * FROM ALUMNO;
SELECT * FROM HISTORICO_ALUMNO;

-- Crear el disparador para BEFORE DELETE en la tabla ALUMNO
DELIMITER //
CREATE TRIGGER before_delete_alumno
BEFORE DELETE ON ALUMNO
FOR EACH ROW
BEGIN
    INSERT INTO HISTORICO_OPERACIONES (ID_BORRADO, Nombre_BORRADO, Apellido_BORRADO, Usuario_Activo, Fecha_Borrado, Hora_Borrado)
    VALUES (OLD.ID, OLD.Nombre, OLD.Apellido, CURRENT_USER(), CURDATE(), CURTIME());
END;
//
DELIMITER ;

-- Eliminar 3 registros de la tabla ALUMNO
DELETE FROM ALUMNO WHERE ID = 1;
DELETE FROM ALUMNO WHERE ID = 2;
DELETE FROM ALUMNO WHERE ID = 3;

-- Visualizar los registros en las tablas después de la eliminación
SELECT * FROM ALUMNO;
SELECT * FROM HISTORICO_OPERACIONES;

-- Crear el procedimiento almacenado BuscarAlumno
DELIMITER //
CREATE PROCEDURE BuscarAlumno(
    IN p_ID INT,
    OUT p_nombre VARCHAR(30),
    OUT p_apellido VARCHAR(35),
    OUT p_fecha_nacimiento DATE
)
BEGIN
    SELECT Nombre, Apellido, Fecha_Nacimiento
    INTO p_nombre, p_apellido, p_fecha_nacimiento
    FROM ALUMNO
    WHERE ID = p_ID;
END;
//
DELIMITER ;

-- Buscar el registro con ID número 5
CALL BuscarAlumno(5, @nombre, @apellido, @fecha_nacimiento);

-- Visualizar el resultado de la búsqueda
SELECT @nombre AS Nombre, @apellido AS Apellido, @fecha_nacimiento AS Fecha_Nacimiento;

-- Crear una vista para visualizar un reporte de todos los estudiantes
CREATE VIEW reporte_estudiantes AS
SELECT ID, Nombre, Apellido, Dirección, Email, Fecha_Nacimiento, Estado, Municipio
FROM ALUMNO;

-- Visualizar la vista de reporte de estudiantes
SELECT * FROM reporte_estudiantes;

