package com.survey.node;

import com.survey.RespondentSurveyContext;

/**
 * Created by a.nigam on 03/03/17.
 */
public class SurveyStartNode extends AbstractSurveyNode {


    public SurveyStartNode() {
        super(0);
    }

    @Override
    public SurveyNode getNextActiveNode(RespondentSurveyContext context) {
        return getAllPossibleNextNodes().get(0).getTarget();
    }

    @Override
    public String toString() {
        return "Start -> ";
    }
}
