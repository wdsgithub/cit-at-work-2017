import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { ClassifyImageDto, ImageClassifiersDto } from '../../oxana/domain/domain';
import { environment } from '../../../environments/environment';

/**
 * Service um die VisualRecognition API aufzurufen und Bilder zu klassifizieren
 */
@Injectable()
export class VisualRecognitionService {
    
    private static BASE_URL: string = environment.apiUrl + "/vrec";
    
    constructor(private httpClient: HttpClient) {
    }
    
    /**
     * Classifies an Image via web api
     * @param {string} base64Data
     * @return {Observable<ImageClassifiersDto>}
     */
    public classifyImage(base64Data: string): Observable<ImageClassifiersDto> {
        let payload: ClassifyImageDto = {
            imageDataBase64: base64Data
        };
        
        return this.httpClient.post<ImageClassifiersDto>(VisualRecognitionService.BASE_URL + "/classify", payload);
    }
    
}
