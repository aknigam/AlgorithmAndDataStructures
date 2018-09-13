package com.survey;

import com.survey.node.PageNode;
import com.survey.node.QuestionNode;
import com.survey.node.SurveyNode;
import com.survey.node.SurveyStartNode;

import java.util.Map;

/**
 * Created by a.nigam on 02/04/17.
 */
public interface QuestionContainerI {

    SurveyNode getNext( RespondentSurveyContext respondentSurveyContext);

    SurveyNode getEndNode();

    SurveyStartNode getStartNode();


    PageNode getPageNode(int fromNodeQuestionId);

    boolean isNodePresent(Integer questionId);

    Map<Integer, PageNode> getPageNodes();
}
