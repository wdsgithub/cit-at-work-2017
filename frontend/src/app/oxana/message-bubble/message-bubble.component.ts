import { Component, Input } from '@angular/core';
import { fadeInOut } from './message-bubble.animation';
import { MatDialog } from '@angular/material';
import { PraktikumDialogComponent } from '../../praktikum-dialog/praktikum-dialog.component';

@Component({
    selector: 'message-bubble',
    templateUrl: './message-bubble.component.html',
    styleUrls: ['./message-bubble.component.scss'],
    animations: [fadeInOut]
})
export class MessageBubbleComponent {
    
    @Input() public userName: string;
    @Input() public message: string;
    @Input() public isUserMessage: boolean = false;
    
    constructor(private dialog: MatDialog) {
    }
    
    /**
     * Gibt an ob es sich um eine Url handelt
     * @param {string} message
     * @return {boolean}
     */
    public isLink(message: string): boolean {
        return message.startsWith("http");
    }
    
    /**
     * Gibt an ob es sich um einen Praktikumslink handelt
     * @param {string} message
     * @return {boolean}
     */
    public isPraktikum(message: string): boolean {
        return message.startsWith("{{praktikum");
    }
    
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
