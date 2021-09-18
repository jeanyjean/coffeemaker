package edu.ncsu.csc326.coffeemaker;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class CucumberStepdefs {
    private CoffeeMaker coffeeMaker;

    @Given("A default coffee maker")
    public void aDefaultCoffeeMaker() {
        coffeeMaker = new CoffeeMaker();
    }

    @When("I add {int} {word} to the coffee maker")
    public void iAddCoffeeToTheCoffeeMaker(Integer int1, String str1) throws InventoryException {
        switch (str1) {
            case "coffee":
                coffeeMaker.addInventory(int1.toString(), "0", "0", "0");
                break;
            case "milk":
                coffeeMaker.addInventory("0", int1.toString(), "0", "0");
                break;
            case "sugar":
                coffeeMaker.addInventory("0", "0", int1.toString(), "0");
                break;
            case "chocolate":
                coffeeMaker.addInventory("0", "0", "0", int1.toString());
                break;
        }
    }

    @Then("Coffee maker have {int} {word}")
    public void coffeeMakerHaveCoffee(Integer int1, String str1) {
        String inventory = coffeeMaker.checkInventory();
        switch (str1) {
            case "coffee":
                assertEquals("Coffee: " + int1 + "\nMilk: 15\nSugar: 15\nChocolate: 15\n", inventory);
                break;
            case "milk":
                assertEquals("Coffee: 15\nMilk: " + int1 + "\nSugar: 15\nChocolate: 15\n", inventory);
                break;
            case "sugar":
                assertEquals("Coffee: 15\nMilk: 15\nSugar: " + int1 + "\nChocolate: 15\n", inventory);
                break;
            case "chocolate":
                assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: " + int1 + "\n", inventory);
                break;
        }
    }

    @Then("Coffee maker have {int} coffee, {int} milk, {int} sugar, {int} chocolate")
    public void coffeeMakerHaveInventory(Integer int1, Integer int2, Integer int3, Integer int4) {
        String inventory = coffeeMaker.checkInventory();
        assertEquals("Coffee: " + int1 + "\nMilk: " + int2 + "\nSugar: " + int3 + "\nChocolate: " + int4 + "\n", inventory);
    }
}
