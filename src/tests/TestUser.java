package tests;

import domain.ComplexIngredient;
import domain.Ingredient;
import domain.Recipe;
import domain.SimpleIngredient;
import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUser {

    private User user;
    private Recipe chocolateCake;
    private Recipe italianPasta;
    private Ingredient cocoaPowder;
    private Ingredient basil;

    @BeforeEach
    public void setUp() {
        user = new User("adelin232", "adelin232@cookbook.ro");

        chocolateCake = new Recipe(1, "Chocolate Cake", "A delicious and rich chocolate cake", "20 minutes", "35 minutes", "Mix ingredients, bake at 350°F for 35 minutes.");
        italianPasta = new Recipe(2, "Italian Pasta", "Classic Italian pasta with a rich tomato sauce", "10 minutes", "20 minutes", "Cook pasta, prepare sauce, mix and serve.");

        cocoaPowder = new SimpleIngredient(1, "Cocoa Powder", "Used in cakes, cookies and desserts");
        basil = new ComplexIngredient(2, "Basil", "Aromatic herb used in Italian cuisine", null);

        user.addFavoriteRecipe(chocolateCake);
        user.addFavoriteRecipe(italianPasta);

        user.addIngredient(cocoaPowder);
        user.addIngredient(basil);
    }

    @Test
    public void testUser() {
        assertAll(
                // Test username and email
                () -> assertEquals("adelin232", user.getUsername()),
                () -> assertEquals("adelin232@cookbook.ro", user.getEmail()),

                // Test favorite recipes
                () -> assertEquals(2, user.getFavoriteRecipes().size()),
                () -> assertTrue(user.getFavoriteRecipes().contains(chocolateCake)),
                () -> assertTrue(user.getFavoriteRecipes().contains(italianPasta)),

                // Test ingredients (assuming getShoppingList returns the ingredients)
                () -> assertEquals(2, user.getShoppingList().size()),
                () -> assertTrue(user.getShoppingList().contains(cocoaPowder)),
                () -> assertTrue(user.getShoppingList().contains(basil))
        );

        // Test removing a favorite recipe
        user.removeFavoriteRecipe(chocolateCake);
        assertAll(
                () -> assertEquals(1, user.getFavoriteRecipes().size()),
                () -> assertFalse(user.getFavoriteRecipes().contains(chocolateCake))
        );

        // Test removing an ingredient
        user.removeIngredient(cocoaPowder);
        assertAll(
                () -> assertEquals(1, user.getShoppingList().size()),
                () -> assertFalse(user.getShoppingList().contains(cocoaPowder))
        );

        // Assume user can rate a recipe
        user.rateRecipe(italianPasta, 5, "Absolutely delicious, felt like I was in Italy!");
        assertEquals(5, italianPasta.getAverageRating());
    }
}
