package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "Cheeseburger";
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final Long UOM_ID = 2L;

    IngredientCommandToIngredient converter;
    UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @Before
    public void setUp() {
        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
        converter = new IngredientCommandToIngredient(uomConverter);
    }

    @Test
    public void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void testConverter() {
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        command.setUnitOfMeasure(uomCommand);

        Ingredient ingredient = converter.convert(command);

        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }
}