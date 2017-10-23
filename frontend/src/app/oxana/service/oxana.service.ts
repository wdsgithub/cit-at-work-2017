import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AnswerDto, QuestionDto, StartConversationDto } from '../domain/domain';
import { environment } from '../../../environments/environment';

@Injectable()
export class OxanaService {
    
    private static OXANA_BASE_URL: string = environment.apiUrl + "/svibo";
    
    private userName: string = "Anonymous";
    
    constructor(private httpClient: HttpClient) {
    }
    
    /**
     * Start a new Conversation
     * @return {StartConversationDto}
     */
    public startConversation(): Observable<StartConversationDto> {
        return this.httpClient
            .get<StartConversationDto>(OxanaService.OXANA_BASE_URL + "/start?user=" + this.userName);
    }
    
    /**
     * Ask a Question
     * @param {string} question
     * @param {string} context
     * @return {AnswerDto}
     */
    public ask(question: string, context: any): Observable<AnswerDto> {
        let userId: string = this.userName;
        let questionDto: QuestionDto = {userId, context, question};
        
        return this.httpClient
            .post<AnswerDto>(OxanaService.OXANA_BASE_URL + "/ask", questionDto);
    }
}
