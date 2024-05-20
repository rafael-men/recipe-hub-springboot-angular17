package com.rafa.recipehub.controller;


import com.rafa.recipehub.model.Recipe;
import com.rafa.recipehub.model.User;
import com.rafa.recipehub.services.RecipeServices;
import com.rafa.recipehub.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeServices recipeServices;

    @Autowired
    private UserServices userServices;

    @PostMapping("/api/recipe/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception{
        User user = userServices.findUserById(userId);
        Recipe createdRecipe=recipeServices.createRecipe(recipe,user);
        return createdRecipe;
    }

    @PutMapping("/api/recipe/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe ,@PathVariable  Long Id) throws Exception{
        Recipe updatedRecipe = recipeServices.updateRecipe(recipe,Id);
        return updatedRecipe;
    }

    @GetMapping("/api/recipe")
    public List<Recipe> getAllRecipe() throws Exception{
        List<Recipe> recipes=recipeServices.findAllRecipe();
        return recipes;
    }

    @DeleteMapping("/api/recipe/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception{
        recipeServices.deleteRecipe(recipeId);
        return "Deleted Successfully";
    }

    @PutMapping("/api/recipe/{id}/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long userId,@PathVariable  Long Id) throws Exception{

        User user=userServices.findUserById(userId);
        Recipe updatedRecipe = recipeServices.likeRecipe(Id,user);
        return updatedRecipe;
    }
}
