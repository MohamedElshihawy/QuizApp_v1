package com.example.quizappv1.model;


import java.util.Date;

public class UserHasQuiz {

    private Long id;
    private int degree;
    private boolean tookExam;
    private long userId;
    private String username;
    private long quizId;
    private String quizTitle;
    private int quizMark;
    private Date dataCreate;

    private Date lastUpdate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public boolean isTookExam() {
        return tookExam;
    }

    public void setTookExam(boolean tookExam) {
        this.tookExam = tookExam;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(Date dataCreate) {
        this.dataCreate = dataCreate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    @Override
    public String toString() {
        return "UserHasQuiz{" +
                "id=" + id +
                ", degree=" + degree +
                ", tookExam=" + tookExam +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", quizId=" + quizId +
                ", quizTitle='" + quizTitle + '\'' +
                ", quizMark=" + quizMark +
                ", dataCreate=" + dataCreate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
