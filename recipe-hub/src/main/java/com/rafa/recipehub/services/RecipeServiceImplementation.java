package com.rafa.recipehub.services;


import com.rafa.recipehub.model.Recipe;
import com.rafa.recipehub.model.User;
import com.rafa.recipehub.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImplementation implements RecipeServices {

    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public Recipe createRecipe(Recipe recipe, User user) {

        Recipe createdRecipe = new Recipe();
        createdRecipe.
        return null;
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {
        return null;
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {

    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        return null;
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return List.of();
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        return null;
    }
}
