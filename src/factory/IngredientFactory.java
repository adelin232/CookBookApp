package factory;

import domain.ComplexIngredient;
import domain.Ingredient;
import domain.SimpleIngredient;

import java.util.ArrayList;

public class IngredientFactory {

    public static Ingredient createNewIngredient(IngredientType ingredientType, int id, String name, String description) {
        if (ingredientType == null) {
            return null;
        }

        return switch (ingredientType) {
            case SIMPLE -> new SimpleIngredient(id, name, description);
            case COMPLEX -> new ComplexIngredient(id, name, description, new ArrayList<>());
        };
    }
}
