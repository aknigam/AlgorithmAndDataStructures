package com.survey;

import com.survey.node.QuestionNode;
import com.survey.node.SurveyNode;

/**
 * Created by a.nigam on 16/01/17.
 */
public class LinkEdge {

    private final SurveyNode target;

    @Override
    public String toString() {
        return target.toString();
    }

    public LinkEdge(SurveyNode target){
        this.target = target;
    }
    public SurveyNode getTarget() {
        return target;
    }

    boolean evaluate(RespondentSurveyContext context){
        return true;
    }


}
