import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberQrcodeDialogComponent } from './member-qrcode-dialog.component';

describe('MemberQrcodeDialogComponent', () => {
  let component: MemberQrcodeDialogComponent;
  let fixture: ComponentFixture<MemberQrcodeDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemberQrcodeDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberQrcodeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
