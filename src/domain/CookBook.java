package domain;

import email.Email;
import email.EmailException;
import email.EmailService;
import exceptions.RecipeExistsException;
import exceptions.UserExistsException;
import utils.UserRegistrationListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CookBook {

    private final List<User> users = new ArrayList<>();
    private final Set<Recipe> recipes = new HashSet<>();
    private final List<UserRegistrationListener> listeners = new ArrayList<>();
    private EmailService emailService;

    private final User admin = new User("admin", "admin@cookbook.ro");

    private final User system = new User("system", "system@cookbook.ro");

    public CookBook() {
        listeners.add(user -> System.out.println("User added: " + user.getUsername()));
        listeners.add(user -> {
            System.out.println("Notification email for user " + user.getUsername() + " to be sent");

            if (emailService != null) {
                try {
                    emailService.sendNotificationEmail(
                            new Email()
                                    .setFrom(system)
                                    .setTo(List.of(admin))
                                    .setTitle("User Added Notification")
                                    .setBody("User added: " + user)
                    );
                } catch (EmailException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        listeners.add(user -> System.out.println("User " + user.getUsername() + " added on: "
                + DateFormat.getDateInstance(DateFormat.FULL).format(new Date())));
    }

    private void notify(User user) {
        for (UserRegistrationListener listener : listeners) {
            listener.onUserAdded(user);
        }
    }

    public void addUser(final User user) throws UserExistsException {
        if (users.contains(user)) {
            throw new UserExistsException("User already exists in the cook book");
        }

        users.add(user);
        notify(user);
    }

    public List<User> getUsers() {
        return this.users;
    }

    public User getUser(String username) {
        for (User user : users)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    public void addRecipe(final Recipe recipe) throws RecipeExistsException {
        if (recipes.contains(recipe)) {
            throw new RecipeExistsException("Recipe already exists in the cook book");
        }

        recipes.add(recipe);
    }

    public Recipe getRecipe(String name) {
        for (Recipe recipe : recipes)
            if (recipe.getName().equals(name))
                return recipe;
        return null;
    }

    public Set<Recipe> getRecipes() {
        return this.recipes;
    }
}
