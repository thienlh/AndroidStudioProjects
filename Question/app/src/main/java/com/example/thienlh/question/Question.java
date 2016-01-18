package com.example.thienlh.question;

/**
 * Created by thienlh on 1/7/2016.
 */
public class Question {
  private String question;
  private boolean answer;

  public Question(String question, boolean answer) {
    this.question = question;
    this.answer = answer;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public boolean isAnswer() {
    return answer;
  }

  public void setAnswer(boolean answer) {
    this.answer = answer;
  }
}
