import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OxanaComponent } from './oxana.component';

describe('OxanaComponent', () => {
  let component: OxanaComponent;
  let fixture: ComponentFixture<OxanaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OxanaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OxanaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
