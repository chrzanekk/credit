
CREATE TABLE credit (
	creditID INT AUTO_INCREMENT,
	creditName VARCHAR(50),
	customerID INT NOT NULL,
	creditValue DECIMAL(10,2),
	PRIMARY KEY (creditID),
	FOREIGN KEY  (customerID) REFERENCES customer(customerID)
);