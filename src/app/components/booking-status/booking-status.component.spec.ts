import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BookingStatusComponent } from './booking-status.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';

describe('BookingStatusComponent', () => {
  let component: BookingStatusComponent;
  let fixture: ComponentFixture<BookingStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BookingStatusComponent],
      imports: [RouterTestingModule, HttpClientTestingModule, MatSnackBarModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
