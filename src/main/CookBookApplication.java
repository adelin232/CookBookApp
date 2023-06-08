package main;

import domain.*;
import exceptions.RecipeExistsException;
import exceptions.UserExistsException;
import service.CookBookReport;

import java.util.Optional;

public class CookBookApplication {

    private static CookBook cookBook;

    public static void main(String[] args) throws RecipeExistsException, UserExistsException {
        cookBook = new CookBook();
        modifyCookBook();
        printRecipes();
        printCookBookStatistics(cookBook);
    }

    private static void modifyCookBook() throws RecipeExistsException, UserExistsException {
        // Creating ingredients
        Ingredient flour = new SimpleIngredient(1, "Flour", "Wheat Flour");
        Ingredient sugar = new SimpleIngredient(2, "Sugar", "White Sugar");
        Ingredient chocolate = new SimpleIngredient(3, "Chocolate", "Dark Chocolate");

        // Creating recipes
        Recipe cake = new Recipe(1, "Cake", "Delicious Cake", "15 minutes", "1 hour", "Bake at 350 degrees for 1 hour.");
        Recipe chocolateCake = new Recipe(2, "Chocolate Cake", "Delicious Chocolate Cake", "20 minutes", "1 hour 10 minutes", "Bake at 350 degrees for 1 hour and 10 minutes.");

        // Adding ingredients to the recipes
        cake.addIngredient(flour);
        cake.addIngredient(sugar);

        chocolateCake.addIngredient(flour);
        chocolateCake.addIngredient(sugar);
        chocolateCake.addIngredient(chocolate);

        // Creating a user
        User user = new User("JohnDoe", "johndoe@example.com");

        // Adding favorite recipes to the user
        user.addFavoriteRecipe(cake);
        user.addFavoriteRecipe(chocolateCake);

        // Rating the recipes
        user.rateRecipe(cake, 5, "Loved it!");
        user.rateRecipe(chocolateCake, 4, "Really good!");

        // Adding recipes to the cookbook
        cookBook.addRecipe(cake);
        cookBook.addRecipe(chocolateCake);

        // Adding user to the cookbook
        cookBook.addUser(user);
    }

    private static void printRecipes() {
        System.out.println("\nPrint Recipes in CookBook");
        for (Recipe recipe : cookBook.getRecipes()) {
            System.out.println("Recipe: " + recipe.getName());
            System.out.println("Description: " + recipe.getDescription());
            System.out.print("Ingredients: ");
            for (Ingredient ingredient : recipe.getIngredients()) {
                System.out.print(ingredient.getName() + ", ");
            }
            System.out.println("\nRatings: ");
            for (Rating rating : recipe.getReceivedRatings()) {
                System.out.println("User: " + rating.getUser().getUsername() + " - Rating: " + rating.getStars() + " - Comment: " + rating.getComment());
            }
            System.out.println();
        }
    }

    private static void printCookBookStatistics(CookBook cookBook) {
        CookBookReport cookBookReport = new CookBookReport();
        System.out.println("\nCookBook Statistics:");
        System.out.println("Number of Recipes: " + CookBookReport.getNumberOfRecipes(cookBook));
        System.out.println("Number of Users: " + cookBookReport.getNumberOfUsers(cookBook));
        Optional<Recipe> highestRatedRecipe = cookBookReport.getHighestRatedRecipe(cookBook);
        if (highestRatedRecipe.isPresent()) {
            System.out.println("Highest Rated Recipe: " + highestRatedRecipe.get().getName());
        } else {
            System.out.println("No recipes in the cookbook.");
        }
    }
}
