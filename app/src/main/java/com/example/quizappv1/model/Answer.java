package com.example.quizappv1.model;



public class Answer {

    private long id;
    private String givenAnswer;
    private long questionId;
    private String content;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private long quizId;
    private String quizTitle;
    private int quizMark;
    private int quizDegree ;
    private long userId;
    private long userQuizId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public int getQuizMark() {
        return quizMark;
    }

    public void setQuizMark(int quizMark) {
        this.quizMark = quizMark;
    }

    public int getQuizDegree() {
        return quizDegree;
    }

    public void setQuizDegree(int quizDegree) {
        this.quizDegree = quizDegree;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserQuizId() {
        return userQuizId;
    }

    public void setUserQuizId(long userQuizId) {
        this.userQuizId = userQuizId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", givenAnswer='" + givenAnswer + '\'' +
                ", questionId=" + questionId +
                ", content='" + content + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", answer='" + answer + '\'' +
                ", quizId=" + quizId +
                ", quizTitle='" + quizTitle + '\'' +
                ", quizMark=" + quizMark +
                ", quizDegree=" + quizDegree +
                ", userId=" + userId +
                ", userQuizId=" + userQuizId +
                '}';
    }
}
