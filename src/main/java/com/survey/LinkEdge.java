package com.survey;

import com.survey.node.QuestionNode;
import com.survey.node.SurveyNode;

/**
 * Created by a.nigam on 16/01/17.
 */
public class LinkEdge {

    private final SurveyNode target;

    public SurveyNode getSource() {
        return source;
    }

    private final SurveyNode source;
    private String linkChoice;

    private boolean active = true;

    @Override
    public String toString() {
        return target.toString();
    }

    public LinkEdge(SurveyNode source , SurveyNode target, String choice){
        this(source, target);
        this.linkChoice = choice;
    }

    public LinkEdge( SurveyNode source , SurveyNode target){
        this.target = target;
        this.source = source;
    }

    public SurveyNode getTarget() {
        return target;
    }

    public boolean evaluate(RespondentSurveyContext context){
        if(linkChoice == null)
            return true;
        String answerToCurrentQuestion = context.getAnswertoSingleChoiceQuestion((QuestionNode) source);
        if(answerToCurrentQuestion == null || answerToCurrentQuestion.equalsIgnoreCase(linkChoice)){
            return true;
        }
        return false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public boolean isActive() {
        return active;
    }

    public boolean isLinkedLogicEdge(){
        return linkChoice != null;
    }
}
