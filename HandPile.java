import java.util.ArrayList;

/**
 * HandPile
 */
public class HandPile extends Pile{

    ArrayList<Card> cards;
    
    public HandPile(){
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




    
}