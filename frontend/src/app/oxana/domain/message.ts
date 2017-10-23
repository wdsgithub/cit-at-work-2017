export class Message {
  userName: string;
  text: string;
  messageType: string;
  
  constructor(userName: string, text: string, messageType: string) {
    this.userName = userName;
    this.text = text;
    this.messageType = messageType;
  }
}
