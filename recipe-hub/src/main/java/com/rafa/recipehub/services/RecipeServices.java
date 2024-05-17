package com.rafa.recipehub.services;

import com.rafa.recipehub.model.Recipe;
import com.rafa.recipehub.model.User;

import java.util.List;

public interface RecipeServices {
    public Recipe createRecipe(Recipe recipe, User user);
    public Recipe findRecipeById(Long id) throws Exception;

    public void deleteRecipe(Long id) throws Exception;
    public Recipe updateRecipe(Recipe recipe,Long id) throws Exception;

    public List<Recipe> findAllRecipe();
    public Recipe likeRecipe(Long recipeId, User user) throws Exception;
}
