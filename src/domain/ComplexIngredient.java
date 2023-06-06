package domain;

import java.util.Collections;
import java.util.List;

public class ComplexIngredient extends AbstractIngredient {

    private List<Ingredient> subIngredients;

    public ComplexIngredient(int id, String name, String description, List<Ingredient> subIngredients) {
        super(id, name, description);
        this.subIngredients = subIngredients;
    }

    public List<Ingredient> getSubIngredients() {
        return Collections.unmodifiableList(subIngredients);
    }
}
