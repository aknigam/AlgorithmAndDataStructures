package com.survey.node;

import com.survey.LinkEdge;
import com.survey.RespondentSurveyContext;

import java.util.List;

/**

   Created by a.nigam on 03/03/17.


 progressing through the survey

 1. Get next question from the survey and respond to it
 2. If the survey has changed then if the change is in previous node then take the user there as impacts the path
 3. If the change is in future then need not to worry. While traversing we can show it highlighted so that respondent
    can edit the response (only if user responded to it earlier). Can be handled by version property.


 Resume case handling

 1. What questions have been updated after the user saw the survey?
 2. Are these questions seen/answered by the respondent ?
 3. If yes does any of these questions come before the current question?
 4. If yes then take the user to this question


 Various business rules that impacts the shape of the DAG

   1. Skip logic -
    Skip logic determines the next question respondents will see after the current question. The respondents will
    automatically go to the designated question, skipping all questions in between.

   2. Branch Logic
    Branch logic determines the next question respondents will see based on their answer to the current question.
    For example, respondents who answer "A" will go to Question 14, while respondents who answer "B" will go to
    Question 23.

   3. Advanced logic
    a. Link Options
        Use the answer to a source question to determine which choices are displayed in this question:

    b. Masking Settings
        Mask the choices of this question with the choices of a source question:
            No
            Yes, display the choices that the respondent selected.
            Yes, display the choices that the respondent did not select.

   4. Sub question
        questions shown based on the answer to some other question. E.g if a particular choice in a question is selected
        then show the sub questions

   5. Hide question
        hidden questions are not visible to the respondends. They can still be responded to by planner

   6. Visibility logic
        Question shown/hidden based on a lot of criteria like contact custme field, answer to a question etc


 **/
public interface SurveyNode {

    int getId();

    void addDirectedEdge(LinkEdge linkEdge);

    SurveyNode getNextActiveNode(RespondentSurveyContext context);

    List<LinkEdge> getAllPossibleNextNodes();

    List<LinkEdge> getAllPossibleBackNodes();

    boolean isVisited();

    void setVisited(boolean visited) ;

    SurveyNode getPredecessorTreeNode();

    void setPredecessorTreeNode(SurveyNode node);

    String getName();

    void addBackEdge(LinkEdge linkEdge);

    void removeDirectedEdge(int nodeIdToDelete);

    void removeBackEdge(int nodeIdToDelete);


    void setActive(boolean b);

    boolean isActive();
}
