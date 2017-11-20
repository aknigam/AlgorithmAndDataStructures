package com.survey;

import com.survey.node.QuestionNode;
import com.survey.node.SurveyNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a.nigam on 16/01/17.
 */
public class RespondentSurveyContext {


    private int currentQuestionId;

    private int currentChapterId;

    private int version;

    private String chapterLoopValue;
    private Chapter chapter;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    private RespondentStatus respondentStatus = RespondentStatus.NEW;

    public static enum RespondentStatus{
        NEW, IN_PROGRESS, FINISHED
    }

    public RespondentStatus getRespondentStatus() {
        return respondentStatus;
    }

    public void setRespondentStatus(RespondentStatus respondentStatus) {
        this.respondentStatus = respondentStatus;
    }


//    private List<String> multiSelectAnswer;

//    private String answertoCurrentQuestion;

    private Map<Integer, String> singleChoiceAnswers = new HashMap<>();
    private Map<Integer, List<String>> multiChoiceAnswers = new HashMap<>();

    public void setAnswertoCurrentQuestion(String answertoCurrentQuestion, QuestionNode question) {

        QuestionType questionType = question.getQuestionType();
        if(questionType == QuestionType.SINGLE_CHOICE)
            this.singleChoiceAnswers.put( question.getId(), answertoCurrentQuestion);
        else{
            this.multiChoiceAnswers.put( question.getId(), Arrays.asList(answertoCurrentQuestion.split(",")));

        }
    }




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

    public String getAnswertoSingleChoiceQuestion(QuestionNode questionNode) {

        if(questionNode.getQuestionType() == QuestionType.SINGLE_CHOICE) {
            return  singleChoiceAnswers.get(questionNode.getId());
        }
        return null;
    }

    public List<String> getAnswertoMultiChoiceQuestion(QuestionNode questionNode) {

        if(questionNode.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
            return  multiChoiceAnswers.get(questionNode.getId());
        }
        return null;
    }

}
