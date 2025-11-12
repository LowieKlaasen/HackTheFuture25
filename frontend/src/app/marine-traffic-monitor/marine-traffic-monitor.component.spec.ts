import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarineTrafficMonitorComponent } from './marine-traffic-monitor.component';

describe('MarineTrafficMonitorComponent', () => {
  let component: MarineTrafficMonitorComponent;
  let fixture: ComponentFixture<MarineTrafficMonitorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MarineTrafficMonitorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MarineTrafficMonitorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
