package com.survey;

import com.survey.node.PageNode;
import com.survey.node.SurveyNode;
import com.survey.node.SurveyStartNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: Node CRUD operations have to be atomic in nature
 */
public abstract class QuestionContainer implements QuestionContainerI {


    protected QuestionContainer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private final int id;



    protected Map<Integer, Chapter> chaptersIndex = new HashMap<>();
    private Map<Integer, PageNode> pageNodes = new HashMap<>();
    private Map<Integer, List<PageNode>> pages = new LinkedHashMap<>();

    public void addToIndex(PageNode question) {
        pageNodes.put(question.getId(), question);
    }

    public abstract SurveyStartNode getStartNode();


    public PageNode getPageNode(int fromNodeQuestionId) {
        return pageNodes.get(fromNodeQuestionId);
    }


    public boolean isNodePresent(Integer questionId) {
        return pageNodes.get(questionId) != null;
    }


    public Map<Integer, PageNode> getPageNodes() {
        return pageNodes;
    }


    protected SurveyNode getNext(RespondentSurveyContext respondentSurveyContext, int surveyVersion) {


        if (hasRespondentJustStartedSurvey( getStatus( respondentSurveyContext))) {
            // TODO: 12/09/18 change this
            if(!isChapter()) {
                respondentSurveyContext.setRespondentStatus(RespondentSurveyContext.RespondentStatus.IN_PROGRESS);
            } else {
                respondentSurveyContext.setRespondentChapterStatus(getId(), RespondentSurveyContext.RespondentStatus
                        .IN_PROGRESS);
            }
            return getStartNode().getNextActiveNode(respondentSurveyContext);
        }

//        int currentQuestionId = respondentSurveyContext.getCurrentQuestionId();
        int currentPageId = respondentSurveyContext.getCurrentPage();
        PageNode currentPage = getPage(currentPageId);

        if (currentPage != null) {

                SurveyNode nextNode = currentPage.getNextActiveNode(respondentSurveyContext);

                if (nextNode != null) {
                    return nextNode;
                }
                Chapter chapter = currentPage.getChapter();
                if (chapter != null) {
                    // chapter starts here
                    respondentSurveyContext.setCurrentChapterId(chapter.getId()); // todo this prevent nexted chapters
                    return chapter.getNext(respondentSurveyContext);
                }
                return null;


        }
        // if currentNode is not null then else should not be executed
        // if node is not in survey , rather it is chapter. This means user is already in the chapter
        else if (respondentSurveyContext.getCurrentChapterId() != 0 && respondentSurveyContext.getChapterLoopValue() != null) {

            int chapterId = respondentSurveyContext.getCurrentChapterId();

            Chapter chapter = chaptersIndex.get(chapterId);
            if (chapter == null) {
                System.out.println("Error: invalid chapterId - " + chapterId);
                return null;
            }

            return chapter.getNext(respondentSurveyContext);
        }
        else {
            return getEndNode();
        }

    }

    protected boolean isChapter() {
        return false;
    }

    protected RespondentSurveyContext.RespondentStatus getStatus(RespondentSurveyContext respondentSurveyContext){
        return respondentSurveyContext.getRespondentStatus();
    }

    private PageNode getPage(int currentPageId) {
        return pageNodes.get(currentPageId);
    }

    private List<? extends SurveyNode> getAllPageQuestions(int page) {
        return pages.get(page);
    }


    protected boolean hasRespondentJustStartedSurvey(RespondentSurveyContext.RespondentStatus status) {
        return status == RespondentSurveyContext.RespondentStatus.NEW;
    }


    public abstract SurveyNode getEndNode();



    public boolean isPageWithinContainer(int page) {
        return pages.get(page) != null;
    }

    protected boolean doesPageComesBefore(int currentPage) {
        List<Integer> l = new ArrayList<>(pageNodes.keySet());
        Collections.sort(l);
        return l.get(0) > currentPage;
    }


    private PageNode lastPage;

    public void addPage(PageNode page) {
        if(pageNodes.size() == 0 ) {
            LinkEdge edge = new LinkEdge(getStartNode(), page);
            getStartNode().addDirectedEdge(edge);
            lastPage = page;
        }
        else {
            lastPage.addDirectedEdge(new LinkEdge(lastPage, page));
        }
        pageNodes.put(page.getId(), page);

    }
}
