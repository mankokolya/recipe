package guru.springframework.recipe.services;

import guru.springframework.recipe.converters.*;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl recipeService;

    @Before
    public void setUp() {
        recipeRepository = Mockito.mock(RecipeRepository.class);
        recipeService = new RecipeServiceImpl(recipeRepository,
                new RecipeCommandToRecipe(new NoteCommandToNote(),
                        new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                        new CategoryCommandToCategory()),
                new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                        new NoteToNoteCommand(), new CategoryToCategoryCommand()));
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> recipeSet = recipeService.getRecipes();
        assertEquals(recipeSet.size(),1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);
        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void testDeleteRecipeById() {
        long idToDelete = 2L;
        recipeService.deleteById(idToDelete);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}