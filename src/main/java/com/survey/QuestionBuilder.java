package com.survey;

import com.survey.node.QuestionNode;

/**
 * Created by a.nigam on 15/04/17.
 */
public class QuestionBuilder {

    QuestionNode q;

    public QuestionBuilder wc(String choice) {
        q.addChoice(choice);
        return this;
    }

    public QuestionNode build() {
        return q;
    }

    public QuestionBuilder withIdText(int i, String qu) {
        q = new QuestionNode(qu, i);
        return this;
    }

    public QuestionBuilder withType(QuestionType type) {
        q.setQuestionType(type);
        return this;
    }
}
