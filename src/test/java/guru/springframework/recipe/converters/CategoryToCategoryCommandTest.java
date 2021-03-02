package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.CategoryCommand;
import guru.springframework.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryToCategoryCommandTest {
    public static final Long ID_VALUE  = 1L;
    public static final String DESCRIPTION = "description";

    CategoryToCategoryCommand converter;

    @Before
    public void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObj() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void testConvert() {
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryCommand categoryCommand = converter.convert(category);

        assertNotNull(category);
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }

}
