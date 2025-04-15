USE CLINICA_MEDICA;

DELIMITER $$
DROP PROCEDURE IF EXISTS PROC_CARREGA_PACIENTES;$$
CREATE PROCEDURE PROC_CARREGA_PACIENTES()
BEGIN
	
    DECLARE N1               INT;                  # Seletor de 1º nome.
    DECLARE N2               INT;                  # Seletor de 2º nome.
    DECLARE N3               INT;                  # Seletor de sobrenome.
    DECLARE I                INT DEFAULT 0;        # Contador.
    DECLARE GENERO           INT;                  # Seletor para gênero.
                                                   
    DECLARE _NOME            VARCHAR(200);         # Variáveis para atribuição das colunas da tabela.
    DECLARE _DATA_NASCIMENTO DATETIME;             # 
    DECLARE _SEXO            VARCHAR(1);           # 
    DECLARE _ENDERECO        VARCHAR(100);         # 
    DECLARE _TELEFONE        VARCHAR(14);          # 
    DECLARE _ATIVO           TINYINT(1);           # 
                                                   
    DECLARE ANO              INT;                  # Obtenção alteório de data/hora.
	DECLARE MES              INT;                  # 
	DECLARE DIA              INT;                  # 
    DECLARE MAXDIA           INT;                  # 
    DECLARE HORA             VARCHAR(2);           # 
    DECLARE MINUTO           VARCHAR(2);           # 
    DECLARE SEGUNDO          VARCHAR(2);           # 
                                                   
    DECLARE TIPO_LOGRADOURO  INT;                  # Obtenção aletório de endereço.
    DECLARE LOGRADOURO       INT;                  # 
    DECLARE NUM_LOGRADOURO   INT;                  # 
    
	DELETE FROM REGISTRO_CONSULTA WHERE ID_CONSULTA > 0;
    DELETE FROM AGENDAMENTO WHERE ID_CONSULTA > 0; # Remove qualquer vínculo de angedamento.
    ALTER TABLE AGENDAMENTO AUTO_INCREMENT = 1;    # Reinicia o controle de auto incremento da ID
    DELETE FROM PACIENTE WHERE ID_PACIENTE > 0;    # Limpa a tabela
    ALTER TABLE PACIENTE AUTO_INCREMENT = 1;       # Reinicia o controle de auto incremento da ID
    
    WHILE I < 1000 DO
    BEGIN
		
        SET I      = I + 1;
		SET N1     = ROUND(RAND() * 30);
        SET N2     = ROUND(RAND() * 30);
        SET N3     = ROUND(RAND() * 10);
        SET GENERO = ROUND(RAND());
        
        # GARANTE PELO MENOS 1.
        IF N1 = 0 THEN
			SET N1 = 1;
        END IF;
        
        # GARANTE PELO MENOS 1.
        IF N2 = 0 THEN
			SET N2 = 1;
        END IF;
        
        # GARANTE PELO MENOS 1.
        IF N3 = 0 THEN
			SET N3 = 1;
        END IF;
        
        # MASCULINO
        IF GENERO = 0 THEN
        BEGIN
			
            SET _SEXO = 'M';
        
            # 1º NOME
            CASE N1
                WHEN 1  THEN SET _NOME = 'ANDRE';
                WHEN 2  THEN SET _NOME = 'ANDRIANO';
                WHEN 3  THEN SET _NOME = 'AILTON';
                WHEN 4  THEN SET _NOME = 'BARNEI';
                WHEN 5  THEN SET _NOME = 'BRANDON';
                WHEN 6  THEN SET _NOME = 'BRUNO';
                WHEN 7  THEN SET _NOME = 'CARLOS';
                WHEN 8  THEN SET _NOME = 'CAIO';
                WHEN 9  THEN SET _NOME = 'CIRO';
                WHEN 10 THEN SET _NOME = 'DANIEL';
                WHEN 11 THEN SET _NOME = 'DARLAM';
                WHEN 12 THEN SET _NOME = 'DARLEI';
                WHEN 13 THEN SET _NOME = 'EVERTON';
                WHEN 14 THEN SET _NOME = 'EMERSON';
                WHEN 15 THEN SET _NOME = 'EVALDO';
                WHEN 16 THEN SET _NOME = 'FERNANDO';
                WHEN 17 THEN SET _NOME = 'FELIPE';
                WHEN 18 THEN SET _NOME = 'FILIPE';
                WHEN 19 THEN SET _NOME = 'GABRIEL';
                WHEN 20 THEN SET _NOME = 'GARCIA';
                WHEN 21 THEN SET _NOME = 'GILDO';
                WHEN 22 THEN SET _NOME = 'HERALDO';
                WHEN 23 THEN SET _NOME = 'HELTON';
                WHEN 24 THEN SET _NOME = 'HEMIRIDIANO';
                WHEN 25 THEN SET _NOME = 'ISRAEL';
                WHEN 26 THEN SET _NOME = 'IRUA';
                WHEN 27 THEN SET _NOME = 'IVAM';
                WHEN 28 THEN SET _NOME = 'JOSÉ';
                WHEN 29 THEN SET _NOME = 'JOSIAS';
                ELSE         SET _NOME = 'JERSON';
            END CASE;
            
            # 2º NOME
            CASE N2
                WHEN 1  THEN SET _NOME = CONCAT(_NOME, ' ', 'ARIEL');
                WHEN 2  THEN SET _NOME = CONCAT(_NOME, ' ', 'AISTÓTOLES');
                WHEN 3  THEN SET _NOME = CONCAT(_NOME, ' ', 'ARIES');
                WHEN 4  THEN SET _NOME = CONCAT(_NOME, ' ', 'BARTOLOMEU');
                WHEN 5  THEN SET _NOME = CONCAT(_NOME, ' ', 'BARTINI');
                WHEN 6  THEN SET _NOME = CONCAT(_NOME, ' ', 'BARTOLINI');
                WHEN 7  THEN SET _NOME = CONCAT(_NOME, ' ', 'CAIO');
                WHEN 8  THEN SET _NOME = CONCAT(_NOME, ' ', 'CAIRU');
                WHEN 9  THEN SET _NOME = CONCAT(_NOME, ' ', 'CAIQUE');
                WHEN 10 THEN SET _NOME = CONCAT(_NOME, ' ', 'DANTE');
                WHEN 11 THEN SET _NOME = CONCAT(_NOME, ' ', 'DINES');
                WHEN 12 THEN SET _NOME = CONCAT(_NOME, ' ', 'DAIA');
                WHEN 13 THEN SET _NOME = CONCAT(_NOME, ' ', 'ESTEVAM');
                WHEN 14 THEN SET _NOME = CONCAT(_NOME, ' ', 'ESTEIO');
                WHEN 15 THEN SET _NOME = CONCAT(_NOME, ' ', 'ETIMO');
                WHEN 16 THEN SET _NOME = CONCAT(_NOME, ' ', 'FAEL');
                WHEN 17 THEN SET _NOME = CONCAT(_NOME, ' ', 'FEIL');
                WHEN 18 THEN SET _NOME = CONCAT(_NOME, ' ', 'FERREIRA');
                WHEN 19 THEN SET _NOME = CONCAT(_NOME, ' ', 'GERHS');
                WHEN 20 THEN SET _NOME = CONCAT(_NOME, ' ', 'GARG');
                WHEN 21 THEN SET _NOME = CONCAT(_NOME, ' ', 'GAIO');
                WHEN 22 THEN SET _NOME = CONCAT(_NOME, ' ', 'HERMANN');
                WHEN 23 THEN SET _NOME = CONCAT(_NOME, ' ', 'HAIR');
                WHEN 24 THEN SET _NOME = CONCAT(_NOME, ' ', 'HEIRE');
                WHEN 25 THEN SET _NOME = CONCAT(_NOME, ' ', 'IESBIK');
                WHEN 26 THEN SET _NOME = CONCAT(_NOME, ' ', 'ISTOM');
                WHEN 27 THEN SET _NOME = CONCAT(_NOME, ' ', 'IRLAND');
                WHEN 28 THEN SET _NOME = CONCAT(_NOME, ' ', 'JOSEPH');
                WHEN 29 THEN SET _NOME = CONCAT(_NOME, ' ', 'JEREMIAS');
                ELSE         SET _NOME = CONCAT(_NOME, ' ', 'JAIL');
            END CASE;
            
            # 3º NOME
            CASE N3
                WHEN 1 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE AMARANTES');
                WHEN 2 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE BARBOSA ');
                WHEN 3 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE CARTEL');
                WHEN 4 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE DIAMANTINA');
                WHEN 5 THEN SET _NOME = CONCAT(_NOME, ' ', 'DA ESLOVÁQUIA');
                WHEN 6 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE FARIAS');
                WHEN 7 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE GARIGUER');
                WHEN 8 THEN SET _NOME = CONCAT(_NOME, ' ', 'HEBROM');
                WHEN 9 THEN SET _NOME = CONCAT(_NOME, ' ', 'GENEROTOM');
                ELSE        SET _NOME = CONCAT(_NOME, ' ', 'JESSIE');
            END CASE;
		END;
        # FEMININO
        ELSE
        BEGIN
        
			SET _SEXO = 'F';
        
            # 1º NOME
            CASE N1
                WHEN 1  THEN SET _NOME = 'ANDRINA';
                WHEN 2  THEN SET _NOME = 'ANDREIA';
                WHEN 3  THEN SET _NOME = 'ARIELE';
                WHEN 4  THEN SET _NOME = 'BARBARA';
                WHEN 5  THEN SET _NOME = 'BARTIRA';
                WHEN 6  THEN SET _NOME = 'BRUNA';
                WHEN 7  THEN SET _NOME = 'CARLA';
                WHEN 8  THEN SET _NOME = 'CIDA';
                WHEN 9  THEN SET _NOME = 'CELIA';
                WHEN 10 THEN SET _NOME = 'DANIELA';
                WHEN 11 THEN SET _NOME = 'DAIANE';
                WHEN 12 THEN SET _NOME = 'DENISE';
                WHEN 13 THEN SET _NOME = 'ELIANE';
                WHEN 14 THEN SET _NOME = 'EMILIANE';
                WHEN 15 THEN SET _NOME = 'EVELLIN';
                WHEN 16 THEN SET _NOME = 'FABIANA';
                WHEN 17 THEN SET _NOME = 'FERNANDA';
                WHEN 18 THEN SET _NOME = 'FRANCIELE';
                WHEN 19 THEN SET _NOME = 'GABRIELA';
                WHEN 20 THEN SET _NOME = 'GILDA';
                WHEN 21 THEN SET _NOME = 'GEISE';
                WHEN 22 THEN SET _NOME = 'HERA';
                WHEN 23 THEN SET _NOME = 'HILDA';
                WHEN 24 THEN SET _NOME = 'HOIDA';
                WHEN 25 THEN SET _NOME = 'IRIS';
                WHEN 26 THEN SET _NOME = 'ISABELA';
                WHEN 27 THEN SET _NOME = 'IULDA';
                WHEN 28 THEN SET _NOME = 'JESSI';
                WHEN 29 THEN SET _NOME = 'JOILMA';
                ELSE         SET _NOME = 'JOSSI';
            END CASE;
            
            # 2º NOME
            CASE N2
                WHEN 1  THEN SET _NOME = CONCAT(_NOME, ' ', 'ARIEL');
                WHEN 2  THEN SET _NOME = CONCAT(_NOME, ' ', 'AISTÓTOLES');
                WHEN 3  THEN SET _NOME = CONCAT(_NOME, ' ', 'ARIES');
                WHEN 4  THEN SET _NOME = CONCAT(_NOME, ' ', 'BARTOLOMEU');
                WHEN 5  THEN SET _NOME = CONCAT(_NOME, ' ', 'BARTINI');
                WHEN 6  THEN SET _NOME = CONCAT(_NOME, ' ', 'BARTOLINI');
                WHEN 7  THEN SET _NOME = CONCAT(_NOME, ' ', 'CAIO');
                WHEN 8  THEN SET _NOME = CONCAT(_NOME, ' ', 'CAIRU');
                WHEN 9  THEN SET _NOME = CONCAT(_NOME, ' ', 'CAIQUE');
                WHEN 10 THEN SET _NOME = CONCAT(_NOME, ' ', 'DANTE');
                WHEN 11 THEN SET _NOME = CONCAT(_NOME, ' ', 'DINES');
                WHEN 12 THEN SET _NOME = CONCAT(_NOME, ' ', 'DAIA');
                WHEN 13 THEN SET _NOME = CONCAT(_NOME, ' ', 'ESTEVAM');
                WHEN 14 THEN SET _NOME = CONCAT(_NOME, ' ', 'ESTEIO');
                WHEN 15 THEN SET _NOME = CONCAT(_NOME, ' ', 'ETIMO');
                WHEN 16 THEN SET _NOME = CONCAT(_NOME, ' ', 'FAEL');
                WHEN 17 THEN SET _NOME = CONCAT(_NOME, ' ', 'FEIL');
                WHEN 18 THEN SET _NOME = CONCAT(_NOME, ' ', 'FERREIRA');
                WHEN 19 THEN SET _NOME = CONCAT(_NOME, ' ', 'GERHS');
                WHEN 20 THEN SET _NOME = CONCAT(_NOME, ' ', 'GARG');
                WHEN 21 THEN SET _NOME = CONCAT(_NOME, ' ', 'GAIO');
                WHEN 22 THEN SET _NOME = CONCAT(_NOME, ' ', 'HERMANN');
                WHEN 23 THEN SET _NOME = CONCAT(_NOME, ' ', 'HAIR');
                WHEN 24 THEN SET _NOME = CONCAT(_NOME, ' ', 'HEIRE');
                WHEN 25 THEN SET _NOME = CONCAT(_NOME, ' ', 'IESBIK');
                WHEN 26 THEN SET _NOME = CONCAT(_NOME, ' ', 'ISTOM');
                WHEN 27 THEN SET _NOME = CONCAT(_NOME, ' ', 'IRLAND');
                WHEN 28 THEN SET _NOME = CONCAT(_NOME, ' ', 'JOSEPH');
                WHEN 29 THEN SET _NOME = CONCAT(_NOME, ' ', 'JEREMIAS');
                ELSE         SET _NOME = CONCAT(_NOME, ' ', 'JAIL');
            END CASE;
            
            # 3º NOME
            CASE N3
                WHEN 1 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE AMARANTES');
                WHEN 2 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE BARBOSA ');
                WHEN 3 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE CARTEL');
                WHEN 4 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE DIAMANTINA');
                WHEN 5 THEN SET _NOME = CONCAT(_NOME, ' ', 'DA ESLOVÁQUIA');
                WHEN 6 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE FARIAS');
                WHEN 7 THEN SET _NOME = CONCAT(_NOME, ' ', 'DE GARIGUER');
                WHEN 8 THEN SET _NOME = CONCAT(_NOME, ' ', 'HEBROM');
                WHEN 9 THEN SET _NOME = CONCAT(_NOME, ' ', 'GENEROTOM');
                ELSE        SET _NOME = CONCAT(_NOME, ' ', 'JESSIE');
            END CASE;
		END;
        END IF;
        
        SET ANO = ROUND(RAND() * 60) + 1950; # COM INTERVALO DE 1950 A 2010.
        
        SET MES = ROUND(RAND() * 12);
        
        IF MES = 0 THEN
            SET MES = 1;
        END IF;
        
        # 2	Fevereiro   28 (29 bissexto)
        # 
        # 4	Abril	    30
        # 6	Junho	    30
        # 9	Setembro    30
        # 11 Novembro	30
        # 
        # 1	Janeiro	    31
        # 3	Março	    31
        # 5	Maio	    31
        # 7	Julho	    31
        # 8	Agosto	    31
        # 10 Outubro    31
        # 12 Dezembro	31

        CASE MES
            WHEN 2  THEN SET MAXDIA = 28; # Fevereiro
            
            WHEN 4  THEN SET MAXDIA = 30; # Abril
            WHEN 6  THEN SET MAXDIA = 30; # Junho
            WHEN 9  THEN SET MAXDIA = 30; # Setembro
            WHEN 11 THEN SET MAXDIA = 30; # Novembro
            
            WHEN 1  THEN SET MAXDIA = 31; # Janeiro
            WHEN 3  THEN SET MAXDIA = 31; # Março
            WHEN 5  THEN SET MAXDIA = 31; # Maio
            WHEN 7  THEN SET MAXDIA = 31; # Julho
            WHEN 8  THEN SET MAXDIA = 31; # Agosto
            WHEN 10 THEN SET MAXDIA = 31; # Outubro
            WHEN 12 THEN SET MAXDIA = 31; # Dezembro
        END CASE;
        
        SET DIA = ROUND(RAND() * MAXDIA);
        
        IF DIA = 0 THEN
            SET DIA = 1;
        END IF;
        
        # DESPRESAR O ANO BISSEXTO
        IF DIA = 29 AND MES = 2 THEN
            SET DIA = 28;
        END IF;
               
        SET HORA    = ROUND(RAND() * 23);
        SET MINUTO  = ROUND(RAND() * 59);
        SET SEGUNDO = ROUND(RAND() * 59);
        
        # GARANTIR OS DOIS DÍGITOS PARA HORA, MINUTO E SEGUNDO.
        IF HORA < 10 THEN
            SET HORA = CONCAT('0', HORA);
        END IF;
        
        IF MINUTO < 10 THEN
            SET MINUTO = CONCAT('0', MINUTO);
        END IF;
        
        IF SEGUNDO < 10 THEN
            SET SEGUNDO = CONCAT('0', SEGUNDO);
        END IF;
        
        SET _DATA_NASCIMENTO = CONCAT(ANO, '-', MES, '-', DIA, ' ', HORA, ':', MINUTO, ':', SEGUNDO);
        
        SET TIPO_LOGRADOURO = ROUND(RAND() * 3);
                
        CASE TIPO_LOGRADOURO
            WHEN 0 THEN SET _ENDERECO = 'RUA';
            WHEN 1 THEN SET _ENDERECO = 'AV';
            WHEN 2 THEN SET _ENDERECO = 'TV';
            ELSE        SET _ENDERECO = 'PQ';
        END CASE;
        
        SET LOGRADOURO = ROUND(RAND() * 9);
        
        CASE LOGRADOURO
            WHEN 0 THEN SET _ENDERECO = CONCAT(_ENDERECO, ' ', 'ANDRADAS');
            WHEN 1 THEN SET _ENDERECO = CONCAT(_ENDERECO, ' ', 'BARBOSA DA CONCEIÇÃO');
            WHEN 2 THEN SET _ENDERECO = CONCAT(_ENDERECO, ' ', 'CABRAL DA SILVA');
            WHEN 3 THEN SET _ENDERECO = CONCAT(_ENDERECO, ' ', 'DUQUE DE CAXIAS');
            WHEN 4 THEN SET _ENDERECO = CONCAT(_ENDERECO, ' ', 'ESTEVÃO');
            WHEN 5 THEN SET _ENDERECO = CONCAT(_ENDERECO, ' ', 'SGT FARIAS');
            WHEN 6 THEN SET _ENDERECO = CONCAT(_ENDERECO, ' ', '18 DE NOVEMBRO');
            WHEN 7 THEN SET _ENDERECO = CONCAT(_ENDERECO, ' ', '20 DE OUTUBRO');
            WHEN 8 THEN SET _ENDERECO = CONCAT(_ENDERECO, ' ', 'TEN GALIÃO');
            ELSE        SET _ENDERECO = CONCAT(_ENDERECO, ' ', 'CAIQUE DE ALBUQUERQUE');
        END CASE;
        
        SET NUM_LOGRADOURO = ROUND(RAND() * 1000);
        
        SET _ENDERECO = CONCAT(_ENDERECO, ', ', NUM_LOGRADOURO);
        
        SET _TELEFONE =
            CONCAT(
                    '51'
                    , '3'
                    , ROUND(RAND() * 9)
                    , ROUND(RAND() * 9)
                    , ROUND(RAND() * 9)
                    , ROUND(RAND() * 9)
                    , ROUND(RAND() * 9)
                    , ROUND(RAND() * 9)
                    , ROUND(RAND() * 9)
                );
                
        SET _ATIVO = ROUND(RAND());
        
        INSERT INTO PACIENTE
        (
			NOME
            , DATA_NASCIMENTO
            , SEXO
            , ENDERECO
            , TELEFONE
            , ATIVO
            , FOTO
		)
        VALUES
        (
			_NOME
            , _DATA_NASCIMENTO
            , _SEXO
            , _ENDERECO
            , _TELEFONE
            , _ATIVO
            , LOAD_FILE('D:/Temp/paciente.png') # Sempre por em caminhos que não envolvam pasta de usuário ou nível de previlégio elevado!
		);
        
	END;
	END WHILE;
    
END $$
DELIMITER ;

CALL PROC_CARREGA_PACIENTES();
#SELECT * FROM PACIENTE;
