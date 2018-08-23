import java.util.ArrayList;

public abstract class Player{

    public abstract HandPile getHandPile();
    public abstract StockPile getStockPile();
    public abstract String getName();
    public abstract int getNumber();
    public abstract boolean getIsActive();
    public abstract void addCardToStock(Card card);
    public abstract void addCardToStock(Pile listOfCards);
    public abstract void fromStockToHand();
    public abstract void revealHand();
    public abstract void setIsActive(boolean isActive);

}