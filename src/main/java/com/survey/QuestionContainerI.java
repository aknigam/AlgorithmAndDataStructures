package com.survey;

import com.survey.node.QuestionNode;
import com.survey.node.SurveyNode;
import com.survey.node.SurveyStartNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a.nigam on 02/04/17.
 */
public interface QuestionContainerI {

    void addQuestionNode(QuestionNode question);

    void addQuestionNode(int fromNodeQuestionId, QuestionNode target);

    SurveyNode getNext( RespondentSurveyContext respondentSurveyContext);

    SurveyNode getPrevious( RespondentSurveyContext surveyContext);

    SurveyNode getEndNode();

    SurveyStartNode getStartNode();

    void deleteNode(int nodeIdToDelete);

    void insertQuestionNode(int beforeQuestionId, QuestionNode questionNode, int afterQuestionNodeId);

    void addToIndex(QuestionNode question);

    QuestionNode getQuestionNode(int fromNodeQuestionId);

    boolean isNodePresent(Integer questionId);

    Map<Integer, QuestionNode> getSurveyNodes();
}
