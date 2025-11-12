import { Routes } from '@angular/router';
import { AquaInfrastructureComponent } from './aqua-infrastructure/aqua-infrastructure.component';
import { CommonDividerComponent } from './common-divider/common-divider.component';
import { DecodeComponent } from './decode/decode.component';
<<<<<<< HEAD
import { MarineTrafficMonitorComponent } from './marine-traffic-monitor/marine-traffic-monitor.component';
import { SensorBuoyNetwerkComponent } from './sensor-buoy-netwerk/sensor-buoy-netwerk.component';
=======
import {Home} from './home/home';
>>>>>>> 5029f88bb8f559b096ece2cb29e072bcb527c47e

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'password', component: AquaInfrastructureComponent },
    { path: 'divider', component: CommonDividerComponent },
    { path: 'decode', component: DecodeComponent },
<<<<<<< HEAD
    { path: 'factorial', component: MarineTrafficMonitorComponent },
    { path: 'morse', component: SensorBuoyNetwerkComponent },
    { path: '**', redirectTo: '/password' }
=======
    { path: 'home', component: Home },
    { path: '**', redirectTo: '/home' }
>>>>>>> 5029f88bb8f559b096ece2cb29e072bcb527c47e
];
