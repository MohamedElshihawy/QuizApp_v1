package com.example.quizappv1.model;


public class QuizHistoryItem {

    private String subject ;
    private int score ;
    private int maxScore ;
    private long DateInMillis ;
    private long timeTaken;


    public QuizHistoryItem(String subject, int score, int maxScore, long dateInMillis, long timeTaken) {
        this.subject = subject;
        this.score = score;
        this.maxScore = maxScore;
        DateInMillis = dateInMillis;
        this.timeTaken = timeTaken;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getDateInMillis() {
        return DateInMillis;
    }

    public void setDateInMillis(Long dateInMillis) {
        DateInMillis = dateInMillis;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
