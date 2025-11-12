import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-marine-traffic-monitor',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './marine-traffic-monitor.component.html',
  styleUrl: './marine-traffic-monitor.component.css'
})
export class MarineTrafficMonitorComponent {
  private http = inject(HttpClient);
  
  factorial = 0;
  isLoading = false;
  
  calculateFactorial() {
    this.isLoading = true;

    this.http.get<{ found: boolean; result?: number; externalResponse?: string }>(
      '/data-visualization/MarineTrafficMonitor'
    ).subscribe({
      next: (response) => {
        this.isLoading = false;
        
        if (response.found && response.result !== undefined) {
          this.factorial = response.result;
        }
      },
      error: (error) => {
        console.error('Error calculating factorial:', error);
        this.isLoading = false;
        this.factorial = -1;
      }
    });
  }
}