package com.rafa.recipehub.services;

import com.rafa.recipehub.exception.RecipeNotFoundException;
import com.rafa.recipehub.model.Recipe;
import com.rafa.recipehub.model.User;
import com.rafa.recipehub.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class RecipeServiceImplementation implements RecipeServices {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe createdRecipe = new Recipe();
        createdRecipe.setTitle(recipe.getTitle());
        createdRecipe.setImage(recipe.getImage());
        createdRecipe.setDescription(recipe.getDescription());
        createdRecipe.setUser(user);
        createdRecipe.setCreatedAt(LocalDateTime.now());
        return recipeRepository.save(createdRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id " + id));
    }

    @Override
    public void deleteRecipe(Long id) {
        Recipe recipe = findRecipeById(id);
        recipeRepository.delete(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) {
        Recipe oldRecipe = findRecipeById(id);

        if (recipe.getTitle() != null) {
            oldRecipe.setTitle(recipe.getTitle());
        }
        if (recipe.getImage() != null) {
            oldRecipe.setImage(recipe.getImage());
        }
        if (recipe.getDescription() != null) {
            oldRecipe.setDescription(recipe.getDescription());
        }
        return recipeRepository.save(oldRecipe);
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) {
        Recipe recipe = findRecipeById(recipeId);
        if (recipe.getLikes().contains(user.getId())) {
            recipe.getLikes().remove(user.getId());
        } else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe); // Save and return the updated recipe
    }
}
