package com.survey;

import com.survey.node.*;

import java.util.List;

/**
 * Created by a.nigam on 02/04/17.
 */
public class Chapter extends QuestionContainer {

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

    @Override
    public SurveyNode getNext(RespondentSurveyContext respondentSurveyContext) {

        int currentQuestionId = respondentSurveyContext.getCurrentQuestionId();
        String currentChapterLoopValue = respondentSurveyContext.getChapterLoopValue();
        List<String> choices = loopQuestion.getChoices();
        boolean chapterStarted = false;
        if(currentChapterLoopValue == null){
            currentChapterLoopValue = choices.get(0);
            chapterStarted = true;
        }

        SurveyNode nextNode = getNextLoopedChapterNode(currentQuestionId, currentChapterLoopValue, respondentSurveyContext);
        if (nextNode != null) {
            return nextNode;
        }
        // reached the end of the looped chapter. get the start question node of the next looped chapter


        boolean found = false;

        for (String c : choices) {
            if (c.equals(currentChapterLoopValue)) {
                if(chapterStarted){
                    return getStartNode().getNext(respondentSurveyContext);
                }
                nextNode = getNextLoopedChapterNode(currentQuestionId, c, respondentSurveyContext);
                if (nextNode != null) {
                    return nextNode;
                }else{
                    chapterStarted = true;
                }
            }
        }

        if (nextNode != null) {
            return nextNode;
        }

        // in the end if we have looped through all the chapters then get the survey node after the chapter.

        return getNextSurveyNode();

    }

    private SurveyNode getNextLoopedChapterNode(int currentQuestionId, String currentChapterLoopValue, RespondentSurveyContext respondentSurveyContext) {
        QuestionNode node = getSurveyNodes().get(currentQuestionId);
        if(node == null){
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
                    return questionNode;
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
