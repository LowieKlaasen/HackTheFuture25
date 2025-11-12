import { Routes } from '@angular/router';
import { AquaInfrastructureComponent } from './aqua-infrastructure/aqua-infrastructure.component';

export const routes: Routes = [
    { path: '', redirectTo: '/password', pathMatch: 'full' },
    { path: 'password', component: AquaInfrastructureComponent },
    { path: '**', redirectTo: '/password' }
];
