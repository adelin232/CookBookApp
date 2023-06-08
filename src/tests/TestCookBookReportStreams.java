package tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.*;
import exceptions.CookBookException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CookBookReport;
import service.CookBookService;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

public class TestCookBookReportStreams {

    private CookBook cookBook;
    private CookBookReport cookBookReport;
    private User user1;
    private User user2;
    private Recipe recipe1;
    private Recipe recipe2;
    private SimpleIngredient ingredient1;
    private SimpleIngredient ingredient2;

    @BeforeEach
    public void initialize() throws CookBookException {
        cookBook = new CookBook();

        user1 = new User("alice230", "alice230@cookbook.ro");
        ingredient1 = new SimpleIngredient(1, "pasta", "wheat pasta");
        recipe1 = new Recipe(1, "Pasta", "Delicious pasta", "10min", "15min", "Cook pasta and serve");
        recipe1.addIngredient(ingredient1);
        user1.rateRecipe(recipe1, 5, "Great");
        user1.addFavoriteRecipe(recipe1);
        CookBookService.addUser(cookBook, user1);

        user2 = new User("bob231", "bob231@cookbook.ro");
        ingredient2 = new SimpleIngredient(2, "lettuce", "green lettuce");
        recipe2 = new Recipe(2, "Salad", "Healthy salad", "5min", "0min", "Mix ingredients and serve");
        recipe2.addIngredient(ingredient2);
        user2.rateRecipe(recipe2, 4, "Good");
        user2.addFavoriteRecipe(recipe2);
        CookBookService.addUser(cookBook, user2);

        CookBookService.addRecipe(cookBook, recipe1);
        CookBookService.addRecipe(cookBook, recipe2);

        cookBookReport = new CookBookReport();
    }

    @Test
    public void testCookBookReport() {
        assertAll(
                // Test number of users
                () -> assertEquals(2, cookBookReport.getNumberOfUsers(cookBook)),

                // Test number of favorite recipes
                () -> assertEquals(2, cookBookReport.getNumberOfFavoriteRecipes(cookBook)),

                // Test users sorted by username
                () -> {
                    SortedSet<User> sortedUsers = cookBookReport.getUsersSorted(cookBook);
                    assertEquals(2, sortedUsers.size());
                    assertEquals("alice230", sortedUsers.first().getUsername());
                    assertEquals("bob231", sortedUsers.last().getUsername());
                },

                // Test recipes with ingredient
                () -> {
                    List<Recipe> recipesWithPasta = cookBookReport.getRecipesWithIngredient(cookBook, ingredient1);
                    assertEquals(1, recipesWithPasta.size());
                    assertEquals("Pasta", recipesWithPasta.get(0).getName());
                },

                // Test users who rated a recipe
                () -> {
                    List<User> usersWhoRatedPasta = cookBookReport.getUsersWhoRatedARecipe(cookBook, "Pasta");
                    assertEquals(1, usersWhoRatedPasta.size());
                    assertEquals("alice230", usersWhoRatedPasta.get(0).getUsername());
                },

                // Test recipes with minimum rating
                () -> {
                    List<Recipe> recipesWithMinRating = cookBookReport.getRecipesWithMinimumRating(cookBook, 4.5);
                    assertEquals(1, recipesWithMinRating.size());
                    assertEquals("Pasta", recipesWithMinRating.get(0).getName());
                },

                // Test total number of ingredients
                () -> assertEquals(2, cookBookReport.getTotalNumberOfIngredients(cookBook)),

                // Test average rating for recipe
                () -> assertEquals(5.0, cookBookReport.getAverageRatingForRecipe(cookBook, "Pasta")),

                // Test highest rated recipe
                () -> {
                    Optional<Recipe> highestRatedRecipe = cookBookReport.getHighestRatedRecipe(cookBook);
                    assertTrue(highestRatedRecipe.isPresent());
                    assertEquals("Pasta", highestRatedRecipe.get().getName());
                },

                // Test most used ingredient
                // Test most used ingredient
                () -> {
                    Optional<Ingredient> mostUsedIngredient = cookBookReport.getMostUsedIngredient(cookBook);
                    assertTrue(mostUsedIngredient.isPresent());
                    String ingredientName = mostUsedIngredient.get().getName();
                    assertTrue("pasta".equals(ingredientName) || "lettuce".equals(ingredientName));
                }

        );
    }
}

