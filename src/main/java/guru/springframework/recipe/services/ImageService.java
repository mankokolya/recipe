package guru.springframework.recipe.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {
    void saveImageFile(long recipeId, MultipartFile file);
}
