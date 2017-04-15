package com.survey;

import com.survey.node.QuestionNode;
import com.survey.node.SurveyEndNode;
import com.survey.node.SurveyNode;
import com.survey.node.SurveyStartNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a.nigam on 02/04/17.
 */
public abstract class QuestionContainer implements QuestionContainerI{

    public void addQuestionNode(QuestionNode question){

        getStartNode().addDirectedEdge(new LinkEdge(question));

        addToIndex(question);

    }


    public void addQuestionNode(int fromNodeQuestionId, QuestionNode target){

        if(!isNodePresent(fromNodeQuestionId)){
            System.out.println(String.format("Node %d cannot be added becuase the source does not exists.", fromNodeQuestionId));
            return;
        }
        SurveyNode sourceNode = getQuestionNode(fromNodeQuestionId);

        if(!isNodePresent(target.getId())){
            addToIndex(target);
        }
        sourceNode.addDirectedEdge(new LinkEdge(getQuestionNode(target.getId())));

    }


    public SurveyNode getNext(RespondentSurveyContext respondentSurveyContext){

        int currentQuestionId = respondentSurveyContext.getCurrentQuestionId();

        QuestionNode node = getSurveyNodes().get(currentQuestionId);
        if(respondentSurveyContext.getCurrentChapterId() != 0 && respondentSurveyContext.getChapterLoopValue() != null){


            Chapter chapter = null;
            if(node == null){
                chapter =  SurveyUtil.chapter;
            }
            else
            {
                chapter = node.getChapter();
            }
            // bug
            return chapter.getNext(respondentSurveyContext);
        }

        if(currentQuestionId == 0)
            return getStartNode().getNext(respondentSurveyContext);


        List<LinkEdge> edges = node.getAllPossibleNextNodes();
        for (LinkEdge e: edges){
            if(e.evaluate(respondentSurveyContext)){
                return e.getTarget();
            }
        }
        Chapter chapter = node.getChapter();
        if(chapter !=  null){
            return chapter.getNext(respondentSurveyContext);
        }
        return getEndNode();
    }

    public abstract SurveyNode getEndNode();

    public SurveyNode getPrevious( RespondentSurveyContext surveyContext){
        int currentQuestionId = surveyContext.getCurrentQuestionId();
        SurveyNode node = getSurveyNodes().get(currentQuestionId);
        List<LinkEdge> edges = node.getAllPossibleBackNodes();
        if(edges.size() == 1){
            return edges.get(0).getTarget();
        }
        for (LinkEdge e: edges){
            SurveyNode possiblePreviousNode = e.getTarget();

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
            e.getTarget().removeDirectedEdge(nodeIdToDelete);
        }

        for(LinkEdge e: nodeToDelete.getAllPossibleNextNodes()){
            e.getTarget().removeBackEdge(nodeIdToDelete);
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
        sourceNode.addDirectedEdge(new LinkEdge(target));
        getSurveyNodes().put(questionNode.getId(), target);
    }




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
}
