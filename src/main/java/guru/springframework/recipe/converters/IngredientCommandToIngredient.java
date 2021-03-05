package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;

public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure  uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter ;
    }

    @Override
    public Ingredient convert(IngredientCommand command) {
        if (command == null) {
            return null;
        }
        Ingredient ingredient = new Ingredient();
        ingredient.setId(command.getId());
        ingredient.setDescription(command.getDescription());
        ingredient.setAmount(command.getAmount());
        ingredient.setUom(uomConverter.convert(command.getUnitOfMeasure()));

        return ingredient;

    }
}
