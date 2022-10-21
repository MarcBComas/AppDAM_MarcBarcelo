package com.example.appdam;

public class Question {
    private int TextResId;
    private boolean AnswerTrue;
    private boolean isAnswered;

    public Question(int textResId, boolean answerTrue) {
        TextResId = textResId;
        AnswerTrue = answerTrue;
        isAnswered = false;
    }

    public int getTextResId() {
        return TextResId;
    }

    public void setTextResId(int TextResId) {
        this.TextResId = TextResId;
    }

    public boolean isAnswerTrue() {
        return AnswerTrue;
    }

    public void setAnswerTrue(boolean AnswerTrue) {
        this.AnswerTrue = AnswerTrue;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
