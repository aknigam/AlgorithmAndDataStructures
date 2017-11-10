package com.survey;

import com.survey.node.QuestionNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by a.nigam on 15/04/17.
 */
public class ReadConsole {

    private BufferedReader br = null;

    public void ReadConsole() {
        try {
            while (true) {
                readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }

    }

    public void start(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public void stop(){
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readLine() throws IOException {
        System.out.print("Enter : ");
        String input = br.readLine();

        if ("q".equals(input)) {
            System.out.println("Exit!");
            System.exit(0);
        }

//        System.out.println("input : " + input);
//        System.out.println("-----------\n");
        return input;
    }

    public static void main(String[] args) throws IOException {
        ReadConsole readConsole = new ReadConsole();
        readConsole.start();
        readConsole.readLine();
        readConsole.stop();
    }

    public String recordAnswerForQuestion(QuestionNode question) throws IOException {
        String qstnText = question.getName();
        String type = question.getQuestionType().getType();
        System.out.print("Enter answer for question of type ("+type+")-----> "+ question+" ("+ question.getChoices()+")\n");
        String input = br.readLine();

        if ("q".equals(input)) {
            System.out.println("Exit!");
            System.exit(0);
        }

//        System.out.println("input : " + input);
//        System.out.println("-----------\n");
        return input;
    }
}
