package com.survey;

import com.survey.node.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by a.nigam on 16/01/17.
 *
 * Survey is a Graph itself. It resembles more with the AdjancyList representation.
 *
 * Content of the a survey:
 * 1. Ordered list of surveyNodes
 * 2. Chapters (Looped or simple)
 *
 *
 *
 *
 *
 */

public class Survey extends QuestionContainer{

    public int getVersion() {
        return version;
    }

    // Every modification of the survey should change the version
    private int version;

    private SurveyStartNode startNode = new SurveyStartNode();
    private SurveyEndNode endNode = new SurveyEndNode();

    private boolean testing = true;

    public boolean inTesting(){
        return testing;
    }

    public void goLive(){
        testing = false;
    }

    public Survey(){
        version = 1;
    }



    public SurveyNode getNext(RespondentSurveyContext respondentSurveyContext){
        return getNext(respondentSurveyContext, version);
    }

    public SurveyEndNode getEndNode() {
        return endNode;
    }

    public SurveyStartNode getStartNode() {
        return startNode;
    }


    public void addChapter(QuestionNode fromNodeQuestion, Chapter chapter) {

        int fromNodeQuestionId = fromNodeQuestion.getId();
        if(!isNodePresent(fromNodeQuestionId)){
            System.out.println(String.format("Node %distance cannot be added becuase the source does not exists.", fromNodeQuestionId));
            return;
        }
        QuestionNode sourceNode = getQuestionNode(fromNodeQuestionId);
        chapter.setPreviousSurveyNode(sourceNode);
        sourceNode.addChapterLink(chapter);
        chaptersIndex.put(chapter.getId(), chapter);


    }

}
