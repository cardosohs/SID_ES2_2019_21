CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_processaLuz`(in momento timestamp, in ordem int, in sensor varchar(9),
 in medicao double, in datetime_sensor datetime)

 
 -- validação se a nova medicao e ou nao outlier
 -- ---------------------------------------------
 BEGIN
#verifica se é outlier 
	#só se tiver mais de 5 medicoes e a última medição tiver sido feita à menos de 10 minutos;
   Set @contador = (select count(idmedicao) from medicoesluz);
   set @ultimaHora = ( select datahoramed
			from medicoesluz
        order by datahoramed desc 
        limit 1);
   if (@contador > 5 and (momento < date_add(@ultimaHora, interval 10 minute) ) ) then #!!!!!verifica à quanto tempo foi a última medicao
  #obtem desvio padrao das últimas 10 medicoes
  set @despad = (select std(Valores) 
	from ( select valormedicaoluz as valores
		from medicoesluz
        order by datahoramed desc 
        limit 10)
        as dp);
   #obtem  último valor registado;
   set @lastMed = ( select valorMedicaoLuz
			from medicoesluz
        order by datahoramed desc 
        limit 1);
    #verifica se a diferença entre o valor atual e o ultimo é inferior a 4x desvioPadrao;
    if ((ABS(@lastmed-medicao)<(4*@despad)) or @devpad=0) then #!se o desvio padrao for zero aceita
    #insere na tabela medicoesluz
		insert into	medicoesluz (datahoraMed,valorMedicaoLuz)
		values (datetime_sensor,medicao);
	end if;
    
   else # Se o número de medições ainda for pequeno ou muito antigas
   #insere na tabela medicoesluz
   insert into	medicoesluz (datahoraMed,valorMedicaoLuz)
		values (datetime_sensor,medicao);
   end if;

END
-- fim
-- ---------

-- valida se a medicao de temperatura e outlier ou nao
-- ------
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_processaTemp`(in momento timestamp, in ordem int, in sensor varchar(9),
 in medicao double, in datetime_sensor datetime)
BEGIN
#verifica se é outlier 
	#só se tiver mais de 5 medicoes e a última medição tiver sido feita à menos de 10 minutos;
   Set @contador = (select count(idmedicao) from medicoestemp);
   set @ultimaHora = ( select datahoramed
			from medicoestemp
        order by datahoramed desc 
        limit 1);
   if @contador > 5  and (momento < date_add(@ultimaHora, interval 10 minute) ) then #verifica à quanto tempo foi a última medicao
  #obtem desvio padrao das últimas 10 medicoes
  set @despad = (select std(Valores) 
	from ( select valormedicaotemp as valores
		from medicoestemp
        order by datahoramed desc 
        limit 10)
        as dp);
   #obtem  último valor registado;
   set @lastMed = ( select valorMedicaoTemp
			from medicoestemp
        order by datahoramed desc 
        limit 1);
    #verifica se a diferença entre o valor atual e o ultimo é inferior a 4x desvioPadrao;
    if ((ABS(@lastmed-medicao)<(4*@despad)) or @devpad=0) then #!se o desvio padrao for zero aceita
    #insere na tabela medicoesluz
		insert into	medicoestemp (datahoraMed,valorMedicaotemp)
		values (datetime_sensor,medicao);
	end if;
    
   else # Se o número de medições ainda for pequeno ou muito antigas
   #insere na tabela medicoesluz
   insert into	medicoestemp (datahoraMed,valorMedicaotemp)
		values (datetime_sensor,medicao);
   end if;


END

-- fim
-- ------