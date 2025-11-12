import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-aqua-infrastructure',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './aqua-infrastructure.component.html',
  styleUrl: './aqua-infrastructure.component.css'
})
export class AquaInfrastructureComponent {
  private http = inject(HttpClient);
  
  password = 'Unknown';
  isLoading = false;

  crackPassword() {
    this.isLoading = true;
    this.password = 'Unknown';

    this.http.get<{ found: boolean; code?: string; externalResponse?: string }>(
      '/aqua-infrastructure/ModularExpansionKit'
    ).subscribe({
      next: (response) => {
        this.isLoading = false;
        
        if (response.found && response.code) {
          this.password = response.code;
        } else {
          this.password = 'Not Found';
        }
      },
      error: (error) => {
        console.error('Error cracking password:', error);
        this.isLoading = false;
        this.password = 'Error';
      }
    });
  }
}