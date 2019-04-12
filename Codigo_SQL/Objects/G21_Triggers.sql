/* TABELA INVESTIGADOR*/

DELIMITER $$
	
    CREATE TRIGGER UP_Investigador
    After update ON investigador
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1 into new_id from log_investigador;

		
	insert into log_investigador(IDInvestigador,Email,NomeInvestigador,CategoriaProfe,operacao,DataHoraLog,IdLog,Autor) 
	values (new.idinvestigador,new.email,new.nomeinvestigador,new.categoriaprofe,'U',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER INS_Investigador
    After insert ON investigador
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_investigador;

		
	insert into log_investigador(IDInvestigador,Email,NomeInvestigador,CategoriaProfe,operacao,DataHoraLog,IdLog,Autor) 
	values (new.idinvestigador,new.email,new.nomeinvestigador,new.categoriaprofe,'I',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER DEL_Investigador
    After delete ON investigador
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_investigador;

		
	insert into log_investigador(IDInvestigador,Email,NomeInvestigador,CategoriaProfe,operacao,DataHoraLog,IdLog,Autor) 
	values (old.idinvestigador,old.email,old.nomeinvestigador,old.categoriaprofe,'D',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;



/* TABELA administrador*/

DELIMITER $$
	
    CREATE TRIGGER UP_administrador
    After update ON administrador
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_administrador;

		
	insert into log_administrador(IDadmin,Email,Nomeadmin,operacao,DataHoraLog,IdLog,Autor) 
	values (new.IDAdmin,new.email,new.nomeadmin,'U',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER INS_administrador
    After insert ON administrador
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1 into new_id from log_administrador;

		
	insert into log_administrador(IDadmin,Email,Nomeadmin,operacao,DataHoraLog,IdLog,Autor) 
	values (new.IDAdmin,new.email,new.nomeadmin,'I',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
 CREATE TRIGGER DEL_administrador
    After delete ON administrador
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_administrador;

		
	insert into log_administrador(IDadmin,Email,Nomeadmin,operacao,DataHoraLog,IdLog,Autor) 
	values (old.IDAdmin,old.email,old.nomeadmin,'D',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;


/* TABELA cultura*/

DELIMITER $$
	
    CREATE TRIGGER UP_cultura
    After update ON cultura
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_cultura;

		
	insert into log_cultura(IDcultura,Nomecultura,descricaocultura,operacao,DataHoraLog,IdLog,Autor) 
	values (new.idcultura,new.nomecultura,new.descricaocultura,'U',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER INS_cultura
    After insert ON cultura
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_cultura;

		
	insert into log_cultura(IDcultura,Nomecultura,descricaocultura,operacao,DataHoraLog,IdLog,Autor) 
	values (new.idcultura,new.nomecultura,new.descricaocultura,'I',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
   	
    CREATE TRIGGER DEL_cultura
    After delete ON cultura
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_cultura;

		
	insert into log_cultura(IDcultura,Nomecultura,descricaocultura,operacao,DataHoraLog,IdLog,Autor) 
	values (old.idcultura,old.nomecultura,old.descricaocultura,'D',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;


/* TABELA medicoes*/

DELIMITER $$
	
    CREATE TRIGGER UP_medicoes
    After update ON medicoes
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_medicoes;

		
	insert into log_medicoes(NumeroMed,DatahoraMed,valorMed,operacao,DataHoraLog,IdLog,Autor) 
	values (new.numeroMed,new.DataHoraMed,new.valorMed,'U',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER INS_medicoes
    After insert ON medicoes
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_medicoes;

		
	insert into log_medicoes(NumeroMed,DatahoraMed,valorMed,operacao,DataHoraLog,IdLog,Autor) 
	values (new.numeroMed,new.DataHoraMed,new.valorMed,'I',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER DEL_medicoes
    After delete ON medicoes
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_medicoes;

		
	insert into log_medicoes(NumeroMed,DatahoraMed,valorMed,operacao,DataHoraLog,IdLog,Autor) 
	values (old.numeroMed,old.DataHoraMed,old.valorMed,'D',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;


/* TABELA medicoesluz*/

DELIMITER $$
	
    CREATE TRIGGER UP_medicoesluz
    After update ON medicoesluz
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_medicoesluz;

		
	insert into log_medicoesluz(IDMedicao,valorMedicaoLuz,dataHoraMed,operacao,DataHoraLog,IdLog,Autor) 
	values (new.idmedicao,new.valorMedicaoLuz,new.dataHoraMed,'U',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER INS_medicoesluz
    After insert ON medicoesluz
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_medicoesluz;

		
	insert into log_medicoesluz(IDMedicao,valorMedicaoLuz,dataHoraMed,operacao,DataHoraLog,IdLog,Autor) 
	values (new.idmedicao,new.valorMedicaoLuz,new.dataHoraMed,'I',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER del_medicoesluz
    After delete ON medicoesluz
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_medicoesluz;

		
	insert into log_medicoesluz(IDMedicao,valorMedicaoLuz,dataHoraMed,operacao,DataHoraLog,IdLog,Autor) 
	values (old.idmedicao,old.valorMedicaoLuz,old.dataHoraMed,'D',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;


/* TABELA medicoestemp*/

DELIMITER $$
	
    CREATE TRIGGER UP_medicoestemp
    After update ON medicoestemp
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_medicoestemp;

		
	insert into log_medicoestemp(IDmedicao,datahoramed,valormedicaotemp,operacao,DataHoraLog,IdLog,Autor) 
	values (new.idmedicao,new.datahoramed,new.valormedicaotemp,'U',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER INS_medicoestemp
    After insert ON medicoestemp
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_medicoestemp;

		
	insert into log_medicoestemp(IDmedicao,datahoramed,valormedicaotemp,operacao,DataHoraLog,IdLog,Autor) 
	values (new.idmedicao,new.datahoramed,new.valormedicaotemp,'I',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER DEL_medicoestemp
    After delete ON medicoestemp
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_medicoestemp;

		
	insert into log_medicoestemp(IDmedicao,datahoramed,valormedicaotemp,operacao,DataHoraLog,IdLog,Autor) 
	values (old.idmedicao,old.datahoramed,old.valormedicaotemp,'D',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;


/* TABELA sistema*/

DELIMITER $$
	
    CREATE TRIGGER UP_sistema
    After update ON sistema
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_sistema;

		
	insert into log_sistema(IDsistema,LimiteInferiorTemp,LimiteSuperiorTemp,LimiteInferiorLuz,LimiteSuperiorLuz,operacao,DataHoraLog,IdLog,Autor) 
	values (new.IDsistema,new.LimiteInferiorTemp,new.LimiteSuperiorTemp,new.LimiteInferiorLuz,new.LimiteSuperiorLuz,'U',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER INS_sistema
    After insert ON sistema
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_sistema;

		
	insert into log_sistema(IDsistema,LimiteInferiorTemp,LimiteSuperiorTemp,LimiteInferiorLuz,LimiteSuperiorLuz,operacao,DataHoraLog,IdLog,Autor) 
	values (new.IDsistema,new.LimiteInferiorTemp,new.LimiteSuperiorTemp,new.LimiteInferiorLuz,new.LimiteSuperiorLuz,'I',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER Del_sistema
    After delete ON sistema
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_sistema;

		
	insert into log_sistema(IDsistema,LimiteInferiorTemp,LimiteSuperiorTemp,LimiteInferiorLuz,LimiteSuperiorLuz,operacao,DataHoraLog,IdLog,Autor) 
	values (old.IDsistema,old.LimiteInferiorTemp,old.LimiteSuperiorTemp,old.LimiteInferiorLuz,old.LimiteSuperiorLuz,'D',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;


/* TABELA variaveis*/

DELIMITER $$
	
    CREATE TRIGGER UP_variaveis
    After update ON variaveis
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_variaveis;

		
	insert into log_variaveis(IDvariavel,Nomevariavel,operacao,DataHoraLog,IdLog,Autor) 
	values (new.IDvariavel,new.Nomevariavel,'U',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER Ins_variaveis
    After insert ON variaveis
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_variaveis;

		
	insert into log_variaveis(IDvariavel,Nomevariavel,operacao,DataHoraLog,IdLog,Autor) 
	values (new.IDvariavel,new.Nomevariavel,'I',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER Del_variaveis
    After delete ON variaveis
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_variaveis;

		
	insert into log_variaveis(IDvariavel,Nomevariavel,operacao,DataHoraLog,IdLog,Autor) 
	values (old.IDvariavel,old.Nomevariavel,'D',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

/* TABELA variaveismedidas*/

DELIMITER $$
	
    CREATE TRIGGER UP_variaveismedidas
    After update ON variaveismedidas
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_variaveismedidas;

		
	insert into log_variaveismedidas(LimiteInferior,LimiteSuperior,operacao,DataHoraLog,IdLog,Autor) 
	values (new.LimiteInferior,new.LimiteSuperior,'U',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER Ins_variaveismedidas
    After insert ON variaveismedidas
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_variaveismedidas;

		
	insert into log_variaveismedidas(LimiteInferior,LimiteSuperior,operacao,DataHoraLog,IdLog,Autor) 
	values (new.LimiteInferior,new.LimiteSuperior,'I',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

DELIMITER $$
	
    CREATE TRIGGER Del_variaveismedidas
    After delete ON variaveismedidas
    FOR EACH ROW
     BEGIN

	DECLARE new_id INT;

	SELECT COALESCE(MAX(idlog), 0) +1  into new_id from log_variaveismedidas;

		
	insert into log_variaveismedidas(LimiteInferior,LimiteSuperior,operacao,DataHoraLog,IdLog,Autor) 
	values (old.LimiteInferior,old.LimiteSuperior,'D',CURRENT_TIMESTAMP,new_id,user());
	
    END$$

DELIMITER ;

