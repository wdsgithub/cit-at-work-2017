import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material';
import { MemberQrcodeDialogComponent } from '../member-qrcode-dialog/member-qrcode-dialog.component';

@Component({
    selector: 'member-info',
    templateUrl: './member-info.component.html',
    styleUrls: ['./member-info.component.scss']
})
export class MemberInfoComponent {
    
    @Input() public name: string;
    @Input() public mail: string;
    
    constructor(private dialog: MatDialog) {
    }
    
    public showVCard(): void {
        let dialogRef = this.dialog.open(MemberQrcodeDialogComponent, {
            data: { name: this.name, mail: this.mail }
        });
    }
}
