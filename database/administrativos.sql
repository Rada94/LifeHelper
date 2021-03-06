Create DATABASE if not exists LifeHelper;	/*Nombre de la base de datos*/
use LifeHelper;	/*instruccion para entrar a la base de datos*/
Create Table if not exists administrativos(	/*crear tabla dentro de la base de datos si no existe con el nombre de "administrativo"*/
id_adm int auto_increment not null UNIQUE,     /*referencia para asignar de forma automatica un id a el registro del administrativo es de tipo int y es unico, sera refen=renciado como primary key*/
nombre_adm varchar(150) NOT NULL,	 /*referencia para asignar los nombres de los administrativo, es de tipo text y no puede estar vacio*/
apellidos_adm varchar(150) NOT NULL,     /*referencia para asignar el apellido paterno de los administrativo, es de tipo text y no puede estar vacio*/
puesto_adm varchar(200) NOT NULL, /*referencia para asignar el puesto del administrativo (secretaria, director, etc), es de tipo text, puede ser modificado y es not null*/
sexo_adm varchar(12) NOT NULL,	/*referencia para asignar el sexo de los administrativo, es de tipo varchar,no puede estar vacio(femenino, masculino) y no puede ser modificado*/
domicilio_adm varchar(300) NOT NULL,	/*referencia para asignar el domicilio(calle, numero) de los administrativo, es de tipo text,no puede estar vacio y puede ser modificado*/
telefono_adm varchar(20) NOT NULL, /*referencia para asignar telefono principal de contacto con el administrativo, es de tipo varchar, no puede estar vacio y puede ser modificado*/
email_adm varchar(255) NOT NULL unique, /*referencia para asignar e-mail para administrativo, es de tipo text, no puede estar vacio y puede ser modificado*/
pass_adm varchar(8) NOT NULL, /*contraseña del administrativo*/
PRIMARY KEY(id_adm)
)ENGINE=InnoDB;


DELIMITER //
CREATE TRIGGER ins_adm_login AFTER INSERT ON administrativos FOR EACH ROW
BEGIN
	INSERT INTO login (ids, perfiles, emails, passwords) VALUES (NEW.id_adm,"adm", NEW.email_adm, NEW.pass_adm);
END; //

DELIMITER //
CREATE TRIGGER del_adm_login AFTER DELETE ON administrativos FOR EACH ROW
BEGIN
	DELETE FROM login WHERE (ids=OLD.id_adm AND perfiles="adm");
END; //

DELIMITER //
CREATE TRIGGER upd_adm_login AFTER UPDATE ON administrativos FOR EACH ROW
BEGIN
	UPDATE login SET emails=NEW.email_adm, passwords=NEW.pass_adm WHERE (ids=OLD.id_adm AND perfiles="adm");
END; //

desc administrativos;
select * from administrativos;