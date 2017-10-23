import { Component } from '@angular/core';
import { MatDialog } from '@angular/material';
import { PraktikumDialogComponent } from '../praktikum-dialog/praktikum-dialog.component';

@Component({
  selector: 'app-footer',
  templateUrl: './app-footer.component.html',
  styleUrls: ['./app-footer.component.scss']
})
export class AppFooterComponent {

  constructor(private dialog: MatDialog) { }
    
    /**
     * Öffnet den Dialog für Praktikumsplätze
     * @param {string} message
     */
    public openPraktikumDialog(message: string): void {
        let dialogRef = this.dialog.open(PraktikumDialogComponent, {
            data: { praktikum: message }
        });
    }
}
