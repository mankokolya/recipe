package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    IngredientToIngredientCommand ingredientToIngredientCommand;
    NoteToNoteCommand notesConverter;
    CategoryToCategoryCommand categoryConverter;


    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
                                 NoteToNoteCommand notesConverter, CategoryToCategoryCommand categoryConverter) {
        ingredientToIngredientCommand = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }
    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setIngredients(recipe.getIngredients().stream()
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .collect(Collectors.toSet()));
        recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));
        recipeCommand.setCategories(recipe.getCategories().stream()
                .map(category -> categoryConverter.convert(category))
                .collect(Collectors.toSet()));

        return recipeCommand;
    }
}
