package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recipe {

    private String name;
    private String description;
    private List<Ingredient> ingredients;
    private String preparationTime;
    private String cookingTime;
    private String instructions;
    private List<Rating> receivedRatings = new ArrayList<>();

    public Recipe(String name, String description, String preparationTime, String cookingTime, String instructions) {
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
        return Collections.unmodifiableList(ingredients);
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
