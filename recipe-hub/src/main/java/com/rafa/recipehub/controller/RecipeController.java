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

    @PostMapping()
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userServices.findUserByJwt(jwt);
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

    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception{

        User user=userServices.findUserByJwt(jwt);
        Recipe updatedRecipe = recipeServices.likeRecipe(id,user);
        return updatedRecipe;
    }
}
