package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;

public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        uomConverter = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setUnitOfMeasure(uomConverter.convert(ingredient.getUom()));
        return ingredientCommand;
    }
}
