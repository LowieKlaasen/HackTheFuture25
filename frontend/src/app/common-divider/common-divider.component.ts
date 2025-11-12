import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-common-divider',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './common-divider.component.html',
  styleUrl: './common-divider.component.css'
})
export class CommonDividerComponent {
  private http = inject(HttpClient);
  
  divider = 0;
  isLoading = false;

  calculateDivider() {
    this.isLoading = true;
    this.divider = 0;

    this.http.get<{found: boolean, result?: number, externalResponse?: string}>(
      '/aqua-infrastructure/EnergyEfficientWaterPump'
    ).subscribe({
      next: (response) => {
        this.isLoading = false;
        
        if (response.found && response.result !== undefined) {
          this.divider = response.result;
        } else {
          this.divider = -1;
        }
      },
      error: (error) => {
        console.error('Error calculating GCD:', error);
        this.isLoading = false;
        this.divider = -1;
      }
    });
  }
}