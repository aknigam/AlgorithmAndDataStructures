package com.survey.node;

import com.survey.Chapter;
import com.survey.QuestionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.nigam on 16/01/17.
 */
public class QuestionNode extends AbstractSurveyNode{

    int index;

    String question;

    List<String> choices = new ArrayList<>();

    int chapterId;
    String chapterLoopValue;
    private String chapterName;

    public int getIndex() {
        return index;
    }

    private QuestionType questionType = QuestionType.SINGLE_CHOICE;

    public Chapter getChapter() {
        return chapter;
    }

    private Chapter chapter;

    @Override
    public String toString() {
//        String visited = isVisited()? "v" : "";
//        String predecessor = getPredecessorTreeNode()==null?"": getPredecessorTreeNode().getName();
//        return question+ "("+predecessor+ ")" ;
        return question;
    }

    public QuestionNode(int id){
        super(id);

    }

    public QuestionNode(String question, int id){
        super(id);
        this.question = question;
    }

    public String getName(){
        return question;
    }


    public void addChoice(String x) {
        choices.add(x);
    }

    public void addChapterLink(Chapter chapter) {
        this.chapter =  chapter;
    }

    public List<String> getChoices() {
        return choices;
    }

    public String getChapterLoopValue() {
        return chapterLoopValue;
    }

    public void setChapterLoopValue(String chapterLoopValue) {
        this.chapterLoopValue = chapterLoopValue;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
}
