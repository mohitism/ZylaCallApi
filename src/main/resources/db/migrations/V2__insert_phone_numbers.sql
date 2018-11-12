DELIMITER $$
CREATE FUNCTION `insert_phone_numbers`(start BIGINT,end BIGINT) RETURNS varchar(100)
BEGIN

    declare i varchar(100) ;
    
    set i =start;
    
    WHILE i <= end DO
    	SET i = i + 1;
    	INSERT INTO `phone_directory` (`phone_number`, `status`, `status_changed_on`, `status_changed_remarks`, `created_on`, `created_by`, `updated_on`, 		 `updated_by`)
VALUES
	(i, 0, null, '', null, '', null, '');
	
  	END WHILE;

    return "Success";
END$$


DELIMITER ;

select insert_phone_numbers(11110,99998) as insertStatus;