# BillingTest
This is a simple java application  to do the Billing checkout as in a supermarket.

This application works with JDK 8 or higher version.

To execute this application create a db:billingdb and table item.
 url = "jdbc:mysql://localhost:3306/billingdb";
 username = "root";
 password = "Mydb!001";
 The queries used:

CREATE SCHEMA `billingdb` ;

CREATE TABLE `billingdb`.`item` (
  `item_name` VARCHAR(50) NOT NULL,
  `unit_price` INT NOT NULL,
  `special_price` VARCHAR(45) NULL,
  PRIMARY KEY (`item_name`));



INSERT INTO `billingdb`.`item` (`item_name`, `unit_price`, `special_price`) VALUES ('A', '50', '3for130');
INSERT INTO `billingdb`.`item` (`item_name`, `unit_price`, `special_price`) VALUES ('B', '30', '2for45');
INSERT INTO `billingdb`.`item` (`item_name`, `unit_price`) VALUES ('C', '20');
INSERT INTO `billingdb`.`item` (`item_name`, `unit_price`) VALUES ('D', '15');

Note: Attached the Sample console output document. Upon any exception or wrong input restart the application.
