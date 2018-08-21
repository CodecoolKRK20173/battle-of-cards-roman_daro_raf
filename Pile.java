import java.util.ArrayList;

abstract class Pile{
    ArrayList<Card> cards;
    
    public Pile(){
        cards = new ArrayList<>();
    }

    public abstract Card getTop();
    public abstract void addCard(Card card);
    public abstract void removeTop();



}