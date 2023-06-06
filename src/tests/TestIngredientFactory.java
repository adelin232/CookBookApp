package tests;

import domain.ComplexIngredient;
import domain.Ingredient;
import domain.SimpleIngredient;
import factory.IngredientFactory;
import factory.IngredientType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestIngredientFactory {

    @Test
    public void testSimpleIngredient() {
        Ingredient simpleIngredient = IngredientFactory.createNewIngredient(IngredientType.SIMPLE, 1, "Simple", "Description 1");
        assertInstanceOf(SimpleIngredient.class, simpleIngredient);
    }

    @Test
    public void testComplexIngredient() {
        Ingredient complexIngredient = IngredientFactory.createNewIngredient(IngredientType.COMPLEX, 2, "Complex", "Description 2");
        assertInstanceOf(ComplexIngredient.class, complexIngredient);
    }
}
