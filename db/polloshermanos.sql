CREATE DATABASE IF NOT EXISTS polloshermanos;
USE polloshermanos;

CREATE USER 'app'@'localhost';

GRANT ALL PRIVILEGES
ON polloshermanos.* 
TO 'app'@'localhost' 
IDENTIFIED BY 'admin123';

FLUSH PRIVILEGES;


#Tabla tal cual la pide el enunciado
CREATE TABLE mueble(
    ID_mueble INT PRIMARY KEY,
    nombre_mueble VARCHAR(30),
    tipo VARCHAR(30),
    precio_base INT,
    stock INT,
    estado BOOLEAN,
    tamano VARCHAR(30),
    material VARCHAR(30)
);

CREATE TABLE variante(
    ID_variante INT PRIMARY KEY,
    nombre_variante VARCHAR(30),
    descripcion VARCHAR(90),
    precio_extra INT
);  

CREATE TABLE item(
    ID_item INT PRIMARY KEY,
    ID_mueble INT NOT NULL,
    ID_cotizacion INT,
    precio INT
);


CREATE TABLE conexion_item_variante(
    ID_conexion_item_variante INT PRIMARY KEY,
    ID_item INT NOT NULL,
    ID_variante INT NOT NULL
);

CREATE TABLE cotizacion(
    ID_cotizacion INT PRIMARY KEY,
    total INT
);

CREATE TABLE venta(
    ID_venta INT PRIMARY KEY,
    ID_cotizacion INT NOT NULL
);


ALTER TABLE item
    ADD FOREIGN KEY (ID_mueble) REFERENCES mueble(ID_mueble),
    ADD FOREIGN KEY (ID_cotizacion) REFERENCES cotizacion(ID_cotizacion);

ALTER TABLE conexion_item_variante
    ADD FOREIGN KEY (ID_item) REFERENCES item(ID_item),
    ADD FOREIGN KEY (ID_variante) REFERENCES variante(ID_variante);

ALTER TABLE venta
    ADD FOREIGN KEY (ID_cotizacion) REFERENCES cotizacion(ID_cotizacion);