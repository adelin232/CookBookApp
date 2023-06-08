package service;

import domain.CookBook;
import domain.Recipe;
import domain.User;
import exceptions.RecipeExistsException;
import exceptions.UserExistsException;

public class CookBookService {

    public static void addUser(CookBook cookBook, User user) throws UserExistsException {
        cookBook.addUser(user);
    }

    public static User getUser(CookBook cookBook, String username) {
        return cookBook.getUser(username);
    }

    public static void addRecipe(CookBook cookBook, Recipe recipe) throws RecipeExistsException {
        cookBook.addRecipe(recipe);
    }

    public static Recipe getRecipe(CookBook cookBook, String name) {
        return cookBook.getRecipe(name);
    }
}
