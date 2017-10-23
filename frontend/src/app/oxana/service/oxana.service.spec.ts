import { inject, TestBed } from '@angular/core/testing';

import { OxanaService } from './oxana.service';

describe('OxanaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OxanaService]
    });
  });

  it('should be created', inject([OxanaService], (service: OxanaService) => {
    expect(service).toBeTruthy();
  }));
});
