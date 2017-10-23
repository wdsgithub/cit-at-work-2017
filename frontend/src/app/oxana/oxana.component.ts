import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { OxanaService } from './service/oxana.service';
import { AnswerDto, StartConversationDto } from './domain/domain';
import { Message } from './domain/message';
import { Observable } from 'rxjs';
import { MessageEvent } from './domain/message-event';

@Component({
    selector: 'oxana',
    templateUrl: './oxana.component.html',
    styleUrls: ['./oxana.component.scss']
})
export class OxanaComponent implements OnInit {
    private static messageDelay: number = 2000;
    
    /** List of chat messages */
    public messages: Message[] = [];
    
    /** User Input */
    public question: string;
    
    public sviboIsTyping: boolean = false;
    
    /** Username */
    private userName: string = "User";
    
    /** Conversation Context */
    private context: any;
    
    @ViewChild('chatWindow') private chatWindow: ElementRef;
    
    constructor(private oxanaService: OxanaService) {
    }
    
    public ngOnInit() {
        this.startNewConversation();
    }
    
    public startNewConversation(): void {
        this.messages = [];
        this.oxanaService.startConversation()
            .subscribe((start: StartConversationDto) => {
                this.context = start.context;
                this.handleResponseMessages(start.greetings);
            }, (err) => {
                console.error("Fehler bei StartConversation", err);
            });
    }
    
    public askQuestion(): void {
        this.addMessage(this.convertMessageRequest(this.question));
        this.oxanaService.ask(this.question, this.context)
            .subscribe((answer: AnswerDto) => {
                this.context = answer.context;
                this.handleResponseMessages(answer.answers);
            });
        this.question = "";
    }
    
    private convertMessageRequest(message: string): Message {
        return new Message(this.userName, message, "request");
    }
    
    private convertMessageResponse(message: string): Message {
        return new Message("Svibo", message, "response");
    }
    
    private addMessage(message: Message): void {
        this.messages.push(message);
        this.scrollToBottom();
    }
    
    private scrollToBottom(): void {
        try {
            setTimeout(() => {
                this.chatWindow.nativeElement.scrollTop = this.chatWindow.nativeElement.scrollHeight;
            });
        } catch (err) {
        }
    }
    
    private handleResponseMessages(answers: string[]) {
        let firstPackage: boolean = true;
        
        if (!answers) {
            console.warn("no answers received");
            return;
        }
        
        Observable.from(answers)
            .map((answer: string) => <MessageEvent>{type: "message", message: answer})
            .map((messageEvent: MessageEvent) => {
                if (firstPackage) {
                    firstPackage = false;
                    return Observable.of(messageEvent);
                }
                
                let variableTimeBeforeTyping: number = Math.random()*1000;
                let variableTimeWhileTyping: number = Math.random()*800;
                
                return Observable.of(<MessageEvent>{type: "typing_on"}).delay(500 + variableTimeBeforeTyping)
                    .concat(Observable.of(<MessageEvent>{type: "typing_off"}).delay(1000 + variableTimeWhileTyping))
                    .concat(Observable.of(messageEvent).delay(100))
                //return Observable.of(answer).concat(Observable.empty().delay(OxanaComponent.messageDelay))
            })
            .concatAll()
            .subscribe((messageEvent: MessageEvent) => {
                switch (messageEvent.type) {
                    case "typing_on":
                        this.sviboIsTyping = true;
                        break;
                    case "typing_off":
                        this.sviboIsTyping = false;
                        break;
                    case "message":
                        this.addMessage(this.convertMessageResponse(messageEvent.message));
                }
                this.scrollToBottom();
            });
    }
}
