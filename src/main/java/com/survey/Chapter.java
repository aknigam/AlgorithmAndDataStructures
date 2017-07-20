package com.survey;

import com.survey.node.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by a.nigam on 02/04/17.
 */
public class Chapter extends QuestionContainer {

    public int getId() {
        return id;
    }

    private final int id;
    private final String chapterName;
    private QuestionNode loopQuestion;
    private SurveyStartNode chapterStartNode = new SurveyStartNode();
    private SurveyEndNode chapterEndNode = new SurveyEndNode();
    private SurveyNode previousSurveyNode;
    private SurveyNode nextSurveyNode;


    @Override
    public String toString() {
        return chapterName;
    }

    public Chapter(int id, String name) {
        this.id = id;
        this.chapterName = name;

    }

    /**
     * Use cases:
     * 1. User has answered at looping question
     * 2. User is in-between the chapter
     * 3. User is at the last question of the looped chapter
     * 4. User is at the last question of all the looped chapters
     */
    @Override
    public SurveyNode getNext(RespondentSurveyContext respondentSurveyContext) {

        int currentQuestionId = respondentSurveyContext.getCurrentQuestionId();
        String currentChapterLoopValue = respondentSurveyContext.getChapterLoopValue();


        QuestionNode nextNode = getNextLoopedChapterNode(currentQuestionId, currentChapterLoopValue, respondentSurveyContext);

        if (nextNode != null) {
            return nextNode;
        }

        // in the end if we have looped through all the chapters then get the survey node after the chapter.

        return getNextSurveyNode();

    }

    private QuestionNode getNextLoopedChapterNode(int currentQuestionId,String currentChapterLoopValue, RespondentSurveyContext respondentSurveyContext) {

        List<String> choices = loopQuestion.getChoices();
        if(currentChapterLoopValue != null && !choices.contains(currentChapterLoopValue)){
            System.out.println("ERROR: Invalid looped question answer.");
            return null;
        }

        QuestionNode currentNode = getSurveyNodes().get(currentQuestionId);

        // Case 1: Getting the next question after the user has answered the looping question
        if(currentNode == null) { // node does not exists in chapter. it may be the looping question
            return getNextQuestionIfUserJustAnsweredLoopingQuestion( currentQuestionId, respondentSurveyContext, currentChapterLoopValue);
        }

        // Case 2: node is present in the chapter
        if(currentChapterLoopValue == null){
            Log.error("Current chapter loop value is mandatory");
            return null;
        }
        QuestionNode questionNode = getNextQuestionInTheChapter(currentNode, respondentSurveyContext, currentChapterLoopValue);
        if(questionNode != null)
        {
            return questionNode;
        }

        // Case 3: more looped chapters exist?
        String nextLoopValue = getNextLoopValue(loopQuestion, currentChapterLoopValue, respondentSurveyContext );
        if(nextLoopValue != null)
            return getNextLoopedChapterNode(loopQuestion.getId(), nextLoopValue, respondentSurveyContext);

        // Case 4: User is at the last question of all the looped chapters
        return null;

    }

    private QuestionNode getNextQuestionInTheChapter(QuestionNode node, RespondentSurveyContext respondentSurveyContext, String currentChapterLoopValue) {
        List<LinkEdge> edges = node.getAllPossibleNextNodes();
        for (LinkEdge e: edges){
            if(!(e.getTarget() instanceof QuestionNode)){
                return null;
            }
            else if(e.evaluate(respondentSurveyContext)){
                SurveyNode target = e.getTarget();
                if(target instanceof QuestionNode){
                    QuestionNode questionNode = (QuestionNode) target;
                    questionNode.setChapterId(id);
                    questionNode.setChapterName(chapterName);
                    questionNode.setChapterLoopValue(currentChapterLoopValue);
                    return questionNode;
                }
            }
        }
        return null;
    }

    /**
     * Current question node is not present in the chapter
     */
    private QuestionNode getNextQuestionIfUserJustAnsweredLoopingQuestion( int currentQuestionId, RespondentSurveyContext respondentSurveyContext, String currentChapterLoopValue) {


        if (loopQuestion.getId() == currentQuestionId) { // it is the looping questions
            List<String> loopQuestionAnswer = respondentSurveyContext.getAnswertoMultiChoiceQuestion(loopQuestion);
            if (loopQuestionAnswer == null || loopQuestionAnswer.size() == 0) {
                // user did not provided any answer on which chapter looping can be done
                Log.error("No answer provided to looping question so can't proceed with chapter");
                return null; // no looped chapter . may be we should return the question after the chapter.
            }
            SurveyNode firstQuestionNodeInChapter = getStartNode().getNext(respondentSurveyContext);
            if (firstQuestionNodeInChapter instanceof QuestionNode) {
                QuestionNode qn = (QuestionNode) firstQuestionNodeInChapter;
                if (currentChapterLoopValue == null) {
                    qn.setChapterLoopValue(loopQuestionAnswer.get(0));
                } else {
                    // TODO: redundant. Else should never be executed as this is use case 1, which means chapter has just started
                    qn.setChapterLoopValue(currentChapterLoopValue);
                }
                // TODO: 19/07/17 should not be modifying the node. Instead set the properties in the context directly.
                // TODO: Another option is to clone the node.
                qn.setChapterId(id);
                qn.setChapterName(chapterName);
                return qn;
            } else {
                // TODO: 19/07/17 handle the case when the next node is not question node. It can be chapter end node or survey end node
                Log.error("Chapter has no questions. Start node does not points to any question node");
                return null;
            }
        } else {
            // node not present in chapter and its not the looping question as well
            Log.error("Current node is not the looping question so the chapter cannot be started");
            return null;
        }




    }

    private String getNextLoopValue(QuestionNode loopQuestion, String currentChapterLoopValue, RespondentSurveyContext respondentSurveyContext) {
        List<String> loopQuestionAnswer = respondentSurveyContext.getAnswertoMultiChoiceQuestion(loopQuestion);
        if(loopQuestionAnswer == null || loopQuestionAnswer.size() == 0){
            return null;
        }
        Iterator<String> itr = loopQuestionAnswer.iterator();

        while (itr.hasNext()){
            String choice = itr.next();
            if(choice.equalsIgnoreCase(currentChapterLoopValue)){
                if(itr.hasNext()){
                    return itr.next();
                }
            }
        }

        return null;
    }

    @Override
    public SurveyNode getEndNode() {
        return chapterEndNode;
    }

    @Override
    public SurveyStartNode getStartNode() {
        return chapterStartNode;
    }

    public void setLoopQuestion(QuestionNode loopQuestion) {
        this.loopQuestion = loopQuestion;
    }

    public void setPreviousSurveyNode(SurveyNode sourceNode) {
        this.previousSurveyNode = sourceNode;
    }

    public SurveyNode getNextSurveyNode() {
        return nextSurveyNode;
    }

    public void setNextSurveyNode(SurveyNode nextSurveyNode) {
        this.nextSurveyNode = nextSurveyNode;
    }







}
