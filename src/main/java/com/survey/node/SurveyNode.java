package com.survey.node;

import com.survey.LinkEdge;
import com.survey.RespondentSurveyContext;

import java.util.List;

/**
 * Created by a.nigam on 03/03/17.
 */
public interface SurveyNode {

    int getId();

    void addDirectedEdge(LinkEdge linkEdge);

    SurveyNode getNext(RespondentSurveyContext context);

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
