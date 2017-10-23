import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
    selector: 'member-qrcode-dialog',
    templateUrl: './member-qrcode-dialog.component.html',
    styleUrls: ['./member-qrcode-dialog.component.scss']
})
export class MemberQrcodeDialogComponent {
    
    constructor(public dialogRef: MatDialogRef<MemberQrcodeDialogComponent>,
                @Inject(MAT_DIALOG_DATA) public data: any) {
    }
    
    public get name(): string {
        return this.data.name;
    }
    
    public get mail(): string {
        return this.data.mail;
    }
    
    public closeDialog(): void {
        this.dialogRef.close();
    }
    
}
