import { Routes } from '@angular/router';
import { AquaInfrastructureComponent } from './aqua-infrastructure/aqua-infrastructure.component';
import { CommonDividerComponent } from './common-divider/common-divider.component';
import { DecodeComponent } from './decode/decode.component';
import {Home} from './home/home';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'password', component: AquaInfrastructureComponent },
    { path: 'divider', component: CommonDividerComponent },
    { path: 'decode', component: DecodeComponent },
    { path: 'home', component: Home },
    { path: '**', redirectTo: '/home' }
];
