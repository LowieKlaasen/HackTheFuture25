import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-decode',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './decode.component.html',
  styleUrl: './decode.component.css'
})
export class DecodeComponent {
private http = inject(HttpClient);
  
  decodedString = "Decode now!";
  encodingType = "";
  isLoading = false;

  decode() {
    this.isLoading = true;
    this.decodedString = "Decode now!";
    this.encodingType = "";

    this.http.get<{}>(
      '/aqua-infrastructure/FoundationAnchor'
    ).subscribe({
      next: (response) => {
        this.isLoading = false;
        
      },
      error: (error) => {
        console.error('Error decoding:', error);
        this.isLoading = false;
        this.decodedString = "Error";
      }
    });
  }
}