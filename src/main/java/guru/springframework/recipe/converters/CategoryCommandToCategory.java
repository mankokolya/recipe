package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.CategoryCommand;
import guru.springframework.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;

public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand == null) {
            return null;
        }
        final Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());
        return category;
    }
}
