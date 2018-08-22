import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        this.deck = new Pile();
        this.currentPlayer = 1;
        loadDeck();
        setPlayers(names);
        dealCards();
    }

    private void setPlayers( String[] names){
        for(int i = 1; i <= this.numberOfPlayers; i++ ){
            players.add(new Player("Player", i));
        }
    }

    private void loadDeck() {
        final int COUNTRY_NAME_COLLUMN = 0;
        final int POPULATION_COLLUMN = 1;
        final int DENSITY_COLLUMN = 2;
        final int AREA_COLLUMN = 3;
        final int MEDIAN_AGE_COLLUMN = 4; 

        BufferedReader br = new BufferedReader(new FileReader(new File("countries.txt")));

        String line;
        while ((line = br.readLine()) != null) {
            String[] countryData = line.split("/t");
            deck.addCard(new Card(countryData[COUNTRY_NAME_COLLUMN], 
                              Float.valueOf(countryData[POPULATION_COLLUMN]), 
                              Float.valueOf(countryData[DENSITY_COLLUMN]), 
                              Float.valueOf(countryData[AREA_COLLUMN]), 
                              Float.valueOf(countryData[MEDIAN_AGE_COLLUMN])));   
        }
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