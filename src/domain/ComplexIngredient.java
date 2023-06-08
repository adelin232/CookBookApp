package domain;

import java.util.List;

public class ComplexIngredient extends AbstractIngredient {

    private List<Ingredient> subIngredients;

    public ComplexIngredient(int id, String name, String description, List<Ingredient> subIngredients) {
        super(id, name, description);
        this.subIngredients = subIngredients;
    }

    public void addSubIngredient(Ingredient ingredient) {
        this.subIngredients.add(ingredient);
    }

    public List<Ingredient> getSubIngredients() {
        return this.subIngredients;
    }
}
