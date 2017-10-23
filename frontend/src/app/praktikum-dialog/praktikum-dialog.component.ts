import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'praktikum-dialog',
  templateUrl: './praktikum-dialog.component.html',
  styleUrls: ['./praktikum-dialog.component.scss']
})
export class PraktikumDialogComponent {
    
    constructor(public dialogRef: MatDialogRef<PraktikumDialogComponent>,
                @Inject(MAT_DIALOG_DATA) public data: any) {
    }
    
    public get praktikumImage(): string {
        let imageName: string = this.data.praktikum;
        
        if (imageName) {
            imageName = imageName.replace(/{/g, "").replace(/}/g, "");
        }
        
        return imageName;
    }
    
    public closeDialog(): void {
        this.dialogRef.close();
    }
}
