package guru.springframework.recipe.bootstrap;

import guru.springframework.recipe.domain.*;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        UnitOfMeasure eachUom = unitOfMeasureRepository.findByDescription("Each")
                .orElseThrow(() -> new RuntimeException("Expected UOM Not Found"));

        UnitOfMeasure tableSpoonUom = unitOfMeasureRepository.findByDescription("Tablespoon")
                .orElseThrow(() -> new RuntimeException("Expected UOM Not Found"));

        UnitOfMeasure teaSpoonUom = unitOfMeasureRepository.findByDescription("Teaspoon")
                .orElseThrow(() -> new RuntimeException("Expected UOM Not Found"));

        UnitOfMeasure dashUom = unitOfMeasureRepository.findByDescription("Dash")
                .orElseThrow(() -> new RuntimeException("Expected UOM Not Found"));

        UnitOfMeasure pintUom = unitOfMeasureRepository.findByDescription("Pint")
                .orElseThrow(() -> new RuntimeException("Expected UOM Not Found"));

        UnitOfMeasure cupUom = unitOfMeasureRepository.findByDescription("Cup")
                .orElseThrow(() -> new RuntimeException("Expected UOM Not Found"));

        Category americanCategory = categoryRepository.findByDescription("American")
                .orElseThrow(() -> new RuntimeException("Expected Category Not found"));

        Category mexicanCategory = categoryRepository.findByDescription("Mexican")
                .orElseThrow(() -> new RuntimeException("Expected Category Not found"));

        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("1. Cut the avocados in half. Remove the pit. Score the inside of the avocado with " +
                "a blunt knife and scoop out the flesh with a spoon. Place in a bowl."
                + System.lineSeparator() +
                "2. Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
                + System.lineSeparator() +
                "3. Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance " +
                "to the richness of the avocado and will help delay the avocados from turning brown."
                + System.lineSeparator() +
                "Add the chopped onion, cilantro, black pepper, and chilies. Chili peppers vary individually in their " +
                "hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree " +
                "of hotness."
                + System.lineSeparator() +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                "Start with this recipe and adjust to your taste."
                + System.lineSeparator() +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, " +
                "add it just before serving."
                + System.lineSeparator() +
                "Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole " +
                "and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation " +
                "which will turn the guacamole brown.) Refrigerate until ready to serve."
                + System.lineSeparator() +
                "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your" +
                " mashed avocados."
                + System.lineSeparator() +
                "Once you have basic guacamole down, feel free to experiment with variations including strawberries, " +
                "peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and " +
                "chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!");

        guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("minces red onion or thinly sliced green onion", new BigDecimal(2), eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("serrano chilies, stems and seeds remove, minced", new BigDecimal(2), eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacamoleRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacamoleRecipe.getCategories().add(americanCategory);
        guacamoleRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacamoleRecipe);

        //Yummy tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1. Prepare a gas or charcoal grill for medium-high, direct heat."
                + System.lineSeparator() +
                "2. Make the marinade and coat the chicken"
                + System.lineSeparator() +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. " +
                "Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl " +
                "and toss to coat all over. "
                + System.lineSeparator() +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings."
                + System.lineSeparator() +
                "3. Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part " +
                "of the meat registers 165F. Transfer to a plate and rest for 5 minutes."
                + System.lineSeparator() +
                "4. Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. " +
                "As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat " +
                "for a few seconds on the other side. "
                + System.lineSeparator() +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving."
                + System.lineSeparator() +
                "5. Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken" +
                " slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour " +
                "cream. Serve with lime wedges."
                + System.lineSeparator() +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/"
        );
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla."
                + System.lineSeparator() +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled " +
                "jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot " +
                "pan on the stove comes wafting through the house."
                + System.lineSeparator() +
                "Today's tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!"
                + System.lineSeparator() +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and " +
                "sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings."
                + System.lineSeparator() +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the " +
                "tacos and dig in. The whole meal comes together in about 30 minutes!"
                + System.lineSeparator() +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Chopped", new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Olive oil", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("small corn  tortillas", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slice", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;
    }
}
