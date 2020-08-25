-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS CPSMusic;
CREATE DATABASE CPSMusic;
USE CPSMusic;


-- -----------------------------------------------------------------------------
-- - Construction de la table des utilisateurs                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
	IdUser		 		 int(4)		PRIMARY KEY AUTO_INCREMENT,
	Login		 		 varchar(20)	NOT NULL,
	Password	 		 varchar(20)	NOT NULL,
	ConnectionNumber	 int(4)		NOT NULL DEFAULT 0
) ENGINE = InnoDB;

INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 1, 'Anderson',	'Neo' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 2, 'Skywalker',	'Luke' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 3, 'Plissken',	'Snake' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 4, 'Ripley',		'Ellen' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 5, 'Bond',		'James' );

SELECT * FROM T_Users;

-- -----------------------------------------------------------------------------
-- - Construction de la table des administrateurs                            ---
-- -----------------------------------------------------------------------------

CREATE TABLE T_Admins (
	IdUser				int(4)		NOT NULL REFERENCES T_Users(IdUser),
	Rights				varchar(10) NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Admins VALUES ( 1, "-RWX------" );
INSERT INTO T_Admins VALUES ( 5, "-RWK------" );

-- -----------------------------------------------------------------------------
-- - Construction de la tables des partitions en vente                         ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Sheet (
	IdSheet			int(4)		PRIMARY KEY AUTO_INCREMENT,
	SheetName			text     	NOT NULL,
	InstrumentType				text        NOT NULL,
	OriginalArtistName		text        NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Sheet ( SheetName, InstrumentType, OriginalArtistName ) VALUES ( 'La lettre a Elise',					'piano',				'Ludwig van Beethoven' );
INSERT INTO T_Sheet ( SheetName, InstrumentType, OriginalArtistName ) VALUES ( 'Tristan et Isolde',					'opera',			'Richard Wagner ' );
INSERT INTO T_Sheet ( SheetName, InstrumentType, OriginalArtistName ) VALUES ( 'Sonate pour violon',	'violon',	'Jean Sebastien Bach' );
INSERT INTO T_Sheet ( SheetName, InstrumentType, OriginalArtistName ) VALUES ( 'Concerto pour flute', 			'flute',			'Wolfgang Amadeus Mozart' );
INSERT INTO T_Sheet ( SheetName, InstrumentType, OriginalArtistName ) VALUES ( 'Les saisons', 			'opera',					'Piotr Llitch Tchaikovski' );



SELECT * FROM T_Articles;

-- -----------------------------------------------------------------------------
-- - Construction de la tables des commandes                                 ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Commands (
	IdCommand		int(4)		PRIMARY KEY AUTO_INCREMENT,
	IdUser			int(4)		NOT NULL REFERENCES T_Users(IdUser),
    CommandDate     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE TABLE T_CommandLines (
	IdCommandLine	int(4)		PRIMARY KEY AUTO_INCREMENT,
	IdCommand		int(4)		NOT NULL REFERENCES T_Commands(IdCommand),
	IdArticle		int(4)		NOT NULL REFERENCES T_Articles(IdCommand),
	Quantity		int(4) 		NOT NULL
) ENGINE = InnoDB;

-- Une première commande
INSERT INTO T_Commands (IdUser, CommandDate) VALUES ( 2, now() ); 
INSERT INTO T_CommandLines (IdCommand, IdSheet, Quantity) VALUES (1, 1, 5); 
INSERT INTO T_CommandLines (IdCommand, IdSheet, Quantity) VALUES (1, 3, 3);

-- Une seconde commande, pour un admin
INSERT INTO T_Commands (IdUser, CommandDate) VALUES ( 1, now() ); 
INSERT INTO T_CommandLines (IdCommand, IdSheet, Quantity) VALUES (2, 2, 4); 
INSERT INTO T_CommandLines (IdCommand, IdSheet, Quantity) VALUES (2, 3, 1);
INSERT INTO T_CommandLines (IdCommand, IdSheet, Quantity) VALUES (2, 4, 1);

