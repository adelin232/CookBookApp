package tests;

import domain.Ingredient;
import domain.Rating;
import domain.Recipe;
import domain.SimpleIngredient;
import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRecipe {

    private Recipe recipe;
    private User user;

    @BeforeEach
    public void setup() {
        recipe = new Recipe(1, "Pasta", "Delicious Pasta", "10min", "15min", "Boil pasta, add sauce, serve.");
        user = new User("john_doe", "john.doe@example.com");
    }

    @Test
    public void testRecipeName() {
        assertEquals("Pasta", recipe.getName());
    }

    @Test
    public void testRecipeDescription() {
        assertEquals("Delicious Pasta", recipe.getDescription());
    }

    @Test
    public void testRecipePreparationTime() {
        assertEquals("10min", recipe.getPreparationTime());
    }

    @Test
    public void testRecipeCookingTime() {
        assertEquals("15min", recipe.getCookingTime());
    }

    @Test
    public void testRecipeInstructions() {
        assertEquals("Boil pasta, add sauce, serve.", recipe.getInstructions());
    }

    @Test
    public void testAddIngredient() {
        Ingredient ingredient = new SimpleIngredient(1, "Pasta", "Wheat pasta");
        recipe.addIngredient(ingredient);
        assertTrue(recipe.getIngredients().contains(ingredient));
    }

    @Test
    public void testAddRating() {
        int stars = 5;
        String comment = "Delicious!";
        Rating rating = new Rating(user, recipe, stars);
        rating.setComment(comment);
        recipe.addRating(rating);

        assertTrue(recipe.getReceivedRatings().contains(rating));
    }
}
