import { ClassResult } from '../../oxana/domain/domain';

/**
 * Container for image classification results
 */
export class ImageClassification {
    /** DataUrl of the image */
    public imageDataUrl: string;
    
    /** Image MIME-Type */
    public mimeType: string;
    
    /** Classified classes for this image */
    public classes: ClassResult[] = [];
    
    /** Flag where image is being classified */
    public loading: boolean = false;
}

