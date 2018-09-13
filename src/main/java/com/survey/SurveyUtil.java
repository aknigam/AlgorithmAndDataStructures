package com.survey;

import com.survey.node.PageNode;
import com.survey.node.QuestionNode;
import com.survey.node.SurveyNode;

import java.io.IOException;
import java.util.*;

/**
 * Created by a.nigam on 16/01/17.
 */
public class SurveyUtil {


    public static Chapter chapter;

    /*
            -------------->           -------------->
           |               |         |               |
    a ----> b ----> c ----> d -----> e ----> f ----> g ----> h ----> END
    |               |                        |               |
     -------------->                          -------------->


    refer following for visualisation:
    1. http://thinkingonthinking.com/Getting-Started-With-D3/
    2. https://jgraph.github.io/mxgraph/docs/manual.html
    3. http://www.puzzlr.org/basics-of-d3-force-directed-graphs/


     */

    public static void main(String[] args) throws IOException {

        /*

        Survey with 4 pages

        2 pages at the start followed by
        2 pages in a Chapter
        chapter get looped based on questions from page 2


         */


        Survey survey = new Survey();

        PageNode page1 = new PageNode(1, "page1");

        QuestionNode a1  = new QB().withIdText(11,"a1").wc("x").wc("y").build();
        QuestionNode a2  = new QB().withIdText(12,"a2").wc("x").wc("y").build();
        QuestionNode a3  = new QB().withIdText(13,"a3").wc("x").wc("y").build();

        page1.addQuestion(a1);
        page1.addQuestion(a2);
        page1.addQuestion(a3);
        survey.addPage(page1);

        PageNode page2 = new PageNode(2, "page2");

        QuestionNode a  = new QB().withIdText(1,"a").wc("x").wc("y").build();
        QuestionNode b  = new QB().withIdText(2,"b").wc("x").wc("y").build();
        QuestionNode c  = new QB().withIdText(3,"c").build();
        QuestionNode d  = new QB().withIdText(4,"distance").wc("x").wc("y").wc("z").withType(QuestionType.MULTIPLE_CHOICE).build();

        page2.addQuestion(a);
        page2.addQuestion(b);
        page2.addQuestion(c);
        page2.addQuestion(d);
        survey.addPage(page2);
        page1.setNext(page2);

        PageNode page3 = new PageNode(3, "page3");

        QuestionNode e  = new QB().withIdText(5,"e").wc("x").wc("y").build();
        QuestionNode f  = new QB().withIdText(6,"f").wc("x").wc("y").build();
        QuestionNode g  = new QB().withIdText(7,"g").build();
        QuestionNode h  = new QB().withIdText(8,"h").build();

        page3.addQuestion(e);
        page3.addQuestion(f);
        page3.addQuestion(g);
        page3.addQuestion(h);


//        survey.addFirstQuestionNode(a1);
//        survey.addNextQuestionNode(a1, a2);
//        survey.addNextQuestionNode(a2, a3);
//        survey.addNextQuestionNode(a3, a);
//        survey.addNextChoiceLinkedQuestionNode(a, b, "y");
//        survey.addNextChoiceLinkedQuestionNode(a, c, "x");
//        survey.addNextChoiceLinkedQuestionNode(b, c, "y");
//        survey.addNextChoiceLinkedQuestionNode(b, d, "x");
//        survey.addNextQuestionNode(c, d);

        chapter = new Chapter(10, "ch1", survey);
        chapter.addPage(page3);
        chapter.setLoopQuestion(d);
        survey.addChapter(page2, chapter);


//        chapter.addFirstQuestionNode(e);
//        chapter.addNextChoiceLinkedQuestionNode(e, f, "y");
//        chapter.addNextChoiceLinkedQuestionNode(e, g, "x");
//        chapter.addNextChoiceLinkedQuestionNode(f, g, "y");
//        chapter.addNextChoiceLinkedQuestionNode(f, h, "x");
//        chapter.addNextQuestionNode(g, h);

        chapter.setNextSurveyNode(survey.getEndNode());

        ReadConsole console = new ReadConsole();
        console.start();

        RespondentSurveyContext context = new RespondentSurveyContext(0);

        SurveyNode questionNode = null;
        SurveyUIBuilder uiBuilder = new SurveyUIBuilder();

        String nextChapterName = null;
        while(true) {

            questionNode = survey.getNext(context);

            if(questionNode ==  null || !(questionNode instanceof PageNode)){
                System.out.println("Survey finished.");
                break;
            }
            PageNode pageNode = (PageNode) questionNode;
            nextChapterName = context.getChapterLoopValue();
            if(nextChapterName != null ){

                System.out.println("-----------------------Chapter name : "+ pageNode.getChapter().getId()+"+["+ context
                        .getChapterLoopValue()
                        +"] started");
                uiBuilder.setChapter(""+pageNode.getChapter().getId()+"+["+ context.getChapterLoopValue()+"]");
            }
            if(pageNode == null)
                break;

            String page = pageNode.getName();
            System.out.println(page);
            context.setCurrentPage(pageNode.getId());

            List<QuestionNode> questions = pageNode.getQuestions();
            for(QuestionNode q : questions ) {
                uiBuilder.appendQuestion(q.getName());
                String answer = console.recordAnswerForQuestion(q);
                context.setCurrentQuestionId(q.getId());
                context.setAnswertoCurrentQuestion(answer, (q));
            }



        }
        System.out.println(uiBuilder.toString());




        /*
        printDfs(survey);

//        survey.deleteNode(3);
        printForwardEdges(survey);

        printBackEdges(survey);

        printDfs(chapter);
        printForwardEdges(chapter);

        printBackEdges(chapter);
        */



    }



}
