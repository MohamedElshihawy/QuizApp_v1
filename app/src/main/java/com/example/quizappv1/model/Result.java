package com.example.quizappv1.model;

public class Result {
    private double marksGot;
    private int correctAnswers;
    private int attempted;

    public double getMarksGot() {
        return marksGot;
    }

    public void setMarksGot(double marksGot) {
        this.marksGot = marksGot;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getAttempted() {
        return attempted;
    }

    public void setAttempted(int attempted) {
        this.attempted = attempted;
    }

    @Override
    public String toString() {
        return "Result{" +
                "marksGot=" + marksGot +
                ", correctAnswers=" + correctAnswers +
                ", attempted=" + attempted +
                '}';
    }
}
