import java.io.BufferedReader;
import java.util.ArrayList;

public class Game{
    private int currentPlayer;
    private int numberOfPlayers;
    private ArrayList<Player> players;
    private Pile deck;
    private boolean isWon;
    private int category;
    

    public Game(int numberOfPlayers, String... names){
        this.isWon = false;
        this.numberOfPlayers = numberOfPlayers;
        setPlayers(names);
        this.deck = loadDeck();
        this.currentPlayer = 1;
        dealCards();
    }

    private void setPlayers( String[] names){
        for(int i = 1; i <= this.numberOfPlayers; i++ ){
            players.add(new Player("Player", i));
        }
    }

    private Pile loadDeck(){
        // TO DO
        return null;
    }

    private void dealCards(){
        int playerNumber = 0;
        for(Card nextCard: deck.getCards()){
            plyers.get(playerNumber % this.numberOfPlayers).addCardsToStock(nextCard);
            playerNumber++;
        }
    }

    public void runGame(){
        boolean isDraw = true;
        while(!this.isWon){
            while (isDraw) {    
                moveCardsToHand();
                revealActiveHand();
                chooseCategory();
                compareCards();
                
            }
            moveCardsToWinningPlayer();
            setActiveAsWinning();
            checkIfWon();
        }
    }

    private void moveCardsToHand(){
        for (Player player : this.players) {
            player.fromStockToHand();
        }
    }

    private void revealActiveHand(){
        this.players.get(this.currentPlayer).revealHand();
    }

    private void chooseCategory(){
        //REPLACE WITH VIEW.costam
        System.out.println("Choose category");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.category = Integer.parseInt(reader.readLine());
    }

    private void compareCards(){

    }

    private void moveCardsToWinningPlayer(){

    }
}