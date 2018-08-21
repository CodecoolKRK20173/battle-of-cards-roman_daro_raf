import java.util.ArrayList;
import java.util.Collections;


/**
 * Deck
 */
public class Deck extends Pile{

    ArrayList<Card> cards;

    
    public Deck(){
        cards = new ArrayList<>();
    }


    public  void addCard(Card card){
        cards.add(card);
    }
    
    public  Card getTop(){
        return cards.get(0);
    }

    public  void removeTop(){
        cards.remove(0);
    }

    public  void shuffleListOfCards(){
        Collections.shuffle(cards);
    }

    public  ArrayList<Card> getCards(){
        return cards;
    }

    public  int getSize(){
        return cards.size();
    }

}