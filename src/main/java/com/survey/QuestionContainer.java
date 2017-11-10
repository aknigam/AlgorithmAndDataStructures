package com.survey;

import com.survey.node.QuestionNode;
import com.survey.node.SurveyNode;
import com.survey.node.SurveyStartNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: Node CRUD operations have to be atomic in nature
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
            System.out.println(String.format("Node %d cannot be added because the source does not exists.", fromNodeQuestionId));
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

        if(hasRespondentStartedSurvey(respondentSurveyContext)){
            respondentSurveyContext.setRespondentStatus(RespondentSurveyContext.RespondentStatus.IN_PROGRESS);
            return getStartNode().getNextActiveNode(respondentSurveyContext);
        }

        int currentQuestionId = respondentSurveyContext.getCurrentQuestionId();
        QuestionNode currentNode = getSurveyNodes().get(currentQuestionId);


        if(currentNode != null) {

            /*
            Case 1: The current node is not active
                a) Node may or may not belong to a chapter
            TODO: This is not completely implemented
            */
            if (!currentNode.isActive()) {
                // Handle this case
            /*
             Where should the user start from if the current node is deleted?
             Option 1: Go back in the respondents path and stop at a position when you find an active node to which user
              responded or visited earlier. *****And then go to next???****
             Option 2: There may be changes in the survey even before the node found by option 1. Or changes in a
             different path.
             Option 3: mark the path and

             If the change is such that user may choose a different path then the user has to be brought back to the
             starting point


             1. if there is only one previous node then check the question that user actually answered

             */
                // get previous active node which the user has responded or return the start node.
                return getPrevious(respondentSurveyContext, currentQuestionId);
                /*
                List<LinkEdge> backEdges = currentNode.getAllPossibleBackNodes(); //bug none of the back edges will be
                // active
                if(backEdges == null || backEdges.size() == 0){
                    return getStartNode().getNextActiveNode(respondentSurveyContext);
                }
                SurveyNode nextNode = getPreviousActiveNodeInUserPath(backEdges, respondentSurveyContext);
                */


            }
            // Case 2: current node is active
            else { // node is present in survey not in chapter
                SurveyNode nextNode = currentNode.getNextActiveNode(respondentSurveyContext);

                if (nextNode != null) {
                    return nextNode;
                }
                Chapter chapter = currentNode.getChapter();
                if (chapter != null) {
                    // chapter starts here
                    respondentSurveyContext.setCurrentChapterId(chapter.getId()); // todo this prevent nexted chapters
                    return chapter.getNext(respondentSurveyContext);
                }
                return null;

            }
        }
        // if currentNode is not null then else should not be executed
        // if node is not in survey , rather it is chapter. This means user is already in the chapter
        else if (respondentSurveyContext.getCurrentChapterId() != 0 && respondentSurveyContext.getChapterLoopValue() != null){

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

    protected boolean hasRespondentStartedSurvey(RespondentSurveyContext respondentSurveyContext){
        return  respondentSurveyContext.getRespondentStatus() == RespondentSurveyContext.RespondentStatus.NEW;
    }


    public abstract SurveyNode getEndNode();

    //
    public SurveyNode getPrevious( RespondentSurveyContext surveyContext){
        return getPrevious(surveyContext, surveyContext.getCurrentQuestionId());
    }
    public SurveyNode getPrevious( RespondentSurveyContext surveyContext, int currentQuestionId){

        SurveyNode node = getSurveyNodes().get(currentQuestionId);
        List<LinkEdge> backEdges = node.getAllPossibleBackNodes();

        if(backEdges == null || backEdges.isEmpty()) {
            return getStartNode();
        }
        /*

        Note: if more than 1 active nodes are found in previous which were answered by the user then return the latest
        answered node.


         */
        SurveyNode possiblePreviousNode = null;
        for (LinkEdge e : backEdges) {
            possiblePreviousNode = e.getSource();

            if (surveyContext.hasRespondentAnswered(possiblePreviousNode)) {
                // return if the previous node and the path are active
                if(!possiblePreviousNode.isActive() || !e.isActive()){
                    return getPrevious(surveyContext, possiblePreviousNode.getId());
                }
                return possiblePreviousNode;

            }
        }

        /*

         If there are more than one previous nodes then I will choose the node which the user answered.
         User cannot reach the current node without answering one of the possible paths.
        */

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
//        getSurveyNodes().remove(nodeIdToDelete);

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
