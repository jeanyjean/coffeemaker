package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for Inventory class.
 *
 * @author Purich Trainorapong
 */
public class InventoryTest {

    /**
     * The object under test.
     */
    private Inventory inventory;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;

    /**
     * Initializes some recipes to test with and the {@link Inventory}
     * object we wish to test.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        inventory = new Inventory();

        //Set up for r1
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Set up for r2
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");
    }

    /**
     * Test the setChocolate method with positive and negative number as a value.
     */
    @Test
    public void testSetChocolate() {
        inventory.setChocolate(10);
        assertEquals(10,inventory.getChocolate());
        inventory.setChocolate(-10);
        assertEquals(10,inventory.getChocolate());
    }

    /**
     * Test the addChocolate method with string as a value.
     * @throws InventoryException if there was an error parsing the quantity
     *                             to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddChocolateWithString() throws InventoryException {
        inventory.addChocolate("ten");
    }

    /**
     * Test the addChocolate method with negative number as a value.
     * @throws InventoryException if there was an error parsing the quantity
     *                             to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddChocolateWithNegativeNumber() throws InventoryException {
        inventory.addChocolate("-20");
    }

    /**
     * Test the setCoffee method with positive and negative number as a value.
     */
    @Test
    public void testSetCoffee() {
        inventory.setCoffee(20);
        assertEquals(20,inventory.getCoffee());
        inventory.setCoffee(-10);
        assertEquals(20,inventory.getCoffee());
    }

    /**
     * Test the addCoffee method with string as a value.
     * @throws InventoryException if there was an error parsing the quantity
     *                             to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddCoffeeWithString() throws InventoryException {
        inventory.addCoffee("five");
    }

    /**
     * Test the setMilk method with positive and negative number as a value.
     */
    @Test
    public void testSetMilk() {
        inventory.setMilk(15);
        assertEquals(15,inventory.getMilk());
        inventory.setMilk(-15);
        assertEquals(15,inventory.getMilk());
    }

    /**
     * Test the addMilk method with string as a value.
     * @throws InventoryException if there was an error parsing the quantity
     *                             to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddMilkWithString() throws InventoryException {
        inventory.addMilk("six");
    }

    /**
     * Test the setSugar method with positive and negative number as a value.
     */
    @Test
    public void testSetSugar() {
        inventory.setSugar(30);
        assertEquals(30,inventory.getSugar());
        inventory.setSugar(-30);
        assertEquals(30,inventory.getSugar());
    }

    /**
     * Test the addSugar method with string as a value.
     * @throws InventoryException if there was an error parsing the quantity
     *                             to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddSugarWithString() throws InventoryException {
        inventory.addSugar("seven");
    }

    /**
     * Test the enoughIngredients when inventory do not have enough coffee.
     */
    @Test
    public void testEnoughIngredientsWithNotEnoughCoffee() {
        inventory.setCoffee(2);
        assertFalse(inventory.enoughIngredients(recipe1));
    }

    /**
     * Test the enoughIngredients when inventory do not have enough milk.
     */
    @Test
    public void testEnoughIngredientsWithNotEnoughMilk() {
        inventory.setMilk(0);
        assertFalse(inventory.enoughIngredients(recipe1));
    }

    /**
     * Test the enoughIngredients when inventory do not have enough sugar.
     */
    @Test
    public void testEnoughIngredientsWithNotEnoughSugar() {
        inventory.setSugar(0);
        assertFalse(inventory.enoughIngredients(recipe2));
    }

    /**
     * Test the enoughIngredients when inventory do not have enough chocolate.
     */
    @Test
    public void testEnoughIngredientsWithNotEnoughChocolate() {
        inventory.setChocolate(10);
        assertFalse(inventory.enoughIngredients(recipe2));
    }
}
