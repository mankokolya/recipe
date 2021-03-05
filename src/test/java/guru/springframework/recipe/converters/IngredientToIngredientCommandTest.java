package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {
    private static final Long ID = 1L;
    private static String DESCRIPTION = "avocado";
    private static BigDecimal AMOUNT = new BigDecimal("1");
    private static Long UOM_ID = 2L;

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }


    @Test
    public void testConvert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
    }

    @Test
    public void testConverterWithNullUom() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(null);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
    }
}