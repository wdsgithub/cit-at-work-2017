import { inject, TestBed } from '@angular/core/testing';

import { VisualRecognitionService } from './visual-recognition.service';

describe('VisualRecognitionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VisualRecognitionService]
    });
  });

  it('should be created', inject([VisualRecognitionService], (service: VisualRecognitionService) => {
    expect(service).toBeTruthy();
  }));
});
