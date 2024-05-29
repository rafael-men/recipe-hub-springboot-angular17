import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeServiceService {

  private baseUrl='http://localhost:5454'

  constructor(private http:HttpClient) { }

  recipeSubject = new BehaviorSubject<any>({
    recipes:[],
    loading:false,
    newRecipe:null
  })

  private getHeaders():HttpHeaders{
    const token = localStorage.getItem("jwt")
    return new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem("jwt")}`
    })
  }

  createRecipe():Observable<any> {
    const headers = this.getHeaders()
    return this.http.post(`${this.baseUrl}/api/recipe`,{headers}).pipe(
      tap((recipe)=> {
        const currentState=this.recipeSubject.value
        this.recipeSubject.next({...currentState,recipes:[new,]})
      })

    )
  }
}
