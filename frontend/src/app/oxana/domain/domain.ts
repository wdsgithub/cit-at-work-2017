// Generated using typescript-generator version 1.10.220 on 2017-10-17 17:00:32.

export interface AnswerDto {
    answers?: string[];
    context?: { [index: string]: any };
}

export interface QuestionDto {
    userId?: string;
    context?: { [index: string]: any };
    question?: string;
}

export interface StartConversationDto {
    userName?: string;
    conversationId?: string;
    greetings?: string[];
    context?: { [index: string]: any };
}

export interface ClassifyImageDto {
    imageDataBase64?: string;
}

export interface ImageClassifiersDto {
    classifiers?: ClassifierResult[];
}

export interface ClassifierResult extends GenericModel {
    name?: string;
    classifierId?: string;
    classes?: ClassResult[];
}

export interface ClassResult extends GenericModel {
    className?: string;
    score?: number;
    typeHierarchy?: string;
}

export interface GenericModel extends ObjectModel {
}

export interface ObjectModel {
}
