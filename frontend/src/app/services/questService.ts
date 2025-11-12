import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class QuestService {

  constructor(private http: HttpClient) {
  }

  public GetActiveQuest() :Observable<IQuest> {
    let url :string = "https://htf.collide.be/teams/8df4079c-d229-4f47-8e24-ed095f1a3369/quest"

    return this.http.get<IQuest>(url);
  }



}

export interface IQuest {
  "id": string,
  "teamId": string,
  "closed": boolean,
  "problems": IProblem[],
}

export interface IProblem {
  "name": string,
  "description": string,
  "solved": boolean,
  "score": number,
  "badgeUrl": string,
  "mission": IMission[]
}

export interface IMission {
  "id": string,
  "name": string,
  "objective": string,
  "parameters": string,
  "difficulty": number,
  "remainingAttempts": string,
  "solved": boolean,
  "effect": string
}
