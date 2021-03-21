package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository  = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(long recipeId, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException(""));
            Byte[] byteObject = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }
            recipe.setImage(byteObject);
            recipeRepository.save(recipe);
        } catch (IOException e)  {
            //todo handle exception
            log.debug("Received a file");
            e.printStackTrace();
        }


    }
}
