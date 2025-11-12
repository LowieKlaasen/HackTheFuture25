import { Routes } from '@angular/router';
import { AquaInfrastructureComponent } from './aqua-infrastructure/aqua-infrastructure.component';
import { CommonDividerComponent } from './common-divider/common-divider.component';
import { DecodeComponent } from './decode/decode.component';
import { MarineTrafficMonitorComponent } from './marine-traffic-monitor/marine-traffic-monitor.component';
import { SensorBuoyNetwerkComponent } from './sensor-buoy-netwerk/sensor-buoy-netwerk.component';

export const routes: Routes = [
    { path: '', redirectTo: '/password', pathMatch: 'full' },
    { path: 'password', component: AquaInfrastructureComponent },
    { path: 'divider', component: CommonDividerComponent },
    { path: 'decode', component: DecodeComponent },
    { path: 'factorial', component: MarineTrafficMonitorComponent },
    { path: 'morse', component: SensorBuoyNetwerkComponent },
    { path: '**', redirectTo: '/password' }
];
