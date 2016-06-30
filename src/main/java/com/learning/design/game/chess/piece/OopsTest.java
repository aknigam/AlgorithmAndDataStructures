package com.learning.design.game.chess.piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.nigam on 18/12/15.
 */
public class OopsTest {


    List<String> list = new ArrayList<String>();

    public void changeValue(String s){
        s = "anc";
    }

    public void changeBoard(Camel camel){
        camel = new Camel(PieceType.BLACK, 1);
    }

    public static void main(String[] args) {
        String s = "anand";
        OopsTest oopsTest = new OopsTest();

        System.out.println(s.hashCode());
        oopsTest.changeValue(s);
        System.out.println(s.hashCode());
    }


}
