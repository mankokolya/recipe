package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.IngredientCommand;
import org.springframework.stereotype.Service;

@Service
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
