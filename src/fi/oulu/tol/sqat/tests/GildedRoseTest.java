package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));


	//This test causes a bug because the Aged Brie (As it is written in the rules) increase the quality by 3 if the Sellin value is less then 5
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_13() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(13, itemBrie.getQuality());
	}
    
	@Test
	public void testUpdateEndOfDay_Dexterity_Vest_Quality_20_19() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals(19, itemVest.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Dexterity_Vest_SellIn_10_9() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals(9, itemVest.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_50_50() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 15, 50) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(50, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Dexterity_Vest_Quality_20_18() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 0, 20));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals(18, itemVest.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Dexterity_Vest_Quality_0_0() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 0, 0));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals(0, itemVest.getQuality());
	}
	
	
	//This test causes a bug because the Aged Brie (As it is written in the rules) increase the quality by 2 if the Sellin value is less then 10 but more then 5
	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_12() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 10, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(12, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Sulfuras_Quality_80_80() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemSulfurus = items.get(0);
		assertEquals(80, itemSulfurus.getQuality());
	}
	
	//This test causes a bug because quality of Sulfuras must be 80 in any condition
	@Test
	public void testUpdateEndOfDay_Sulfuras_Quality_2_80() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 2, 2));
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemSulfurus = items.get(0);
		assertEquals(80, itemSulfurus.getQuality());
	}
	
	//This test causes a bug because quality of Sulfuras must be 80 in any condition
	@Test
	public void testUpdateEndOfDay_Sulfuras_Quality_81_80() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 2, 81));
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemSulfurus = items.get(0);
		assertEquals(80, itemSulfurus.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Backstage_Pass_Quality_20_0() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBackstage = items.get(0);
		assertEquals(0, itemBackstage.getQuality());
	}
	
	//This test cause a bug because the quality can't be more than 50 so the application should correct our input in 50
	@Test
	public void testUpdateEndOfDay_Dexterity_Vest_SellIn_51_50() {
		//Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 55));
		
		//Act
		store.updateEndOfDay();
		
		//Assert
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals(50, itemVest.getQuality());
	}
}
