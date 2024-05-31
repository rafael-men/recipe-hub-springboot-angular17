import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { RecipeServiceService } from '../../services/Recipe/recipe-service.service';

@Component({
  selector: 'app-create-recipe-form',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule,MatButtonModule],
  templateUrl: './create-recipe-form.component.html',
  styleUrl: './create-recipe-form.component.scss'
})
export class CreateRecipeFormComponent {
  recipeItem:any={
    title:"",
    description:"",
    image:""
  }

  constructor(private recipeService:RecipeServiceService){}

  onSubmit(values:any) {
    console.log(this.recipeItem)
    this.recipeService.createRecipe(this.recipeItem).subscribe(
      {
        next:data=>console.log("created recipe",data),
        error:error=>console.log("error",error)
      }
    )
  }
}
