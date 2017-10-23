import { Routes } from '@angular/router';
import { OxanaComponent } from './oxana/oxana.component';
import { VisualRecognitionComponent } from './visual-recognition/visual-recognition.component';

export const ROUTES: Routes = [
    {
        path: '',
        redirectTo: 'chat',
        pathMatch: 'full'
    },
    {
        path: 'chat',
        component: OxanaComponent,
    },
    {
        path: 'visual-recognition',
        component: VisualRecognitionComponent,
    },
    {
        path: '**',
        redirectTo: 'chat'
    }
];
