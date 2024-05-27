import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';


@Component({
  selector: 'app-update-recipe-form',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule,MatButtonModule],
  templateUrl: './update-recipe-form.component.html',
  styleUrl: './update-recipe-form.component.scss'
})
export class UpdateRecipeFormComponent {
  recipeItem:any={
    title:"",
    description:"",
    image:""
  }

  onSubmit(values:any) {
    console.log(this.recipeItem)
  }
}
