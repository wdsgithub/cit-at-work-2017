import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { VisualRecognitionComponent } from './visual-recognition.component';

describe('VisualRecognitionComponent', () => {
  let component: VisualRecognitionComponent;
  let fixture: ComponentFixture<VisualRecognitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisualRecognitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualRecognitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
