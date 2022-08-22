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

Sample console output document:
Hello User!!! 
Choose any option: 
1.View items details 
2.Update price of item 
3.Scan items and calculate bill
1
User selected Option:1
Item [itemName=A, unitPrice=50, specialPrice=3for130]
Item [itemName=B, unitPrice=30, specialPrice=2for45]
Item [itemName=C, unitPrice=20, specialPrice=No special offer on this]
Item [itemName=D, unitPrice=15, specialPrice=4for25]
Hello User!!! 
Choose any option: 
1.View items details 
2.Update price of item 
3.Scan items and calculate bill
2
User selected Option:2
Enter name of the item to be updated
D
Enter unitprice(pence) to be updated.If no change input:exit
10
Enter specialprice to be updated in format 3for50.Special price should be in pence format.If no change input:exit
exit
Item :D updated. New data:Item [itemName=D, unitPrice=10, specialPrice=4for25]
Hello User!!! 
Choose any option: 
1.View items details 
2.Update price of item 
3.Scan items and calculate bill
1
User selected Option:1
Item [itemName=A, unitPrice=50, specialPrice=3for130]
Item [itemName=B, unitPrice=30, specialPrice=2for45]
Item [itemName=C, unitPrice=20, specialPrice=No special offer on this]
Item [itemName=D, unitPrice=10, specialPrice=4for25]
Hello User!!! 
Choose any option: 
1.View items details 
2.Update price of item 
3.Scan items and calculate bill
3
User selected Option:3
Enter the item names to be scanned for checkout. Type: exit at end of scanning
===============================================================================
a
Running bill amount:50 pence
a
Running bill amount:100 pence
b
Running bill amount:130 pence
b
Deducting the unit prices and considering the special offer price for the item:b
Running bill amount:145 pence
a
Deducting the unit prices and considering the special offer price for the item:a
Running bill amount:175 pence
c
Running bill amount:195 pence
c
Running bill amount:215 pence
b
Running bill amount:245 pence
b
Deducting the unit prices and considering the special offer price for the item:b
Running bill amount:260 pence
a
Running bill amount:310 pence
exit
Scanned items are:[a=4, b=4, c=2]
Generating Bill
===============================
ITEM a   NO OF UNITS: 4 COST:180
ITEM b   NO OF UNITS: 4 COST:90
ITEM c   NO OF UNITS: 2 COST:40
================================================================================================
TOTAL AMOUNT TO BE PAID: £3.10
Hello User!!! 
Choose any option: 
1.View items details 
2.Update price of item 
3.Scan items and calculate bill
