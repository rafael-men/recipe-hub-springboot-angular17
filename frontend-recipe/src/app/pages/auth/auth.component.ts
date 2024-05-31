import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { AuthServiceService } from '../../services/Auth/auth-service.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule
  ],
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']  // Corrected property name to 'styleUrls'
})
export class AuthComponent {
  
  isRegister = false;

  constructor(public authService: AuthServiceService) {}

  loginForm = new FormGroup({
     email: new FormControl('', [Validators.required, Validators.email]),
     password: new FormControl('', [Validators.required])
  });

  registerForm = new FormGroup({
    fullName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  handleRegister() {
    console.log("register", this.registerForm.value);
    this.authService.register(this.registerForm.value).subscribe({
      next: (response) => {
        localStorage.setItem("jwt", response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("signup successfully", response);
      },
      error: (err) => {
        console.error("signup failed", err);
      }
    });
  }

  handleLogin() {
    console.log("login", this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe({
      next: (response) => {
        localStorage.setItem("jwt", response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("login successfully", response);
      },
      error: (err) => {
        console.error("login failed", err);
      }
    });
  }

  togglePanel() {
    this.isRegister = !this.isRegister;
  }
}
