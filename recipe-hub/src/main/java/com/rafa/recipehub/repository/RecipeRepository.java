package com.rafa.recipehub.repository;

import com.rafa.recipehub.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
}
