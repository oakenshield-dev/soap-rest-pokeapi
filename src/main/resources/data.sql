DROP TABLE IF EXISTS request_log;

CREATE TABLE  request_log(
	  id INT AUTO_INCREMENT  PRIMARY KEY,
	  request_date DATE NOT NULL,
	  method VARCHAR(50) NOT NULL,
	  ip_address VARCHAR(20) DEFAULT NULL
);