import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SensorBuoyNetwerkComponent } from './sensor-buoy-netwerk.component';

describe('SensorBuoyNetwerkComponent', () => {
  let component: SensorBuoyNetwerkComponent;
  let fixture: ComponentFixture<SensorBuoyNetwerkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SensorBuoyNetwerkComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SensorBuoyNetwerkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
