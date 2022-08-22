package billing.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import biling.dao.ItemDao;
import billing.model.Item;

public class ItemController {
	private static Scanner input= new Scanner(System.in);
	private static int totalBill =0;
	private static Map<String,Integer> scanItemMap= new HashMap();

	public static void main(String[] args){
		try {
			do {
		System.out.println("Hello User!!! \nChoose any option: \n1.View items details \n2.Update price of item \n3.Scan items and calculate bill");
		
		int option=0;
		if (!input.hasNextInt()) {
			System.out.println("That's not a number!");
			break;
		}else {
			option= Integer.parseInt(input.next());
			System.out.println("User selected Option:"+option);
			
			switch(option) {
			case 1: ItemDao.getAllItems(); break;
			case 2: updateItem();break;
			case 3: scanItems();break;			
			default: System.out.println("Invalid input");
			}
						
		}
		
			}while(true);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	

	private static void getBill() throws SQLException {
		if(!scanItemMap.isEmpty()) {
			int totalCost=0;
			for(Map.Entry<String, Integer> entry: scanItemMap.entrySet()) {
				int getBillAmount= calculateTotalBillForItem(entry.getKey(), entry.getValue());
				System.out.println("ITEM "+ entry.getKey() + "   NO OF UNITS: "+ entry.getValue() + " COST:"+getBillAmount);
				totalCost+=getBillAmount;
			}
			System.out.println("================================================================================================");
			System.out.println("TOTAL AMOUNT TO BE PAID: £"+(totalCost/100 )+"."+(totalCost%100));
		}
		
	}



	private static int calculateTotalBillForItem(String name, Integer value) throws SQLException {
	    int calcualtedCost=0;
		Item itemData= ItemDao.getItemByName(name);
		int itemUnitPrice=itemData.getUnitPrice();
		int specialOfferCount=0;
		int specialOfferPrice=0;
		//get the special Price units count 3for2
		if(itemData.getSpecialPrice()!=null && !itemData.getSpecialPrice().isEmpty()) {
		String[] specialPriceContent= itemData.getSpecialPrice().split("for");
		 specialOfferCount=Integer.parseInt(specialPriceContent[0]);
		 specialOfferPrice= Integer.parseInt(specialPriceContent[1]) ;
		}
		if(value==1) {
			return itemUnitPrice;
		}else {
			if(itemData.getSpecialPrice()!=null && !itemData.getSpecialPrice().isEmpty()) {
			if(value>1 && value<specialOfferCount) {
				for(int i=1;i<=value;i++) {
					calcualtedCost+=itemUnitPrice;
				}
				return calcualtedCost;
			}else if(value>1 && value>specialOfferCount) {
				int offerTimes=value%specialOfferCount;//1
				int offerRest=value/specialOfferCount;//1
				calcualtedCost=offerRest*specialOfferPrice;//130
				for(int dup=1;dup<=offerTimes;dup++) {
					calcualtedCost+=itemUnitPrice;
				}
				
			}else if(value==specialOfferCount) {
				calcualtedCost+=specialOfferPrice;
			}
			}else {
				for(int i=1;i<=value;i++) {
					calcualtedCost+=itemUnitPrice;
				}
			}
		}
		
		return calcualtedCost;
	}






	private static void scanItems() throws Exception{
	   System.out.println("Enter the item names to be scanned for checkout. Type: exit at end of scanning");
	   System.out.println("===============================================================================");
	   scanItemMap= new HashMap();
	   totalBill=0;
	   String scanItem;
	   do {
		   //int defaulCount= 1;
		   scanItem= input.next();
		   if(!scanItem.equalsIgnoreCase("exit")){
		   if(!scanItemMap.isEmpty() && scanItemMap.keySet().contains(scanItem)) {
			   int itemCount= scanItemMap.get(scanItem);
			   scanItemMap.put(scanItem, ++itemCount);
		   }else {
		   scanItemMap.put(scanItem, 1);
		   }
		   calculateBill(scanItem,scanItemMap);
		   }
		   
	   }while(!scanItem.equalsIgnoreCase("exit"));
	   
	   
	   System.out.println("Scanned items are:"+scanItemMap.entrySet());
	  System.out.println("Generating Bill") ;
	  System.out.println("===============================");
	   getBill();
		
	}


	private static void calculateBill(String scanItemName, Map<String, Integer> scanItemMap) throws SQLException {
		//totalBill=0;
		Item itemData= ItemDao.getItemByName(scanItemName);
		int itemUnitPrice=itemData.getUnitPrice();
		int specialOfferCount=0;
		int specialOfferPrice=0;
		//get the special Price units count 3for20
		if(itemData.getSpecialPrice()!=null && !itemData.getSpecialPrice().isEmpty()) {
		String[] specialPriceContent= itemData.getSpecialPrice().split("for");
		specialOfferCount=Integer.parseInt(specialPriceContent[0]);
		specialOfferPrice= Integer.parseInt(specialPriceContent[1]) ;
		}
		
		//get the current count of items scanned
		int scannedCount= scanItemMap.get(scanItemName);
		
		//validate if it is matching the special price count
		if(itemData.getSpecialPrice()!=null && !itemData.getSpecialPrice().isEmpty() && scannedCount>1 && (scannedCount%specialOfferCount==0)) {
			 System.out.println("Deducting the unit prices and considering the special offer price for the item:"+scanItemName);
			for(int i=specialOfferCount; i>1; i--) {//3>1
			 totalBill-=itemUnitPrice;
			}
			
			totalBill+=specialOfferPrice;
		}else {
			totalBill+=itemUnitPrice;
		}
		
		System.out.println("Running bill amount:"+  totalBill +" pence");
		
	}



	private static void updateItem() throws Exception {
		
		
		System.out.println("Enter name of the item to be updated");
		String itemName=input.next();
		Item oldItem= ItemDao.getItemByName(itemName);
		
		if(oldItem!=null) {
			int unitPriceNew = oldItem.getUnitPrice();
			String specialPriceNew= oldItem.getSpecialPrice();
			
			System.out.println("Enter unitprice(pence) to be updated.If no change input:exit");
			
				
			
			if (!input.hasNextInt()) {
				if(!input.next().equals("exit")) {
		        //System.out.println("That's not a valid unit price!");
		        throw new Exception("That's not a valid unit price!");
				}
			}else {
				unitPriceNew= Integer.parseInt(input.next());
							
			}
			System.out.println("Enter specialprice to be updated in format 3for50.Special price should be in pence format.If no change input:exit");
			specialPriceNew=input.next();
			if(!specialPriceNew.equalsIgnoreCase("exit")){
				
				if(!specialPriceNew.contains("for")){
					throw new Exception("Invalid special price input");
				}
			String[] specialPriceContent= specialPriceNew.split("for");
			try {
			if(specialPriceContent.length==2)  {
				int specialCount= Integer.parseInt(specialPriceContent[0]);
				int specialOffer= Integer.parseInt(specialPriceContent[1]) ;
			
				
			}else {
				 throw new Exception("Invalid special price input");
			}
			
			}catch(Exception e) {
				throw new Exception(e.getMessage());
			}}else {
				specialPriceNew= oldItem.getSpecialPrice();
			}
			
			Item itemNew=new Item();
			itemNew.setItemName(itemName);
			itemNew.setUnitPrice(unitPriceNew);
			itemNew.setSpecialPrice(specialPriceNew);
			ItemDao.updateItem(itemNew);
			
			
			System.out.println("Item :"+itemName+" updated. New data:"+ itemNew.toString());
			
		}else {
			System.out.println("No item exist matching the entered name: "+ itemName);
		}
		
		
		
	}

}
