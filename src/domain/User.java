package domain;

import java.util.*;

public class User {

    private final String username;
    private final String email;
    private Set<Recipe> favoriteRecipes = new HashSet<>();
    private Set<Ingredient> shoppingList = new HashSet<>();
    private Set<Rating> givenRatings = new HashSet<>();

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, Set<Recipe> recipes, Set<Ingredient> ingredients, Set<Rating> ratings) {
        this.username = username;
        this.email = email;
        this.favoriteRecipes.addAll(recipes);
        this.shoppingList.addAll(ingredients);
        this.givenRatings.addAll(ratings);
    }

    public void addFavoriteRecipe(final Recipe recipe) {
        favoriteRecipes.add(recipe);
    }

    public void addIngredient(final Ingredient ingredient) {
        shoppingList.add(ingredient);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Set<Recipe> getFavoriteRecipes() {
        return Collections.unmodifiableSet(favoriteRecipes);
    }

    public Set<Ingredient> getShoppingList() {
        return Collections.unmodifiableSet(shoppingList);
    }

    public Set<Rating> getGivenRatings() {
        return Collections.unmodifiableSet(givenRatings);
    }

    public void rateRecipe(Recipe recipe, int stars, String comment) {
        Rating rating = new Rating(this, recipe, stars);
        rating.setComment(comment);
        givenRatings.add(rating);
        recipe.addRating(rating);
    }
}
