import java.util.ArrayList;

abstract class Pile{
   
    public abstract Card getTop();
    public abstract void removeTop();
    public abstract void addCard(Card card);
    public abstract ArrayList<Card> getCards();
    public abstract int getSize();

}