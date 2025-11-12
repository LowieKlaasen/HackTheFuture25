import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonDividerComponent } from './common-divider.component';

describe('CommonDividerComponent', () => {
  let component: CommonDividerComponent;
  let fixture: ComponentFixture<CommonDividerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommonDividerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommonDividerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
