import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArrangementFormComponent } from './arrangement-form.component';

describe('ArrangementFormComponent', () => {
  let component: ArrangementFormComponent;
  let fixture: ComponentFixture<ArrangementFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ArrangementFormComponent]
    });
    fixture = TestBed.createComponent(ArrangementFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
