import java.util.ArrayList;

public abstract class Player{

    public abstract Pile getHandPile();
    public abstract Pile getStockPile();
    public abstract String getName();
    public abstract int getNumber();
    public abstract void addCardToStock(Card card);
    public abstract void addCardToStock(ArrayList<Card> listOfCards);
    public abstract void fromStockToHand();

}