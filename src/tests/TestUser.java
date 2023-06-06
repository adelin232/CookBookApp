package tests;

import domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUser {

    @Test
    public void testUser() {
        User user = new User("adelin232", "adelin232@cookbook.ro");

        Recipe recipe1 = new Recipe("Recipe 1", "Description 1", "0.5h", "2h", "Instructions 1");
        Recipe recipe2 = new Recipe("Recipe 2", "Description 2", "0.25h", "1h", "Instructions 2");
        user.addFavoriteRecipe(recipe1);
        user.addFavoriteRecipe(recipe2);

        Ingredient ingredient1 = new SimpleIngredient(1, "Ingredient 1", "Description 1");
        Ingredient ingredient2 = new ComplexIngredient(2, "Ingredient 2", "Description 2", null);
        user.addIngredient(ingredient1);
        user.addIngredient(ingredient2);

        assertEquals("adelin232", user.getUsername());
        assertEquals("adelin232@cookbook.ro", user.getEmail());
//        assertEquals(2, user.getFavoriteRecipes().size());
//        assertEquals(2, user.getShoppingList().size());
    }
}
