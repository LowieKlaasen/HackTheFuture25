import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-aqua-infrastructure',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './aqua-infrastructure.component.html',
  styleUrl: './aqua-infrastructure.component.css'
})
export class AquaInfrastructureComponent {
  password = 'Unknown';
  isLoading = false;

  crackPassword() {
    this.isLoading = true;

    setTimeout(() => {
      this.isLoading = false;
      this.password = '0001';
    }, 3000);
  }
}
 