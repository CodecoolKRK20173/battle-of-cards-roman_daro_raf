import java.util.ArrayList;

/**
 * StockPile
 */
public class StockPile extends Pile{

    ArrayList<Card> cards;
    
    public StockPile(){
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

    public  ArrayList<Card> getCards(){
        return cards;
    }

    public  int getSize(){
        return cards.size();
    }

    
}