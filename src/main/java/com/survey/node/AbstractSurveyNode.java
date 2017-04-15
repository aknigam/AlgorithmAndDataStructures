package com.survey.node;

import com.survey.LinkEdge;
import com.survey.RespondentSurveyContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.nigam on 03/03/17.
 */
public abstract class AbstractSurveyNode implements SurveyNode {

    List<LinkEdge> edges;
    List<LinkEdge> backEdges;
    int id;
    boolean visited = false;

    public int getId(){
        return id;
    }

    AbstractSurveyNode(int id){
        this.id = id;
        edges = new ArrayList<>();
        backEdges = new ArrayList<>();
    }


    public void removeDirectedEdge(int nodeIdToDelete){
        for(LinkEdge e: edges){
            if(e.getTarget().getId() == nodeIdToDelete){
                edges.remove(e);
                return;
            }
        }
    }

    public void removeBackEdge(int nodeIdToDelete){

        for(LinkEdge e: backEdges){

            if(e.getTarget().getId() == nodeIdToDelete){
                backEdges.remove(e);
                return;
            }
        }
    }

    public void addDirectedEdge(LinkEdge linkEdge){
        edges.add(linkEdge);
        linkEdge.getTarget().addBackEdge(new LinkEdge(this));
    }

    @Override
    public void addBackEdge(LinkEdge backEdge) {
        backEdges.add(backEdge);
    }

    @Override
    public SurveyNode getNext(RespondentSurveyContext context) {
        return getAllPossibleNextNodes().get(0).getTarget();
    }

    @Override
    public List<LinkEdge> getAllPossibleNextNodes() {
        return edges;
    }

    @Override
    public List<LinkEdge> getAllPossibleBackNodes() {
        return backEdges;
    }


    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    SurveyNode predecessorTreeNode;

    @Override
    public SurveyNode getPredecessorTreeNode() {
        return predecessorTreeNode;
    }

    @Override
    public void setPredecessorTreeNode(SurveyNode node) {
        predecessorTreeNode = node;
    }

    public String getName(){
        return "";
    }

    @Override
    public String toString() {
        return getName();
    }
}
