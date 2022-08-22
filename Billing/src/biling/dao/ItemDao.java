package biling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import billing.model.Item;

public class ItemDao {
	private static Connection con = null;
	private static Statement statement = null;

	public static void getAllItems() throws SQLException {
		try (Connection con = ConnectionFactory.connectDB()) {
			statement = con.createStatement();
			String query = "SELECT * FROM item";
			ResultSet rs = statement.executeQuery(query);
			List<Item> itemList = new ArrayList();
			while (rs.next()) {
				Item item = new Item();
				String itemName = rs.getString("item_name");
				int unitPrice = rs.getInt("unit_price");
				String specialPrice = rs.getString("special_price");

				item.setItemName(itemName);
				item.setUnitPrice(unitPrice);
				item.setSpecialPrice(specialPrice);
				itemList.add(item);

			}

			// print the results
			itemList.stream().forEach(p -> System.out.println(p));
		} catch (SQLException e) {
			throw e;
		}
	}

	public static Item getItemByName(String name) throws SQLException {
		try (Connection con = ConnectionFactory.connectDB()) {
			statement = con.createStatement();
			String query = "SELECT * FROM item WHERE item_name='" + name + "'";
			ResultSet rs = statement.executeQuery(query);
			Item item = null;
			while (rs.next()) {
				String itemName = rs.getString("item_name");
				int unitPrice = rs.getInt("unit_price");
				String specialPrice = rs.getString("special_price");
				item = new Item();
				item.setItemName(itemName);
				item.setUnitPrice(unitPrice);
				item.setSpecialPrice(specialPrice);
			}
			if (item != null) {
				// System.out.println("Item found with name:"+name+" Item:"+item);
				return item;
			} else {
				System.out.println("No item found with name:" + name);
				return null;
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;
		}

	}

	public static void updateItem(Item item) throws SQLException {
		try (Connection con = ConnectionFactory.connectDB()) {
			Item itemOld = getItemByName(item.getItemName());
			if (itemOld != null) {
				String query = "UPDATE item set unit_price=?,special_price=? where item_name=?";
				PreparedStatement prepstatement = con.prepareStatement(query);

				prepstatement.setInt(1, item.getUnitPrice());
				prepstatement.setString(2, item.getSpecialPrice());

				prepstatement.setString(3, item.getItemName());
				prepstatement.executeUpdate();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

}
