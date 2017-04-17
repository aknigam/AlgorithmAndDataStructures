package com.survey;

import com.survey.node.AbstractSurveyNode;
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


    public void addQuestionNode(QuestionNode question){

        SurveyNode startNode= getStartNode();
        LinkEdge link = new LinkEdge(startNode, question);
        startNode.addDirectedEdge(link);
        question.addBackEdge(link);
        addToIndex(question);

    }

    public void addQuestionNode(QuestionNode source, QuestionNode target, String choice){

        int fromNodeQuestionId = source.getId();
        if(!isNodePresent(fromNodeQuestionId)){
            System.out.println(String.format("Node %d cannot be added becuase the source does not exists.", fromNodeQuestionId));
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

    public void addQuestionNode(QuestionNode source, QuestionNode target){
        addQuestionNode(source.getId(), target);
    }
    public void addQuestionNode(int fromNodeQuestionId, QuestionNode target){

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


        if(node != null) { // node is present in survey not in chapter
            SurveyNode nextNode = node.getNext(respondentSurveyContext);

            if (nextNode != null) {
                return nextNode;
            }
            Chapter chapter = node.getChapter();
            if (chapter != null) {
                return chapter.getNext(respondentSurveyContext);
            }
        }
        // if node is not in survey , rather it is chapter
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

    public SurveyNode getPrevious( RespondentSurveyContext surveyContext){
        int currentQuestionId = surveyContext.getCurrentQuestionId();
        SurveyNode node = getSurveyNodes().get(currentQuestionId);
        List<LinkEdge> backEdges = node.getAllPossibleBackNodes();
        if(backEdges.size() == 1){
            return backEdges.get(0).getSource();
        }
        for (LinkEdge e: backEdges){
            SurveyNode possiblePreviousNode = e.getSource();

            // version check will have to be put here
            if(surveyContext.hasRespondentAnswered(possiblePreviousNode)){
                return possiblePreviousNode;
            }
        }
        return getStartNode();

    }



    public void deleteNode(int nodeIdToDelete){

        SurveyNode nodeToDelete =getSurveyNodes().get(nodeIdToDelete);
        if( nodeToDelete ==  null){
            System.out.println("Node cannot be deleted because it does not exists.");
            return;
        }

        for(LinkEdge e: nodeToDelete.getAllPossibleBackNodes()){
            e.setActive(false);
        }

        for(LinkEdge e: nodeToDelete.getAllPossibleNextNodes()){
            e.setActive(false);
        }
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