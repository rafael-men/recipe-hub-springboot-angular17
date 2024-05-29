import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule,FormsModule, MatFormFieldModule, MatInputModule,MatButtonModule,ReactiveFormsModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss'
})
export class AuthComponent {
  
  isRegister=true;

  loginForm = new FormGroup({
     email: new FormControl('',[Validators.required,Validators.email]),
     password: new FormControl('',[Validators.required])
  })

  registerForm = new FormGroup({
    fullName: new FormControl('',[Validators.required]),
    email: new FormControl('',[Validators.required,Validators.email]),
    password: new FormControl('',[Validators.required,Validators.minLength(6)])
 })

  handleRegister() {
    console.log("register",this.registerForm.value)
  }

  handleLogin() {
    console.log("login",this.loginForm.value)
  }

  togglePanel() {
    this.isRegister = !this.isRegister
  }
}
