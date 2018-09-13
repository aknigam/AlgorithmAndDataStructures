package com.survey;

import com.survey.node.QuestionNode;
import com.survey.node.SurveyNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by a.nigam on 16/04/17.
 */
public class ChapterTest {


    private Survey survey;
    private Chapter chapter;
    private QuestionNode a;
    private QuestionNode b;
    private QuestionNode c;
    private QuestionNode d;

    @Test
    public void testGetNext(){

        prepare();

        SurveyNode nextNode = null;
        QuestionNode nextQuestionNode = null;

        // case: 1 looped question (distance) answered
        RespondentSurveyContext context = new RespondentSurveyContext(d.getId());
        context.setRespondentStatus(RespondentSurveyContext.RespondentStatus.IN_PROGRESS);
        context.setAnswertoCurrentQuestion("y,u", d);
//        context.setChapterLoopValue(nextQuestionNode.getChapterLoopValue());
        context.setCurrentQuestionId(d.getId());
        context.setVersion(1);
//        context.setCurrentChapterId(10);

//        nextNode = survey.getNext(context);
//        Assert.assertNotNull(nextNode);
        nextNode = chapter.getNext(context);

        Assert.assertNotNull(nextNode);
        if(nextNode instanceof QuestionNode){
            nextQuestionNode = (QuestionNode) nextNode;
        }
        else{
            Assert.fail("1. Question node expected");
        }
        System.out.println(nextNode);

        // case: 2 first question (e) in the chapter answered
//        context.setChapterLoopValue(nextQuestionNode.getChapterLoopValue());
        context.setAnswertoCurrentQuestion("x", ((QuestionNode) nextQuestionNode));
        context.setCurrentQuestionId(nextQuestionNode.getId());
        context.setCurrentChapterId(10);



        nextNode = chapter.getNext(context);
        Assert.assertNotNull(nextNode);
        if(nextNode instanceof QuestionNode){
            nextQuestionNode = (QuestionNode) nextNode;
        }
        else{
            Assert.fail("2. Question node expected");
        }
        System.out.println(nextNode);

        // case: 3 intermediate question(f) with link logic answered
//        context.setChapterLoopValue(nextQuestionNode.getChapterLoopValue());
        context.setAnswertoCurrentQuestion("x", ((QuestionNode) nextQuestionNode));
        context.setCurrentQuestionId(nextQuestionNode.getId());
        context.setCurrentChapterId(10);

        nextNode = chapter.getNext(context);
        Assert.assertNotNull(nextNode);
        if(nextNode instanceof QuestionNode){
            nextQuestionNode = (QuestionNode) nextNode;
        }
        else{
            Assert.fail("3. Question node expected");
        }
        System.out.println(nextNode);

        // case: 4 intermediate question (g) without link logic answered
//        context.setChapterLoopValue(nextQuestionNode.getChapterLoopValue());
        context.setAnswertoCurrentQuestion("x", ((QuestionNode) nextQuestionNode));
        context.setCurrentQuestionId(nextQuestionNode.getId());
        context.setCurrentChapterId(10);

        nextNode = chapter.getNext(context);
        Assert.assertNotNull(nextNode);
        if(nextNode instanceof QuestionNode){
            nextQuestionNode = (QuestionNode) nextNode;
        }
        else{
            Assert.fail("4. Question node expected");
        }
        System.out.println(nextNode);

        // case: 5 last question (h) with link logic answered
//        context.setChapterLoopValue(nextQuestionNode.getChapterLoopValue());
        context.setAnswertoCurrentQuestion("x", ((QuestionNode) nextQuestionNode));
        context.setCurrentQuestionId(nextQuestionNode.getId());
        context.setCurrentChapterId(10);

        nextNode = chapter.getNext(context);
        Assert.assertNotNull(nextNode);
        if(nextNode instanceof QuestionNode){
            nextQuestionNode = (QuestionNode) nextNode;
        }
        else{
            Assert.fail("3. Question node expected");
        }
        System.out.println(nextNode); // e

        // case: 5 last question (h) of last looped chapter answered
//        context.setChapterLoopValue("u");
        context.setAnswertoCurrentQuestion("x", ((QuestionNode) nextQuestionNode));
        context.setCurrentQuestionId(8);
        context.setCurrentChapterId(10);

        nextNode = chapter.getNext(context);
        Assert.assertNotNull(nextNode);
        if(!(nextNode instanceof SurveyNode)){
            Assert.fail("3. Survey end node expected");
        }
        System.out.println(nextNode);


    }

    /*

                -------------->           -------------->
           |               |         |               |
    a ----> b ----> c ----> distance -----> e ----> f ----> g ----> h ----> END
    |               |                        |               |
     -------------->                          -------------->


     */
    private void prepare() {
//        survey = new Survey();
//
//        a  = new QB().withIdText(1,"a.").wc("x").wc("y").build();
//        b = new QB().withIdText(2, "b.").wc("x").wc("y").build();
//        c  = new QB().withIdText(3,"c.").build();
//        d  = new QB().withIdText(4,"distance.").wc("x").wc("y").wc("z").wc("u").wc("v").withType(QuestionType.MULTIPLE_CHOICE).build();
//
//        survey.addFirstQuestionNode(a);
//        survey.addNextChoiceLinkedQuestionNode(a, b, "y");
//        survey.addNextChoiceLinkedQuestionNode(a, c, "x");
//        survey.addNextChoiceLinkedQuestionNode(b, c, "y");
//        survey.addNextChoiceLinkedQuestionNode(b, d, "x");
//        survey.addNextQuestionNode(c, d);
//
//        chapter = new Chapter(10, "ch1", survey);
//        chapter.setLoopQuestion(d);
//        survey.addChapter(d, chapter);
//
//
//        QuestionNode e  = new QB().withIdText(5,"e.").wc("x").wc("y").build();
//        QuestionNode f  = new QB().withIdText(6,"f.").wc("x").wc("y").build();
//        QuestionNode g  = new QB().withIdText(7,"g.").build();
//        QuestionNode h  = new QB().withIdText(8,"h.").build();
//
//
//        chapter.addFirstQuestionNode(e);
//        chapter.addNextChoiceLinkedQuestionNode(e, f, "y");
//        chapter.addNextChoiceLinkedQuestionNode(e, g, "x");
//        chapter.addNextChoiceLinkedQuestionNode(f, g, "y");
//        chapter.addNextChoiceLinkedQuestionNode(f, h, "x");
//        chapter.addNextQuestionNode(g, h);
//
//
//        chapter.setNextSurveyNode(survey.getEndNode());
    }


}
