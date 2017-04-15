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
 */

public class Survey extends QuestionContainer{


    private SurveyStartNode startNode = new SurveyStartNode();
    private SurveyEndNode endNode = new SurveyEndNode();

    public SurveyEndNode getEndNode() {
        return endNode;
    }

    public SurveyStartNode getStartNode() {
        return startNode;
    }


    public void addChapter(int fromNodeQuestionId, Chapter chapter) {

        if(!isNodePresent(fromNodeQuestionId)){
            System.out.println(String.format("Node %d cannot be added becuase the source does not exists.", fromNodeQuestionId));
            return;
        }
        QuestionNode sourceNode = getQuestionNode(fromNodeQuestionId);
        chapter.setPreviousSurveyNode(sourceNode);
        sourceNode.addChapterLink(chapter);


    }


    public static void main(String[] args) throws IOException {
        UUID uuid = UUID.randomUUID();

        String s = uuid.toString();
        int noOfBytes = s.length()*2;
        System.out.println(s+" - "+ noOfBytes);

        FileWriter w = new FileWriter("UUID2.txt");

        for (int i = 0; i < 1; i++) {
            s = uuid.toString();
            w.write(s);
        }

        w.close();



    }
}
