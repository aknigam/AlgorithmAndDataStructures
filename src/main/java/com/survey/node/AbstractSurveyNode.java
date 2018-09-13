package com.survey.node;

import com.survey.LinkEdge;
import com.survey.RespondentSurveyContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by a.nigam on 03/03/17.
 */
public abstract class AbstractSurveyNode implements SurveyNode {

    List<LinkEdge> edges;
    List<LinkEdge> backEdges;
    int id;
    boolean visited = false;
    private boolean active = true;

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

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean status) {
        active = status;
    }

    public void addDirectedEdge(LinkEdge linkEdge){
        edges.add(linkEdge);

    }

    @Override
    public void addBackEdge(LinkEdge backEdge) {
        backEdges.add(backEdge);
    }

    /*
    This method either returns an active node or a null. it does not returns inactive node
     */
    @Override
    public SurveyNode getNextActiveNode(RespondentSurveyContext context) {

        for ( LinkEdge e : edges) {
            if(e.isActive() && e.evaluate(context)){
                // Can the target be inactive even if the edge was active? No
                return e.getTarget();
            }
        }
//        if(edges.size() == 1 && edges.get(0).isActive()){
//            return edges.get(0).getTarget();
//        }
        return null;
    }



    @Override
    public List<LinkEdge> getAllPossibleNextNodes() {

        List<LinkEdge> fe = new ArrayList<>(edges.size());
        for (LinkEdge e : edges) {
            if(e.isActive()){
                fe.add(e);
            }
        }
        return fe;

    }

    @Override
    public List<LinkEdge> getAllPossibleBackNodes() {
        return Collections.unmodifiableList(backEdges);
        /*
        List<LinkEdge> be = new ArrayList<>();
        for (LinkEdge e : backEdges) {
            if(e.isActive()){
                be.add(e);
            }
        }

        return be;
        */
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
