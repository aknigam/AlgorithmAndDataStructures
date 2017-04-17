package com.survey;

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


     */
    public static void main(String[] args) throws IOException {

        Survey survey = new Survey();

        QuestionNode a  = new QB().withIdText(1,"a").wc("x").wc("y").build();
        QuestionNode b  = new QB().withIdText(2,"b").wc("x").wc("y").build();
        QuestionNode c  = new QB().withIdText(3,"c").build();
        QuestionNode d  = new QB().withIdText(4,"d").wc("x").wc("y").wc("z").withType(QuestionType.MULTIPLE_CHOICE).build();

        survey.addQuestionNode(a);
        survey.addQuestionNode(a, b, "y");
        survey.addQuestionNode(a, c, "x");
        survey.addQuestionNode(b, c, "y");
        survey.addQuestionNode(b, d, "x");
        survey.addQuestionNode(c, d);

        chapter = new Chapter(10, "ch1");
        chapter.setLoopQuestion(d);
        survey.addChapter(d, chapter);


        QuestionNode e  = new QB().withIdText(5,"e").wc("x").wc("y").build();
        QuestionNode f  = new QB().withIdText(6,"f").wc("x").wc("y").build();
        QuestionNode g  = new QB().withIdText(7,"g").build();
        QuestionNode h  = new QB().withIdText(8,"h").build();


        chapter.addQuestionNode(e);
        chapter.addQuestionNode(e, f, "y");
        chapter.addQuestionNode(e, g, "x");
        chapter.addQuestionNode(f, g, "y");
        chapter.addQuestionNode(f, h, "x");
        chapter.addQuestionNode(g, h);


        chapter.setNextSurveyNode(survey.getEndNode());



        SurveyNode node = survey.getStartNode();

        ReadConsole console = new ReadConsole();
        console.start();
        Respondent respondent = new Respondent();
        RespondentSurveyContext context = new RespondentSurveyContext(1);

        SurveyNode questionNode = survey.getStartNode().getNext(context);
        StringBuilder srvy = new StringBuilder();
        srvy.append(node.toString()).append("---");
        String chapterName = null;
        String nextChapterName = null;
        while(questionNode != null) {


            String question = questionNode.getName();
            if(question.equalsIgnoreCase("a")){
//                survey.deleteNode(2);
            }
            srvy.append(question).append("-");
            int id  = questionNode.getId();


            String answer = console.recordAnswerForQuestion((QuestionNode)questionNode);

            context.setCurrentQuestionId(questionNode.getId());
            context.setAnswertoCurrentQuestion(answer, ((QuestionNode)questionNode));
            context.setCurrentChapterId(((QuestionNode) questionNode).getChapterId());
            context.setChapterLoopValue(((QuestionNode) questionNode).getChapterLoopValue());
            questionNode = survey.getNext(context);

            if(questionNode ==  null || !(questionNode instanceof QuestionNode)){
                System.out.println("Survey finished.");
                break;
            }
            nextChapterName =((QuestionNode) questionNode).getChapterLoopValue();
            if(nextChapterName != null ){

                System.out.println("-----------------------Chapter name : "+ ((QuestionNode) questionNode).getChapterId()+"+["+ ((QuestionNode) questionNode).getChapterLoopValue()+"] started");
            }


        }
        System.out.println(srvy.toString());




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

    private static void printBackEdges(QuestionContainer survey) {
        System.out.println("Back edges >>>");
        SurveyNode node ;
        for( Map.Entry<Integer, QuestionNode> nodeEntry : survey.getSurveyNodes().entrySet()){
            node= nodeEntry.getValue();
            System.out.println(node+" ["+node.getAllPossibleBackNodes()+"]");
        }
    }

    private static void printForwardEdges(QuestionContainer survey) {
        System.out.println("Forward edges >>>");
        SurveyNode node ;
        for( Map.Entry<Integer, QuestionNode> nodeEntry : survey.getSurveyNodes().entrySet()){
            node = nodeEntry.getValue();
            System.out.println(node+" ["+node.getAllPossibleNextNodes()+"]");
        }

    }

    private static void printDfs(QuestionContainer survey) {

        SurveyNode node = survey.getStartNode();

        List<LinkEdge> edges = node.getAllPossibleNextNodes();

        Deque<SurveyNode> stack = new LinkedList<>();
        LinkEdge root = node.getAllPossibleNextNodes().get(0);
        stack.push(node);
        node.setVisited(true);

        StringBuffer dfs = new StringBuffer();
        while (!stack.isEmpty()){
            System.out.println("Stack: "+ stack);
            node = stack.pop();
            dfs.append(node.toString());
            List<LinkEdge> nodes = node.getAllPossibleNextNodes();
            for (LinkEdge e:nodes) {
                SurveyNode target = e.getTarget();
                if(!target.isVisited()) {
                    target.setPredecessorTreeNode(node);
                    target.setVisited(true);
                    stack.push(target);
                }
            }
        }
        System.out.println(dfs);
    }

    private static void printAllPossiblePaths(SurveyUtil survey){

    }

    public void transform(Survey survey){

        SurveyNode node = survey.getStartNode();
        List<LinkEdge> edges = node.getAllPossibleNextNodes();
        Deque<LinkEdge> stack = new LinkedList<>();
        LinkEdge edge =  null;
        StringBuffer dfs = new StringBuffer();
        while (!stack.isEmpty()){

            edge = stack.pop();
            SurveyNode target = edge.getTarget();
            target.setVisited(true);

            dfs.append(edge.getTarget().toString());
            List<LinkEdge> nodes = target.getAllPossibleNextNodes();
            for (LinkEdge e:nodes) {
                target = e.getTarget();
                if(!target.isVisited())
                    stack.push(e);
            }

        }

    }


}
