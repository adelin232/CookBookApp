package tests;

import domain.ComplexIngredient;
import domain.Ingredient;
import domain.Recipe;
import domain.SimpleIngredient;
import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUser {

    private User user;
    private Recipe recipe1;
    private Recipe recipe2;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeEach
    public void setUp() {
        user = new User("adelin232", "adelin232@cookbook.ro");

        recipe1 = new Recipe("Recipe 1", "Description 1", "0.5h", "2h", "Instructions 1");
        recipe2 = new Recipe("Recipe 2", "Description 2", "0.25h", "1h", "Instructions 2");

        ingredient1 = new SimpleIngredient(1, "Ingredient 1", "Description 1");
        ingredient2 = new ComplexIngredient(2, "Ingredient 2", "Description 2", null);

        user.addFavoriteRecipe(recipe1);
        user.addFavoriteRecipe(recipe2);

        user.addIngredient(ingredient1);
        user.addIngredient(ingredient2);
    }

    @Test
    public void testUser() {
        assertAll(
                // Test username and email
                () -> assertEquals("adelin232", user.getUsername()),
                () -> assertEquals("adelin232@cookbook.ro", user.getEmail()),

                // Test favorite recipes
                () -> assertEquals(2, user.getFavoriteRecipes().size()),
                () -> assertTrue(user.getFavoriteRecipes().contains(recipe1)),
                () -> assertTrue(user.getFavoriteRecipes().contains(recipe2)),

                // Test ingredients (assuming getShoppingList returns the ingredients)
                () -> assertEquals(2, user.getShoppingList().size()),
                () -> assertTrue(user.getShoppingList().contains(ingredient1)),
                () -> assertTrue(user.getShoppingList().contains(ingredient2))
        );

        // Test removing a favorite recipe
        user.removeFavoriteRecipe(recipe1);
        assertAll(
                () -> assertEquals(1, user.getFavoriteRecipes().size()),
                () -> assertFalse(user.getFavoriteRecipes().contains(recipe1))
        );

        // Test removing an ingredient
        user.removeIngredient(ingredient1);
        assertAll(
                () -> assertEquals(1, user.getShoppingList().size()),
                () -> assertFalse(user.getShoppingList().contains(ingredient1))
        );

        // Assume user can rate a recipe
        user.rateRecipe(recipe2, 5, "Excellent!");
        assertEquals(5, recipe2.getAverageRating());
    }
}