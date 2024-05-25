import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';

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

  onSubmit(values:any) {

  }
}
