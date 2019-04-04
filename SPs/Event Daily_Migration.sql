SET GLOBAL event_scheduler = ON;
CREATE EVENT Daily_Migration
ON SCHEDULE every 1 day starts timestamp('2019-03-30 02:00:00') 
DO 
CALL g21destino.SP_Full_Update();