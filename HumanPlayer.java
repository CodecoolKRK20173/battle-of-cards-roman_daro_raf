import java.util.ArrayList;


public class HumanPlayer extends Player{
    
    private Pile handPile;
    private Pile stockPile;
    private String name;
    private int number;


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
        stockPile.addCard(card);
        card.setOwnerIndex(..........);
    }

    public void addCardToStock(ArrayList<Card> listOfCards ){
        for (Card  card : listOfCards){
            stockPile.addCard(card);
            card.setOwnerIndex(...........);
        }
    }

    public void fromStockToHand(){
        Card topCard = stockPile.getTop();
        stockPile.removeTop();
        handPile.addCard(topCard);
    }

}