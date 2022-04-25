import { TestBed } from '@angular/core/testing';

import { LandingpageGuard } from './landingpage.guard';

describe('LandingpageGuard', () => {
  let guard: LandingpageGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(LandingpageGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
