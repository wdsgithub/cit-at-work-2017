import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { VisualRecognitionService } from './service/visual-recognition.service';
import { Subject } from 'rxjs/Subject';
import { ImageClassification } from './domain/image-classification';
import { ClassifierResult, ClassResult, ImageClassifiersDto } from '../oxana/domain/domain';

@Component({
    selector: 'visual-recognition',
    templateUrl: './visual-recognition.component.html',
    styleUrls: ['./visual-recognition.component.scss']
})
export class VisualRecognitionComponent implements OnInit, AfterViewInit {
    
    @ViewChild('video') private video: any;
    // Canvas for Video Snapshots
    @ViewChild('canvas') private canvas: any;
    
    /** Array der letzten Klassifizierungen */
    public recentClassifications: ImageClassification[] = [];
    
    /** Fehlerobjekt, falls Zugriff auf die Kamera nicht funktioniert (zB Berechtigung) */
    public cameraError: MediaStreamError = null;
    
    /** Queue um Bilder Klassifizieren zu lassen */
    private classifyImages: Subject<ImageClassification> = new Subject<ImageClassification>();
    
    constructor(private visualRecognitionService: VisualRecognitionService) {
    }
    
    public ngOnInit(): void {
        this.classifyImages.asObservable().subscribe((imageClassification: ImageClassification) => {
            this.processImageClassification(imageClassification, this.visualRecognitionService);
            this.recentClassifications.unshift(imageClassification);
            if (this.recentClassifications.length > 3) {
                this.recentClassifications = this.recentClassifications.slice(0, 3);
            }
        });
    }
    
    public ngAfterViewInit(): void {
        let _video = this.video.nativeElement;
        if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
            navigator.mediaDevices.getUserMedia({video: {facingMode: "environment"}})
                .then(stream => {
                    _video.srcObject = stream;
                    _video.play();
                })
                .catch((err: MediaStreamError) => {
                    this.cameraError = err;
                    console.warn("Error initializing camera", err);
                });
        }
    }
    
    /**
     * Takes a Snapshot of the current webcam's view and sends that Image to the classification queue
     */
    public analyzeSnapshot(): void {
        // set canvas size to actual video size
        let _video = this.video.nativeElement;
        let dimensions = {width: 640, height: 480};
        if (_video.videoWidth) {
            dimensions.width = _video.videoWidth;
            dimensions.height = _video.videoHeight;
        }
        
        let _canvas = this.canvas.nativeElement;
        _canvas.width = dimensions.width;
        _canvas.height = dimensions.height;
        
        // paint snapshot image to canvas
        _canvas.getContext('2d').drawImage(this.video.nativeElement, 0, 0);
        
        // read canvas content as image
        let mimeType: string = "image/jpeg";
        let dataUrl: string = _canvas.toDataURL(mimeType);
        
        // trigger image for classification
        this.classifyImages.next(<ImageClassification>{imageDataUrl: dataUrl, mimeType: mimeType});
    }
    
    /**
     * Liefert ein Emoji zu der Klassifikation
     * @param {ClassResult} classResult
     * @return {string}
     */
    public getEmoji(classResult: ClassResult): string {
        return "white_check_mark";
    }
    
    /**
     * Triggers the classification of an image
     * @param {ImageClassification} imageClassification
     * @param {VisualRecognitionService} service
     */
    private processImageClassification(imageClassification: ImageClassification, service: VisualRecognitionService): void {
        // strip dataUrl prefix => result: plain base64 data of the image
        let imageData = imageClassification.imageDataUrl.replace("data:" + imageClassification.mimeType + ";base64,", "");
        //console.log("data", imageData);
        imageClassification.loading = true;
        
        service.classifyImage(imageData)
            .subscribe((imageClassifier: ImageClassifiersDto) => {
                if (!imageClassifier.classifiers) {
                    imageClassification.loading = false;
                    console.warn("no classifiers for image.");
                    return;
                }
                
                // extract Classes from result
                imageClassification.classes = [];
                imageClassifier.classifiers.forEach((classifier: ClassifierResult) => {
                    if (classifier.classes) {
                        imageClassification.classes.push(...classifier.classes);
                    }
                });
                
                // sort all classes by score descending
                imageClassification.classes = imageClassification.classes.sort((a, b) => b.score - a.score);
                
                imageClassification.loading = false;
            }, (err) => {
                imageClassification.loading = false;
                console.warn("error classifying image", err);
            });
    }
    
}
