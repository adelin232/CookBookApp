package tests;

import domain.ComplexIngredient;
import domain.Ingredient;
import domain.SimpleIngredient;
import factory.IngredientFactory;
import factory.IngredientType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIngredientFactory {

    @Test
    public void testSimpleIngredient() {
        Ingredient garlic = IngredientFactory.createNewIngredient(IngredientType.SIMPLE, 1, "Garlic", "Fresh garlic cloves");
        assertInstanceOf(SimpleIngredient.class, garlic);
        assertEquals(1, garlic.getId());
        assertEquals("Garlic", garlic.getName());
        assertEquals("Fresh garlic cloves", garlic.getDescription());
    }

    @Test
    public void testComplexIngredient() {
        Ingredient marinaraSauce = IngredientFactory.createNewIngredient(IngredientType.COMPLEX, 2, "Marinara Sauce", "A classic Italian tomato sauce");
        assertInstanceOf(ComplexIngredient.class, marinaraSauce);
        assertEquals(2, marinaraSauce.getId());
        assertEquals("Marinara Sauce", marinaraSauce.getName());
        assertEquals("A classic Italian tomato sauce", marinaraSauce.getDescription());
    }

    @Test
    public void testComplexIngredientWithSubIngredients() {
        Ingredient pizzaTopping = IngredientFactory.createNewIngredient(IngredientType.COMPLEX, 3, "Pizza Topping", "Deluxe pizza topping with various ingredients");
        assertInstanceOf(ComplexIngredient.class, pizzaTopping);

        // Manually add sub-ingredients after creation
        ComplexIngredient castedPizzaTopping = (ComplexIngredient) pizzaTopping;
        Ingredient pepperoni = new SimpleIngredient(4, "Pepperoni", "Spicy Italian salami slices");
        Ingredient mushrooms = new SimpleIngredient(5, "Mushrooms", "Freshly sliced mushrooms");
        castedPizzaTopping.addSubIngredient(pepperoni);
        castedPizzaTopping.addSubIngredient(mushrooms);

        // Check if the ComplexIngredient contains the sub-ingredients
        List<Ingredient> subIngredients = castedPizzaTopping.getSubIngredients();
        assertTrue(subIngredients.contains(pepperoni));
        assertTrue(subIngredients.contains(mushrooms));
    }
}
