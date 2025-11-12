import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AquaInfrastructureComponent } from './aqua-infrastructure.component';

describe('AquaInfrastructureComponent', () => {
  let component: AquaInfrastructureComponent;
  let fixture: ComponentFixture<AquaInfrastructureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AquaInfrastructureComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AquaInfrastructureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
