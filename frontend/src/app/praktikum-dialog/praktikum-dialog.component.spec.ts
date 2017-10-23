import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PraktikumDialogComponent } from './praktikum-dialog.component';

describe('PraktikumDialogComponent', () => {
  let component: PraktikumDialogComponent;
  let fixture: ComponentFixture<PraktikumDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PraktikumDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PraktikumDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
