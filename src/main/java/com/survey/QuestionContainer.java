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
public abstract class QuestionContainer implements QuestionContainerI{

    protected Map<Integer, Chapter> chaptersIndex = new HashMap<>();
    private Map<Integer, QuestionNode> surveyNodes =  new HashMap<>();

    public void addToIndex(QuestionNode question) {
        surveyNodes.put(question.getId(), question);
    }

    public abstract SurveyStartNode getStartNode();


    public QuestionNode getQuestionNode(int fromNodeQuestionId) {
        return surveyNodes.get(fromNodeQuestionId);
    }


    public boolean isNodePresent(Integer questionId) {
        return surveyNodes.get(questionId) != null;
    }


    public Map<Integer, QuestionNode> getSurveyNodes() {
        return surveyNodes;
    }


    public void addFirstQuestionNode(QuestionNode question){

        SurveyNode startNode= getStartNode();
        LinkEdge link = new LinkEdge(startNode, question);
        startNode.addDirectedEdge(link);
        question.addBackEdge(link);
        addToIndex(question);

    }

    public void addNextChoiceLinkedQuestionNode(QuestionNode source, QuestionNode target, String choice){

        int fromNodeQuestionId = source.getId();
        if(!isNodePresent(fromNodeQuestionId)){
            System.out.println(String.format("Node %d cannot be added because the source does not exists.", fromNodeQuestionId));
            return;
        }
        QuestionNode sourceNode = getQuestionNode(fromNodeQuestionId);

        if(!isNodePresent(target.getId())){
            addToIndex(target);
        }
        target = getQuestionNode(target.getId());
        LinkEdge link =new LinkEdge(sourceNode , target, choice);
        sourceNode.addDirectedEdge(link);
        target.addBackEdge(link);

    }

    public void addNextQuestionNode(QuestionNode source, QuestionNode target){
        addNextQuestionNode(source.getId(), target);
    }
    public void addNextQuestionNode(int fromNodeQuestionId, QuestionNode target){

        if(!isNodePresent(fromNodeQuestionId)){
            System.out.println(String.format("Node %d cannot be added becuase the source does not exists.", fromNodeQuestionId));
            return;
        }
        QuestionNode sourceNode = getQuestionNode(fromNodeQuestionId);

        if(!isNodePresent(target.getId())){
            addToIndex(target);
        }
        target = getQuestionNode(target.getId());

        LinkEdge link = new LinkEdge( sourceNode, target);
        sourceNode.addDirectedEdge(link);
        target.addBackEdge(link);

    }


    public SurveyNode getNext(RespondentSurveyContext respondentSurveyContext){

        int currentQuestionId = respondentSurveyContext.getCurrentQuestionId();
        if(currentQuestionId == 0)
            return getStartNode().getNext(respondentSurveyContext);

        QuestionNode node = getSurveyNodes().get(currentQuestionId);
        /*
        if(!node.isActive()){
            // hanldle this case
        }
        */
        if(node != null) { // node is present in survey not in chapter
            SurveyNode nextNode = node.getNext(respondentSurveyContext);

            if (nextNode != null) {
                return nextNode;
            }
            Chapter chapter = node.getChapter();
            if (chapter != null) {
                // chapter starts here
                return chapter.getNext(respondentSurveyContext);
            }
        }
        // if node is not in survey , rather it is chapter. This means user is already in the chapter
        if(respondentSurveyContext.getCurrentChapterId() != 0 && respondentSurveyContext.getChapterLoopValue() != null){

            int chapterId = respondentSurveyContext.getCurrentChapterId();

            Chapter chapter = chaptersIndex.get(chapterId);
            if(chapter == null){
                System.out.println("Error: invalid chapterId - "+ chapterId);
                return null;
            }

            return chapter.getNext(respondentSurveyContext);
        }

        return getEndNode();
    }

    public abstract SurveyNode getEndNode();

    // TODO: 17/04/17 do the implementation for this.
    public SurveyNode getPrevious( RespondentSurveyContext surveyContext){
        return getPrevious(surveyContext, surveyContext.getCurrentQuestionId());
    }
    public SurveyNode getPrevious( RespondentSurveyContext surveyContext, int currentQuestionId){
        SurveyNode prevNode = null;
        SurveyNode node = getSurveyNodes().get(currentQuestionId);
        List<LinkEdge> backEdges = node.getAllPossibleBackNodes();
        if(backEdges.size() == 1){

            prevNode =  backEdges.get(0).getSource();

        }
        else {
            for (LinkEdge e : backEdges) {
                SurveyNode possiblePreviousNode = e.getSource();

                // version check will have to be put here
                if (surveyContext.hasRespondentAnswered(possiblePreviousNode)) {
                    prevNode =  possiblePreviousNode;
                    break;
                }
            }
        }
        /*

         If there are more than one previous nodes then I will choose the node which the user answered.
         User cannot reach the current node without answering one of the possible paths.

         */

        if(prevNode != null && !prevNode.isActive()){
            return getPrevious(surveyContext, prevNode.getId());
        }
        return getStartNode();

    }


    /**
     *
     * Deleting a node does the following:
     *
     * 1. mark this node as deleted
     * 2. Mark front edges as inactive
     * 3. Mark back edges as inactive
     *
     * Planner should connect other edges accordingly. it won't happen automatically.
     *
     * Some of the possible things that can happen are as follow. These are <<<<1 & 2 are NOT YET IMPLEMENTED. Only 3 is implemented>>>>>
     *
     * 1. if there is only one edge emanating from the deleted node, then all the previous nodes get connected to the target by
     *  adding a new bi-directional edge with similar link logic
     *
     * 2. If there are multiple outgoing edges then planner needs to define the new paths manually
     *
     * 3. Following case can be handled without any planner input
     *
     *      A---------------> NodeToBeDeleted ----------------> C
     *      A-------------------------------------------------> C
     *
     * As a design we can keep a stack of operations so that undo can be supported.
     *
     */
    public void deleteNode(int nodeIdToDelete){

        SurveyNode nodeToDelete =getSurveyNodes().get(nodeIdToDelete);
        if( nodeToDelete ==  null)
        {
            System.out.println("Node cannot be deleted because it does not exists.");
            return;
        }
        List<LinkEdge> backEdges = nodeToDelete.getAllPossibleBackNodes();
        for(LinkEdge e: backEdges){
            e.setActive(false);
        }

        List<LinkEdge> forwardEdges= nodeToDelete.getAllPossibleNextNodes();
        for(LinkEdge e: forwardEdges){
            e.setActive(false);
        }

        if(forwardEdges.size() == 1 && backEdges.size() == 1){
            LinkEdge edge = backEdges.get(0);
            if(!edge.isLinkedLogicEdge()){
                SurveyNode target = forwardEdges.get(0).getTarget();
                LinkEdge link = new LinkEdge(edge.getSource(), target);
                edge.getSource().addDirectedEdge(link);
                target.addBackEdge(link);
            }
        }

        nodeToDelete.setActive(false);
        getSurveyNodes().remove(nodeIdToDelete);

    }

    // TODO: 02/04/17 this is incomplete
    public void insertQuestionNode(int beforeQuestionId, QuestionNode questionNode, int afterQuestionNodeId){

        if(getSurveyNodes().get(beforeQuestionId) == null){
            System.out.println("Node cannot be added because the before does not exists.");
            return;
        }

        if(getSurveyNodes().get(afterQuestionNodeId) == null){
            System.out.println("Node cannot be added because the after question does not exists.");
            return;
        }
        SurveyNode sourceNode = getSurveyNodes().get(beforeQuestionId);
        QuestionNode target = getSurveyNodes().get(questionNode.getId());
        if(target !=  null && target instanceof QuestionNode){
            questionNode = (QuestionNode) target;
        }
        LinkEdge link = new LinkEdge(sourceNode, target);
        sourceNode.addDirectedEdge(link);
        target.addBackEdge(link);
        getSurveyNodes().put(questionNode.getId(), target);
    }





}
