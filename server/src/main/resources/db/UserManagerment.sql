CREATE DEFINER=`CSCI5308_14_DEVINT_USER`@`%` PROCEDURE `USER_MANAGEMENT`(IN action_type VARCHAR(20), IN input_data JSON)
BEGIN
    
    /* register for SQLExceptions */
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		INSERT INTO error_log(error_log_id, error_number, error_line, error_message, object_name, created_at)
		VALUES(null, ERROR_NUMBER(), ERROR_LINE(), ERROR_MESSAGE(), ERROR_PROCEDURE(), now());
    END;
    
    IF action_type = 'CREATE' THEN
		
        /* set input variables by fetching it from json input */
		SET @id = JSON_EXTRACT(input_data, '$.id');
		SET @userName = JSON_EXTRACT(input_data, '$.userName');
		SET @email = JSON_EXTRACT(input_data, '$.email');
		SET @password = JSON_EXTRACT(input_data, '$.password');
		SET @firstName = JSON_EXTRACT(input_data, '$.firstName');
		SET @lastName = JSON_EXTRACT(input_data, '$.lastName');
		SET @isVerified = JSON_EXTRACT(input_data, '$.isVerified');
		SET @status = JSON_EXTRACT(input_data, '$.status');
        
        /* check if any input field is missing */
        IF @userName IS NULL OR @email IS NULL OR @password IS NULL OR @firstName IS NULL OR @lastName IS NULL OR @isVerified IS NULL OR @status IS NULL THEN
            INSERT INTO error_log(error_log_id, error_number, error_line, error_message, object_name, created_at)
			VALUES(null, -1, ERROR_LINE(), 'Missing field(s)', ERROR_PROCEDURE(), now());
        END IF;
        
        /* insert record to the user table */
        INSERT INTO user (id, user_name, email, password, first_name, last_name, is_verified, status, updated_at, created_at)
        VALUES (null, @userName, @email, @password, @firstName, @lastName, @isVerified, @status, now(), now());

		/* insert record to log table */
        INSERT INTO error_log(error_log_id, error_number, error_line, error_message, object_name, created_at)
		VALUES(null, 1, -1, CONCAT('User successfully saved, Id: ', LAST_INSERT_ID()), '', now());
	
    ELSEIF action_type = 'UPDATE' THEN
    
		/* set input variables by fetching it from json input */
		SET @id = JSON_EXTRACT(input_data, '$.id');
		SET @userName = JSON_EXTRACT(input_data, '$.userName');
		SET @email = JSON_EXTRACT(input_data, '$.email');
		SET @password = JSON_EXTRACT(input_data, '$.password');
		SET @firstName = JSON_EXTRACT(input_data, '$.firstName');
		SET @lastName = JSON_EXTRACT(input_data, '$.lastName');
		SET @isVerified = JSON_EXTRACT(input_data, '$.isVerified');
		SET @status = JSON_EXTRACT(input_data, '$.status');
        
        /* check if any input field is missing */
        IF @userName IS NULL OR @email IS NULL OR @password IS NULL OR @firstName IS NULL OR @lastName IS NULL OR @isVerified IS NULL OR @status IS NULL THEN
            INSERT INTO error_log(error_log_id, error_number, error_line, error_message, object_name, created_at)
			VALUES(null, -1, ERROR_LINE(), 'Missing field(s)', ERROR_PROCEDURE(), now());
        END IF;
        
        /* update user */
        UPDATE user SET
        user_name = @userName, email = @email, password = @password, first_name = @firstName, last_name = @lastName, is_verified = @isVerified, status = @status, updated_at = now()
        WHERE id = @id;
        
        /* update record to log table */
        INSERT INTO error_log(error_log_id, error_number, error_line, error_message, object_name, created_at)
		VALUES(null, 1, -1, CONCAT('User successfully updated, Id: ', @id), '', now());
        
    ELSEIF action_type = 'DELETE' THEN
		
        /* set input variables by fetching it from json input */
		SET @id = JSON_EXTRACT(input_data, '$.id');
        
        /* delete user */
        DELETE FROM user WHERE id = @id;
        
        /* update record to log table */
        INSERT INTO error_log(error_log_id, error_number, error_line, error_message, object_name, created_at)
		VALUES(null, 1, -1, CONCAT('User successfully deleted, Id: ', @id), '', now());
        
    ELSEIF action_type = 'SELECTALL' THEN

		/* select all users */
        SELECT * FROM user;
        
		/* log query */
        INSERT INTO error_log(error_log_id, error_number, error_line, error_message, object_name, created_at)
		VALUES(null, 1, -1, 'Selected all Users successfully', '', now());
    
    ELSEIF action_type = 'SELECTBYID' THEN
		
        SELECT "SELECTBYID" AS Message;
        
        /* set input variables by fetching it from json input */
		SET @id = JSON_EXTRACT(input_data, '$.id');
        
		/* select user by id */
		SELECT * FROM user WHERE id = @id;
		
		/* log query */
        INSERT INTO error_log(error_log_id, error_number, error_line, error_message, object_name, created_at)
		VALUES(null, 1, -1, CONCAT('Select User successfully id : ', @id), '', now());
        
    END IF;
END