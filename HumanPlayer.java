import java.util.ArrayList;


public class HumanPlayer extends Player{
    
    private HandPile handPile;
    private StockPile stockPile;
    private String name;
    private int playerIndex;
    private boolean isActive;


    public HumanPlayer( int playerIndex){
        this.name = "Player";
        this.playerIndex = playerIndex;
        this.handPile = new HandPile();
        this.stockPile = new StockPile();
    }

    public HumanPlayer(String name, int playerIndex){
        this.name = name;
        this.playerIndex = playerIndex;
        this.handPile = new HandPile();
        this.stockPile = new StockPile();
    }

    public HandPile getHandPile(){
        return this.handPile;
    }

    public StockPile getStockPile(){
        return this.stockPile;
    }

    public String getName(){
        return this.name;
    }

    public int getNumber(){
        return playerIndex;
    }

    public boolean getIsActive(){
        return this.isActive;
    }

    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

    public void addCardToStock(Card card){
        card.setOwnerIndex(playerIndex);
        stockPile.addCard(card);
    }

    public void addCardToStock(Pile pileOfCards ){
        for (Card  card : pileOfCards.getCards()){
            card.setOwnerIndex(playerIndex);
            stockPile.addCard(card);
        }
    }

    public void fromStockToHand(){
        Card topCard = stockPile.getTop();
        stockPile.removeTop();
        handPile.addCard(topCard);
    }

    public void revealHand(){
        Card card = handPile.getTop();
        card.setFaceUp();
    }
}