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
//    private boolean chapterStarted = true;

    public Chapter(int id, String name) {
        this.id = id;
        this.chapterName = name;

    }

    /**
     * Use cases:
     * 1. User has answered at looping question
     * 2. User is inbetween the chapter
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
        QuestionNode node = getSurveyNodes().get(currentQuestionId);
        SurveyNode nextNode = null;
        if(node == null){ // node does not exists in chapter. it may be the looping question

            if(loopQuestion.getId() == currentQuestionId){ // it is the looping questions
                List<String> loopQuestionAnswer = respondentSurveyContext.getAnswertoMultiChoiceQuestion(loopQuestion);
                if(loopQuestionAnswer == null || loopQuestionAnswer.size() == 0){
                    return null; // no looped chapter . may be we should return the question after the chapter.
                }
                nextNode = getStartNode().getNext(respondentSurveyContext);
                if(nextNode instanceof QuestionNode)
                {
                    QuestionNode qn = (QuestionNode) nextNode;
                    if(currentChapterLoopValue == null) {
                        qn.setChapterLoopValue(loopQuestionAnswer.get(0));
                    }else {
                        qn.setChapterLoopValue(currentChapterLoopValue);
                    }
                    qn.setChapterId(id);
                    qn.setChapterName(chapterName);
                    return qn;
                }
            }
            else{
                // node not present in chapter and its not the looping question as well
                return null;
            }


        }

        // node is present in the chapter
        if(currentChapterLoopValue == null){
            System.out.println("ERROR: Current chapter loop value is mandatory");
            return null;
        }
        List<LinkEdge> edges = node.getAllPossibleNextNodes();
        for (LinkEdge e: edges){
            if(e.evaluate(respondentSurveyContext)){
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
        // more looped chapters exist?

        String nextLoopValue = getNextLoopValue(loopQuestion, currentChapterLoopValue, respondentSurveyContext );
        if(nextLoopValue != null)
            return getNextLoopedChapterNode(loopQuestion.getId(), nextLoopValue, respondentSurveyContext);

        return null;

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
