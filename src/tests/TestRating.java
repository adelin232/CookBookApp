package tests;

import domain.Rating;
import domain.Recipe;
import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRating {

    private User user;
    private Recipe recipe;
    private Rating rating;

    @BeforeEach
    public void initialize() {
        user = new User("john_doe", "john.doe@example.com");
        recipe = new Recipe("Pizza", "Delicious pizza", "15min", "30min", "Bake the pizza");
        rating = new Rating(user, recipe, 5);
    }

    @Test
    public void testGetUser() {
        assertEquals(user, rating.getUser(), "Expected the same user to be returned");
    }

    @Test
    public void testGetRecipe() {
        assertEquals(recipe, rating.getRecipe(), "Expected the same recipe to be returned");
    }

    @Test
    public void testGetStars() {
        assertEquals(5, rating.getStars(), "Expected 5 stars to be returned");
    }
}
