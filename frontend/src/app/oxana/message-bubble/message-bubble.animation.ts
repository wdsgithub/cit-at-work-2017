import { animate, AnimationTriggerMetadata, style, transition, trigger } from '@angular/animations';

export const fadeInOut: AnimationTriggerMetadata = trigger('fadeInOut', [
    transition(':enter', [
        style({opacity:0}),
        animate('300ms ease-in', style({opacity:1}))
    ]),
    transition(':leave', [
        style({opacity:1}),
        animate('300ms ease-out', style({opacity:0}))
    ])
]);
