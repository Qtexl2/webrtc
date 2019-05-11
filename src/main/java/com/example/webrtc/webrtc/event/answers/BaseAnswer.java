package com.example.webrtc.webrtc.event.answers;

public abstract class BaseAnswer {

    private TypeAnswer typeAnswer;

    public BaseAnswer(TypeAnswer typeAnswer) {
        this.typeAnswer = typeAnswer;
    }

    public TypeAnswer getTypeAnswer() {
        return typeAnswer;
    }

    public void setTypeAnswer(TypeAnswer typeAnswer) {
        this.typeAnswer = typeAnswer;
    }
}
