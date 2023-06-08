package domain;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private String description;
    private List<Ingredient> ingredients;
    private String preparationTime;
    private String cookingTime;
    private String instructions;
    private List<Rating> receivedRatings = new ArrayList<>();

    public Recipe(int id, String name, String description, String preparationTime, String cookingTime, String instructions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.instructions = instructions;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public List<Rating> getReceivedRatings() {
        return this.receivedRatings;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void addRating(Rating rating) {
        this.receivedRatings.add(rating);
    }

    public double getAverageRating() {
        return receivedRatings.stream().mapToInt(Rating::getStars).average().orElse(0.0);
    }
}
