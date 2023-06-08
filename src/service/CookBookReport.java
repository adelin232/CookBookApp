package service;

import domain.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CookBookReport {

    public int getNumberOfUsers(CookBook cookBook) {
        return cookBook.getUsers().size();
    }

    public int getNumberOfFavoriteRecipes(CookBook cookBook) {
        return cookBook.getUsers().stream().mapToInt(user -> user.getFavoriteRecipes().size()).sum();
    }

    public SortedSet<User> getUsersSorted(CookBook cookBook) {
        return cookBook.getUsers().stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getUsername))));
    }

    public int getTotalNumberOfIngredients(CookBook cookBook) {
        return cookBook.getRecipes().stream().mapToInt(recipe -> recipe.getIngredients().size()).sum();
    }

    public double getAverageRatingForRecipe(CookBook cookBook, String recipeName) {
        return cookBook.getRecipes().stream()
                .filter(recipe -> recipe.getName().equals(recipeName))
                .flatMap(recipe -> recipe.getReceivedRatings().stream())
                .mapToInt(Rating::getStars)
                .average()
                .orElse(0.0);
    }

    public Optional<Recipe> getHighestRatedRecipe(CookBook cookBook) {
        return cookBook.getRecipes().stream().max(
                Comparator.comparing(recipe -> recipe.getReceivedRatings().stream().mapToInt(Rating::getStars).average().orElse(0.0))
        );
    }

    public Optional<Ingredient> getMostUsedIngredient(CookBook cookBook) {
        return cookBook.getRecipes().stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .collect(Collectors.groupingBy(ingredient -> ingredient, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public List<Recipe> getRecipesWithIngredient(CookBook cookBook, Ingredient ingredient) {
        return cookBook.getRecipes().stream()
                .filter(recipe -> recipe.getIngredients().contains(ingredient))
                .collect(Collectors.toList());
    }

    public List<User> getUsersWhoRatedARecipe(CookBook cookBook, String recipeName) {
        return cookBook.getRecipes().stream()
                .filter(recipe -> recipe.getName().equals(recipeName))
                .flatMap(recipe -> recipe.getReceivedRatings().stream())
                .map(Rating::getUser)
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipesWithMinimumRating(CookBook cookBook, double minRating) {
        return cookBook.getRecipes().stream()
                .filter(recipe -> recipe.getReceivedRatings().stream().mapToInt(Rating::getStars).average().orElse(0.0) >= minRating)
                .collect(Collectors.toList());
    }
}
