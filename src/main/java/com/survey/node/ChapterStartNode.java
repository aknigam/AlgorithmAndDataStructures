package com.survey.node;

import com.survey.RespondentSurveyContext;

/**
 * Created by a.nigam on 03/03/17.
 */
public class ChapterStartNode extends AbstractSurveyNode {




    public ChapterStartNode(int loopQuestionId, String loopChoice){
        super(0);
    }

    @Override
    public SurveyNode getNextActiveNode(RespondentSurveyContext context) {
        return super.getNextActiveNode(context);
    }


}
