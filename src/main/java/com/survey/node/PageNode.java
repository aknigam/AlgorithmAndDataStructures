package com.survey.node;

import com.survey.Chapter;
import com.survey.LinkEdge;
import com.survey.RespondentSurveyContext;

import java.util.ArrayList;
import java.util.List;

public class PageNode extends AbstractSurveyNode{

    private String name;

    private List<QuestionNode> questions = new ArrayList<>();
    private Chapter nextChapter;
    private PageNode nextPage;

    public PageNode(int id, String name) {
        super(id);
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id +"- "+ name;
    }

    public void addQuestion(QuestionNode q) {
        questions.add(q);
    }

    public List<QuestionNode> getQuestions() {
        return questions;
    }

    public void addChapterLink(Chapter chapter) {
        this.nextChapter = chapter;
    }

    public Chapter getChapter() {
        return nextChapter;
    }

    public void setNext(PageNode page2) {
        nextPage = page2;
    }

    @Override
    public String getName() {
        return name;
    }
}
