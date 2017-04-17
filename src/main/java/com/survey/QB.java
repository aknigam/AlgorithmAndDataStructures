package com.survey;

import com.survey.node.QuestionNode;

/**
 * Created by a.nigam on 15/04/17.
 */
public class QB {

    QuestionNode q;

    public QB wc(String choice) {
        q.addChoice(choice);
        return this;
    }

    public QuestionNode build() {
        return q;
    }

    public QB withIdText(int i, String qu) {
        q = new QuestionNode(qu, i);
        return this;
    }

    public QB withType(QuestionType type) {
        q.setQuestionType(type);
        return this;
    }
}
