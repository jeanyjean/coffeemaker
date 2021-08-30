/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 *
 * Modifications
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 *
 * @author Purich Trainorapong
 */
public class CoffeeMakerTest {

    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

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

        //Set up for r3
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Set up for r4
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtChocolate("4");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");
    }


    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with well-formed quantities
     * Then we do not get an exception trying to read the inventory quantities.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("4", "7", "0", "9");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryException() throws InventoryException {
        coffeeMaker.addInventory("4", "-1", "asdf", "3");
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the valid recipe and paying more than
     * the coffee costs
     * Then we get the correct change back.
     */
    @Test
    public void testMakeCoffee() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }

    /**
     * Test if coffee maker can add a recipe successfully.
     */
    @Test
    public void testAddOneRecipe() {
        coffeeMaker.addRecipe(recipe1);
    }

    /**
     * Test if coffee maker won't be able to add more than three recipes.
     */
    @Test
    public void testAddRecipeMoreThanThree() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        boolean result = coffeeMaker.addRecipe(recipe4);
        assertFalse(result);
    }

    /**
     * Test if coffee maker can add a recipe with a non integer price.
     *
     * @throws RecipeException if there was an error parsing the value
     *                         to a positive integer.
     */
    @Test(expected = RecipeException.class)
    public void testAddNewRecipeWithNonIntegerPrice() throws RecipeException {
        Recipe recipe5 = new Recipe();
        recipe5.setName("Test Drink");
        recipe5.setAmtChocolate("5");
        recipe5.setAmtCoffee("2");
        recipe5.setAmtMilk("3");
        recipe5.setAmtSugar("3");
        recipe5.setPrice("wow");
    }

    /**
     * Test if coffee maker won't be able to add recipe with the same name
     */
    @Test
    public void testAddNewRecipeWithSameName() {
        coffeeMaker.addRecipe(recipe1);
        boolean result = coffeeMaker.addRecipe(recipe1);
        assertFalse(result);
    }

    /**
     * Test if coffee maker can delete a recipe.
     */
    @Test
    public void testDeleteRecipe() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        String deletedRecipe = coffeeMaker.deleteRecipe(1);
        Recipe[] recipesList = coffeeMaker.getRecipes();
        assertNull(recipesList[1]);
    }

    /**
     * Test that coffee maker won't be able to delete a recipe at index that is out of range.
     */
    @Test
    public void testDeleteRecipeOutOfRange() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        String deletedRecipe = coffeeMaker.deleteRecipe(5);
        assertNull(deletedRecipe);
    }

    /**
     * Test that coffee maker won't be able to delete a recipe at index that is a negative number.
     */
    @Test
    public void testDeleteRecipeAtNegativeNumber() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        String deletedRecipe = coffeeMaker.deleteRecipe(-1);
        assertNull(deletedRecipe);
    }

    /**
     * Test that recipe's name won't be change when editing a recipe.
     *
     * @throws RecipeException if there was an error parsing the value
     *                         to a positive integer.
     */
    @Test
    public void testEditRecipeNameMustNotChange() throws RecipeException {
        coffeeMaker.addRecipe(recipe1);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Coffee");
        newRecipe.setAmtChocolate("2");
        newRecipe.setAmtCoffee("1");
        newRecipe.setAmtMilk("2");
        newRecipe.setAmtSugar("3");
        newRecipe.setPrice("60");
        Recipe[] recipesList = coffeeMaker.getRecipes();

        assertEquals(recipe1.getName(), coffeeMaker.editRecipe(0, newRecipe));
        assertEquals(recipe1.getName(), recipesList[0].getName());
    }

    /**
     * Test that coffee maker won't be able to edit a recipe at index that is out of range.
     *
     * @throws RecipeException if there was an error parsing the value
     *                         to a positive integer.
     */
    @Test
    public void testEditRecipeOutOfRange() throws RecipeException {
        coffeeMaker.addRecipe(recipe1);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Coffee");
        newRecipe.setAmtChocolate("2");
        newRecipe.setAmtCoffee("1");
        newRecipe.setAmtMilk("2");
        newRecipe.setAmtSugar("3");
        newRecipe.setPrice("60");

        String edittedRecipe = coffeeMaker.editRecipe(5, newRecipe);
        assertNull(edittedRecipe);
    }

    /**
     * Test that coffee maker won't be able to edit a recipe at index that is a negative number.
     *
     * @throws RecipeException if there was an error parsing the value
     *                         to a positive integer.
     */
    @Test
    public void testEditRecipeAtNegativeNumber() throws RecipeException {
        coffeeMaker.addRecipe(recipe1);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Coffee");
        newRecipe.setAmtChocolate("2");
        newRecipe.setAmtCoffee("1");
        newRecipe.setAmtMilk("2");
        newRecipe.setAmtSugar("3");
        newRecipe.setPrice("60");

        String edittedRecipe = coffeeMaker.editRecipe(-1, newRecipe);
        assertNull(edittedRecipe);
    }

    /**
     * Test if coffee maker can add to the inventory with a positive number.
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                             to a positive integer.
     */
    @Test
    public void testAddInventoryPositiveNumber() throws InventoryException {
        coffeeMaker.addInventory("0", "7", "10", "9");
        coffeeMaker.addInventory("4", "0", "10", "9");
        coffeeMaker.addInventory("4", "7", "0", "9");
        coffeeMaker.addInventory("4", "7", "10", "0");
    }

    /**
     * Test if coffee maker can add to the inventory with a negative number.
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryNegativeNumber() throws InventoryException {
        coffeeMaker.addInventory("-4", "-7", "-10", "-9");
        coffeeMaker.addInventory("-4", "7", "10", "9");
        coffeeMaker.addInventory("4", "-7", "10", "9");
        coffeeMaker.addInventory("4", "7", "-10", "9");
        coffeeMaker.addInventory("4", "7", "10", "-9");
    }

    /**
     * Test if the inventory called from checkinventory() will be updated after addinventory() is called.
     *
     * @throws InventoryException if there was an error parsing the quantity
     *                            to a positive integer.
     */
    @Test
    public void testCheckInventory() throws InventoryException {
        coffeeMaker.addInventory("4", "0", "0", "0");
        String inventory = coffeeMaker.checkInventory();
        assertEquals("Coffee: 19\nMilk: 15\nSugar: 15\nChocolate: 15\n", inventory);

        coffeeMaker.addInventory("0", "7", "0", "0");
        inventory = coffeeMaker.checkInventory();
        assertEquals("Coffee: 19\nMilk: 22\nSugar: 15\nChocolate: 15\n", inventory);

        coffeeMaker.addInventory("0", "0", "3", "0");
        inventory = coffeeMaker.checkInventory();
        assertEquals("Coffee: 19\nMilk: 22\nSugar: 18\nChocolate: 15\n", inventory);

        coffeeMaker.addInventory("0", "0", "0", "9");
        inventory = coffeeMaker.checkInventory();
        assertEquals("Coffee: 19\nMilk: 22\nSugar: 18\nChocolate: 24\n", inventory);
    }

    /**
     * Test the amount of money return from the purchase beverage method with enough money to buy a beverage.
     */
    @Test
    public void testPurchaseBeverageWithEnoughMoney() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        int change = coffeeMaker.makeCoffee(0, 50);
        assertEquals(0, change);
    }

    /**
     * Test the amount of money return from the purchase beverage method with more than enough money to buy a beverage.
     */
    @Test
    public void testPurchaseBeverageWithMoreThanEnoughMoney() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        int change = coffeeMaker.makeCoffee(0, 70);
        assertEquals(20, change);
    }

    /**
     * Test the amount of money return from the purchase beverage method with less than enough money to buy a beverage.
     */
    @Test
    public void testPurchaseBeverageWithNotEnoughMoney() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        int change = coffeeMaker.makeCoffee(0, 30);
        assertEquals(30, change);
    }

    /**
     * Test the inventory of the coffee maker after a successful purchase.
     */
    @Test
    public void testPurchaseBeverageInventoryWithEnoughMoney() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        int change = coffeeMaker.makeCoffee(0, 50);
        String inventory = coffeeMaker.checkInventory();
        assertEquals("Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n", inventory);
    }

    /**
     * Test the inventory of the coffee maker after an unsuccessful purchase.
     */
    @Test
    public void testPurchaseBeverageInventoryWithNotEnoughMoney() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        int change = coffeeMaker.makeCoffee(0, 30);
        String inventory = coffeeMaker.checkInventory();
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", inventory);
    }
}
