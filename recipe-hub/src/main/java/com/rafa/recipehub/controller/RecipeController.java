package com.rafa.recipehub.controller;


import com.rafa.recipehub.model.Recipe;
import com.rafa.recipehub.model.User;
import com.rafa.recipehub.services.RecipeServices;
import com.rafa.recipehub.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeServices recipeServices;

    @Autowired
    private UserServices userServices;

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception{
        User user = userServices.findUserById(userId);
        Recipe createdRecipe=recipeServices.createRecipe(recipe,user);
        return createdRecipe;
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
        Recipe updatedRecipe = recipeServices.updateRecipe(recipe, id);
        return updatedRecipe;
    }


    @GetMapping()
    public List<Recipe> getAllRecipe() throws Exception{
        List<Recipe> recipes=recipeServices.findAllRecipe();
        return recipes;
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception{
        recipeServices.deleteRecipe(recipeId);
        return "Deleted Successfully";
    }

    @PutMapping("/{id}/like/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long userId,@PathVariable  Long Id) throws Exception{

        User user=userServices.findUserById(userId);
        Recipe updatedRecipe = recipeServices.likeRecipe(Id,user);
        return updatedRecipe;
    }
}
