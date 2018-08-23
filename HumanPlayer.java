import java.util.ArrayList;


public class HumanPlayer extends Player{
    
    private Pile handPile;
    private Pile stockPile;
    private String name;
    private int number;


    public HumanPlayer( int number){
        this.name = "Player";
        this.number = number;
        this.handPile = new HandPile();
        this.stockPile = new StockPile();
    }

    public HumanPlayer(String name, int number){
        this.name = name;
        this.number = number;
        this.handPile = new HandPile();
        this.stockPile = new StockPile();
    }

    public Pile getHandPile(){
        return handPile;
    }

    public Pile getStockPile(){
        return stockPile;
    }

    public String getName(){
        return this.name;
    }

    public int getNumber(){
        return number;
    }

    public void addCardToStock(Card card){
        card.setOwnerIndex(number);
        stockPile.addCard(card);
    }

    public void addCardToStock(Pile pileOfCards ){
        for (Card  card : pileOfCards.getCards()){
            card.setOwnerIndex(number);
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