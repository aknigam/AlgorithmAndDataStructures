package com.survey;

import com.survey.node.SurveyNode;

/**
 * Created by a.nigam on 16/01/17.
 */
public class RespondentSurveyContext {


    private int currentQuestionId;

    private int currentChapterId;

    private String chapterLoopValue;
    private Chapter chapter;

    public RespondentSurveyContext(int questionId, int chapterId, String loopValue) {
        this.currentQuestionId = questionId;
        this.currentChapterId = chapterId;
        this.chapterLoopValue = loopValue;
    }

    public RespondentSurveyContext(int questionId) {
        this.currentQuestionId = questionId;
    }

    public boolean hasRespondentAnswered(SurveyNode possiblePreviousNode) {
        return true;
    }

    public int getCurrentQuestionId() {
        return currentQuestionId;
    }

    public void setCurrentQuestionId(int currentQuestionId) {
        this.currentQuestionId = currentQuestionId;
    }

    public int getCurrentChapterId() {
        return currentChapterId;
    }

    public void setCurrentChapterId(int currentChapterId) {
        this.currentChapterId = currentChapterId;
    }

    public String getChapterLoopValue() {
        return chapterLoopValue;
    }

    public void setChapterLoopValue(String chapterLoopValue) {
        this.chapterLoopValue = chapterLoopValue;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Chapter getChapter() {
        return chapter;
    }
}
