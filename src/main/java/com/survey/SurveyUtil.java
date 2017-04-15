package com.survey;

import com.survey.node.QuestionNode;
import com.survey.node.SurveyNode;
import com.survey.node.SurveyStartNode;

import java.util.*;

/**
 * Created by a.nigam on 16/01/17.
 */
public class SurveyUtil {


    public static Chapter chapter;

    public static void main(String[] args) {

        Survey survey = new Survey();

        QuestionNode a= new QuestionNode("a", 1);

        survey.addQuestionNode(a);
        survey.addQuestionNode(1, new QuestionNode( "b", 2));
        survey.addQuestionNode(2, new QuestionNode( "c", 3));
        survey.addQuestionNode(3, new QuestionNode("d", 4));
        survey.addQuestionNode(1, new QuestionNode("c", 3));
        QuestionNode d = new QuestionNode("d", 4);
        d.addChoice("x");
        d.addChoice("y");
        d.addChoice("z");
        survey.addQuestionNode(2, d);

        chapter = new Chapter(10, "ch1");
        chapter.setLoopQuestion(d);
        survey.addChapter(4, chapter);

        chapter.addQuestionNode(new QuestionNode( "e", 5));
        chapter.addQuestionNode(5, new QuestionNode( "f", 6));
        chapter.addQuestionNode(5, new QuestionNode( "g", 7));
        chapter.addQuestionNode(6, new QuestionNode("g", 7));
        chapter.addQuestionNode(7, new QuestionNode("h", 8));
        chapter.addQuestionNode(6, new QuestionNode("h", 8));

        chapter.setNextSurveyNode(survey.getEndNode());



        SurveyNode node = survey.getStartNode();
        StringBuilder srvy = new StringBuilder();
        srvy.append(node.toString()).append("---");

        int questionId = 0;
        int chapterId = 10;

        try {
            srvy.append(survey.getNext(new RespondentSurveyContext(0)).toString()).append("---"); // a
            srvy.append(survey.getNext(new RespondentSurveyContext(1)).toString()).append("---"); // b
            srvy.append(survey.getNext(new RespondentSurveyContext(2)).toString()).append("---"); // c
            srvy.append(survey.getNext(new RespondentSurveyContext(3)).toString()).append("---"); // d

            srvy.append(survey.getNext(new RespondentSurveyContext(4)).toString()).append("---"); // e


            srvy.append(survey.getNext(new RespondentSurveyContext(5, chapterId, String.valueOf('x'))).toString()).append("---"); // f
            srvy.append(survey.getNext(new RespondentSurveyContext(6, chapterId, String.valueOf('x'))).toString()).append("---"); // g
            srvy.append(survey.getNext(new RespondentSurveyContext(7, chapterId, String.valueOf('x'))).toString()).append("---"); // h
            srvy.append(survey.getNext(new RespondentSurveyContext(8, chapterId, String.valueOf('x'))).toString()).append("---"); // f

            srvy.append(survey.getNext(new RespondentSurveyContext(5, chapterId, String.valueOf('y'))).toString()).append("---"); // f
            srvy.append(survey.getNext(new RespondentSurveyContext(6, chapterId, String.valueOf('y'))).toString()).append("---"); // g
            srvy.append(survey.getNext(new RespondentSurveyContext(7, chapterId, String.valueOf('y'))).toString()).append("---"); // h
            srvy.append(survey.getNext(new RespondentSurveyContext(8, chapterId, String.valueOf('y'))).toString()).append("---"); // f

            srvy.append(survey.getNext(new RespondentSurveyContext(5, chapterId, String.valueOf('z'))).toString()).append("---"); // f
            srvy.append(survey.getNext(new RespondentSurveyContext(6, chapterId, String.valueOf('z'))).toString()).append("---"); // g
            srvy.append(survey.getNext(new RespondentSurveyContext(7, chapterId, String.valueOf('z'))).toString()).append("---"); // h
            srvy.append(survey.getNext(new RespondentSurveyContext(8, chapterId, String.valueOf('z'))).toString()).append("---"); // f

            System.out.println(srvy.toString());
            /*

            while (questionId <=3) {

                RespondentSurveyContext context = new RespondentSurveyContext(questionId++);
                node = survey.getNext(context);
                srvy.append(node.toString()).append("---");
                if (node == null) {
                    break;
                }
            }
            System.out.println("Question: "+ questionId);
            char loop = 'x';
            while (loop <= 'z') {
                while (questionId <=7) {
                    RespondentSurveyContext context = new RespondentSurveyContext(questionId, chapterId, String.valueOf(loop++));
                    questionId++;
                    node = survey.getNext(context);
                    srvy.append(node.toString()).append("---");
                    if (node == null) {
                        break;
                    }
                }
                questionId = 5;

            }
            */

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println(srvy.toString());
        }


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
