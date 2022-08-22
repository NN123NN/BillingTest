package billing.model;

public class Item {

	private String itemName;
	private int unitPrice;
	private String specialPrice;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(String specialPrice) {
		this.specialPrice = specialPrice;
	}

	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", unitPrice=" + unitPrice + ", specialPrice=" + ((specialPrice!=null) ? specialPrice :"No special offer on this")+ "]";
	}

}
