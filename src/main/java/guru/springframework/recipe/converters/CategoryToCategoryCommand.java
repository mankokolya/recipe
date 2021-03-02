package guru.springframework.recipe.converters;

import guru.springframework.recipe.commands.CategoryCommand;
import guru.springframework.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;

public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) {
            return null;
        }
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());
        return categoryCommand;
    }
}
