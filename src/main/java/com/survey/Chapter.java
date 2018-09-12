package com.survey;

import com.survey.node.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by a.nigam on 02/04/17.
 */
public class Chapter extends QuestionContainer {

    private final Survey survey;

    public int getId() {
        return id;
    }

    private final int id;
    private final String chapterName;
    private QuestionNode loopQuestion;
    private SurveyStartNode chapterStartNode = new SurveyStartNode();
    private SurveyEndNode chapterEndNode = new SurveyEndNode();
    private SurveyNode previousSurveyNode;
    private SurveyNode nextSurveyNode;


    @Override
    public String toString() {
        return chapterName;
    }

    public Chapter(int id, String name, Survey survey) {
        this.id = id;
        this.chapterName = name;
        this.survey = survey;

    }

    /**
     * Use cases:
     * 1. User has answered at looping question
     * 2. User is in-between the chapter
     * 3. User is at the last question of the looped chapter
     * 4. User is at the last question of all the looped chapters
     */

    @Override
    public SurveyNode getNext(RespondentSurveyContext respondentSurveyContext) {

        SurveyNode node = null;
        // Case 1: User is at the looping question and is about to enter the chapter. -->> set the current looping value ->> find next
        if(isUserAtLoopingQuestion(respondentSurveyContext))
        {
            respondentSurveyContext.setRespondentStatus(RespondentSurveyContext.RespondentStatus.NEW);
            String chapterLoopValue = getChapterLoopValue(respondentSurveyContext);
            respondentSurveyContext.setChapterLoopValue(chapterLoopValue);
            node = super.getNext(respondentSurveyContext, survey.getVersion());
            if (node != null) {
                return node;
            }

            respondentSurveyContext.setRespondentStatus(RespondentSurveyContext.RespondentStatus.FINISHED);
        }

        // Case 2: Current node is inside the chapter -->> Find next
        if(userInsideTheChapter(respondentSurveyContext))
        {
            node = super.getNext(respondentSurveyContext, survey.getVersion());
            if (node != null) {
                return node;
            }
            respondentSurveyContext.setRespondentStatus(RespondentSurveyContext.RespondentStatus.FINISHED);
        }
        // Case 3: User has reached the end of currently looped chapter -->> a. set the next looped value -->> find next
        if(respondentSurveyContext.getRespondentStatus() == RespondentSurveyContext.RespondentStatus.FINISHED)
        {
            String chapterLoopValue = getNextLoopValue(loopQuestion,respondentSurveyContext.getChapterLoopValue(),
                    respondentSurveyContext );
            if(chapterLoopValue != null) {
                respondentSurveyContext.setChapterLoopValue(chapterLoopValue);
                respondentSurveyContext.setRespondentStatus(RespondentSurveyContext.RespondentStatus.NEW);
                node = super.getNext(respondentSurveyContext, survey.getVersion());
                if (node != null) {
                    return node;
                }
            }
            respondentSurveyContext.setRespondentStatus(RespondentSurveyContext.RespondentStatus.FINISHED);
        }
        // User is at the last question of last looped chapter --> return next survey node
        return getNextSurveyNode();



    }

    private boolean userInsideTheChapter(RespondentSurveyContext respondentSurveyContext) {
        return getSurveyNodes().get(respondentSurveyContext.getCurrentQuestionId()) != null && respondentSurveyContext.getChapterLoopValue() != null ;
    }

    private String getChapterLoopValue(RespondentSurveyContext respondentSurveyContext) {
        List<String> loopQuestionAnswer = respondentSurveyContext.getAnswertoMultiChoiceQuestion(loopQuestion);
        if (loopQuestionAnswer == null || loopQuestionAnswer.size() == 0) {
            // user did not provided any answer on which chapter looping can be done
            Log.error("No answer provided to looping question so can't proceed with chapter");
            return null; // no looped chapter . may be we should return the question after the chapter.
        }
        return loopQuestionAnswer.get(0);
    }

    private boolean isUserAtLoopingQuestion(RespondentSurveyContext respondentSurveyContext) {
        return respondentSurveyContext.getCurrentQuestionId() == loopQuestion.getId();
    }



    private String getNextLoopValue(QuestionNode loopQuestion, String currentChapterLoopValue, RespondentSurveyContext respondentSurveyContext) {
        List<String> loopQuestionAnswer = respondentSurveyContext.getAnswertoMultiChoiceQuestion(loopQuestion);
        if(loopQuestionAnswer == null || loopQuestionAnswer.size() == 0){
            return null;
        }
        Iterator<String> itr = loopQuestionAnswer.iterator();

        while (itr.hasNext()){
            String choice = itr.next();
            if(choice.equalsIgnoreCase(currentChapterLoopValue)){
                if(itr.hasNext()){
                    return itr.next();
                }
            }
        }

        return null;
    }

    @Override
    public SurveyNode getEndNode() {
        return chapterEndNode;
    }

    @Override
    public SurveyStartNode getStartNode() {
        return chapterStartNode;
    }

    public void setLoopQuestion(QuestionNode loopQuestion) {
        this.loopQuestion = loopQuestion;
    }

    public void setPreviousSurveyNode(SurveyNode sourceNode) {
        this.previousSurveyNode = sourceNode;
    }

    public SurveyNode getNextSurveyNode() {
        return nextSurveyNode;
    }

    public void setNextSurveyNode(SurveyNode nextSurveyNode) {
        this.nextSurveyNode = nextSurveyNode;
    }







}
