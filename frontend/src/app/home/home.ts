import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IQuest, QuestService} from '../services/questService';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit {

  quest : IQuest | undefined;

  constructor(private questService : QuestService, private router: Router) { }

  ngOnInit() {
    this.questService.GetActiveQuest().subscribe(quest => {
      this.quest = quest;
    })
  }

  navigateTo(missionName : string) {
    let route: string = "";

    switch (missionName) {
      case "Modular Expansion Kit":
        route = "password";
        break;
      case "Energy-Efficient Water Pump":
        route = "divider";
        break;
      case "Foundation Anchor":
        route = "decode";
        break;
    }

    this.router.navigate([route]);
  }

}
