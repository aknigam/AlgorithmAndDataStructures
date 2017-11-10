package com.survey.node;

import com.survey.RespondentSurveyContext;

/**
 * Created by a.nigam on 02/04/17.
 */
public class ChapterEndNode extends AbstractSurveyNode{



    public ChapterEndNode(int loopQuestionId, String loopChoice){
        super(0);
    }

    @Override
    public SurveyNode getNextActiveNode(RespondentSurveyContext context) {
        return super.getNextActiveNode(context);
    }

}
