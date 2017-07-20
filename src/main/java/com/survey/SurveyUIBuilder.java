package com.survey;

/**
 * Created by a.nigam on 20/07/17.
 */
public class SurveyUIBuilder {


    private StringBuilder srvy = new StringBuilder();
    private String chapter;

    SurveyUIBuilder(){

    }
    public void appendQuestion(String question) {
        srvy.append(question).append("-");
    }

    @Override
    public String toString() {
        if(this.chapter != null){
            srvy.append(" ) ]");
        }
        return srvy.toString();
    }

    public void setChapter(String chpt) {
        if(this.chapter == null) {
            this.chapter = chpt;
            srvy.append("[ (").append(this.chapter).append(": ");

        }
        if(!this.chapter.equalsIgnoreCase(chpt)){
            srvy.append(" ) ]");
            // finish old and start new
            this.chapter = chpt;
            srvy.append("[ (").append(this.chapter).append(": ");

        }

    }
}
