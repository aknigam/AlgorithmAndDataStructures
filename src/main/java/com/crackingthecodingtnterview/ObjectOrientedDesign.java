package com.crackingthecodingtnterview;

/**
 * Created by a.nigam on 25/11/16.
 */
public class ObjectOrientedDesign {

    enum Suit{
        HEART, SPADE, CLUB , DIAMOND
    }
    enum CardDenomination{
        ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JOKER,QUEEN,KING;
    }
    static class Card{
        CardDenomination denomination;
        Suit suit;
    }
    static class DeckOfCards{

        Card[] cards;

        public DeckOfCards(int size){
            cards = new Card[size];
        }
        public void shuffle(){}

        public void cardAt(int index){

        }

        public void insertCardAt(int index){

        }


    }
}
