import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppMenuComponent } from './app-menu/app-menu.component';
import { PreloadAllModules, RouterModule } from '@angular/router';
import { ROUTES } from './app.routes';
import { AppFooterComponent } from './app-footer/app-footer.component';
import { OxanaComponent } from './oxana/oxana.component';
import { OxanaService } from "./oxana/service/oxana.service";
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatDialogModule, MatInputModule, MatTabsModule } from '@angular/material';
import { VisualRecognitionComponent } from './visual-recognition/visual-recognition.component';
import { VisualRecognitionService } from './visual-recognition/service/visual-recognition.service';
import { LoadingSpinnerComponent } from './loading-spinner/loading-spinner.component';
import { MessageBubbleComponent } from './oxana/message-bubble/message-bubble.component';
import { MemberQrcodeDialogComponent } from './app-footer/member-qrcode-dialog/member-qrcode-dialog.component';
import { MemberInfoComponent } from './app-footer/member-info/member-info.component';
import { PraktikumDialogComponent } from './praktikum-dialog/praktikum-dialog.component';


@NgModule({
    declarations: [
        AppComponent,
        AppMenuComponent,
        AppFooterComponent,
        OxanaComponent,
        VisualRecognitionComponent,
        LoadingSpinnerComponent,
        MessageBubbleComponent,
        MemberQrcodeDialogComponent,
        MemberInfoComponent,
        PraktikumDialogComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule.forRoot(ROUTES, {useHash: true, preloadingStrategy: PreloadAllModules, initialNavigation: true}),
        HttpClientModule,
        BrowserAnimationsModule,
        MatButtonModule,
        MatInputModule,
        MatTabsModule,
        MatDialogModule
    ],
    providers: [
        OxanaService,
        VisualRecognitionService
    ],
    entryComponents: [
        MemberQrcodeDialogComponent,
        PraktikumDialogComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
