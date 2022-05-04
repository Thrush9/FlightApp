import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AddAirlinesComponent } from './add-airlines.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';

describe('AddAirlinesComponent', () => {
  let component: AddAirlinesComponent;
  let fixture: ComponentFixture<AddAirlinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddAirlinesComponent],
      imports: [RouterTestingModule, HttpClientTestingModule, MatSnackBarModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAirlinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
