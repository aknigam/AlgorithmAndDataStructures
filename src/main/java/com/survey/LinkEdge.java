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
    private String choice;

    private boolean active = true;

    @Override
    public String toString() {
        return target.toString();
    }

    public LinkEdge(SurveyNode source , SurveyNode target, String choice){
        this(source, target);
        this.choice = choice;
    }

    public LinkEdge( SurveyNode source , SurveyNode target){
        this.target = target;
        this.source = source;
    }

    public SurveyNode getTarget() {
        return target;
    }

    public boolean evaluate(RespondentSurveyContext context){
        if(choice == null)
            return true;
        String answerToCurrentQuestion = context.getAnswertoSingleChoiceQuestion((QuestionNode) source);
        if(answerToCurrentQuestion.equalsIgnoreCase(choice)){
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
}
