import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipeServiceService {

  private baseUrl = 'http://localhost:5454';

  constructor(private http: HttpClient) { }

  recipeSubject = new BehaviorSubject<any>({
    recipes: [],
    loading: false,
    newRecipe: null
  });

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwt');
    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  getRecipes(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get<any>(`${this.baseUrl}/api/recipes`, { headers }).pipe(
      tap((recipes) => {
        const currentState = this.recipeSubject.value;
        this.recipeSubject.next({ ...currentState, recipes });
      })
    );
  }

  createRecipe(recipe: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post<any>(`${this.baseUrl}/api/recipes`, recipe, { headers }).pipe(
      tap((newRecipe) => {
        const currentState = this.recipeSubject.value;
        this.recipeSubject.next({ ...currentState, recipes: [newRecipe, ...currentState.recipes] });
      })
    );
  }

  updateRecipe(recipe: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put<any>(`${this.baseUrl}/api/recipes/${recipe.id}`, recipe, { headers }).pipe(
      tap((updatedRecipe) => {
        const currentState = this.recipeSubject.value;
        const updatedRecipes = currentState.recipes.map((item: any) => item.id === updatedRecipe.id ? updatedRecipe : item);
        this.recipeSubject.next({ ...currentState, recipes: updatedRecipes });
      })
    );
  }

  likeRecipe(id: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put<any>(`${this.baseUrl}/api/recipes/${id}/like`, {}, { headers }).pipe(
      tap((updatedRecipe) => {
        const currentState = this.recipeSubject.value;
        const updatedRecipes = currentState.recipes.map((item: any) => item.id === updatedRecipe.id ? updatedRecipe : item);
        this.recipeSubject.next({ ...currentState, recipes: updatedRecipes });
      })
    );
  }

  deleteRecipe(id: any): Observable<any> {
    const headers = this.getHeaders();
    const url = `${this.baseUrl}/api/recipes/${id}`;
  
    return this.http.delete<any>(url, { headers }).pipe(
      catchError((error) => {
        console.error('Error deleting recipe:', error);
        throw new Error('Failed to delete recipe. Please try again later.'); // Ou personalize a mensagem de erro conforme necessário
      })
    );
  }
}
