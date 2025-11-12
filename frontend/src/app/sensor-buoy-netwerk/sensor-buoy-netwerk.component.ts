import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';

@Component({
  selector: 'app-sensor-buoy-netwerk',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sensor-buoy-netwerk.component.html',
  styleUrl: './sensor-buoy-netwerk.component.css'
})
export class SensorBuoyNetwerkComponent {
  private http = inject(HttpClient);

  decodedString = "Decode now!";
  isLoading = false;
  found = false;

  decode() {
    this.isLoading = true;
    this.decodedString = "Decoding...";
    this.found = false;

    this.http.get<DecodeResponse>(
      '/data-visualization/SensorBuoyNetwork'
    ).subscribe({
      next: (response) => {
        this.isLoading = false;

        if (response.found && response.result) {
          this.decodedString = response.result;
          this.found = true;
        } else {
          this.decodedString = "Not found - mission not solved";
          this.found = false;
        }
      },
      error: (error) => {
        console.error('Error decoding:', error);
        this.isLoading = false;
        this.decodedString = "Error decoding";
        this.found = false;
      }
    });
  }
}

interface DecodeResponse {
  found: boolean;
  result?: string;
  externalResponse?: string;
}
