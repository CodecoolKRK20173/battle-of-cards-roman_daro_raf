import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Game{
    private int currentPlayer;
    private int numberOfPlayers;
    private ArrayList<Player> players;
    private Pile deck;
    private boolean isWon;
    private int category;
    private ArrayList<Card> handCards;
    private boolean isDraw;
    

    public Game(int numberOfPlayers, String... names){
        this.isWon = false;
        this.isDraw = true;
        this.numberOfPlayers = numberOfPlayers;
        this.handCards = new ArrayList<>();
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
            Card newCard = new Card();
            newCard.setCountryName(countryData[COUNTRY_NAME_COLLUMN]);
            newCard.setPopulation(Float.valueOf(countryData[POPULATION_COLLUMN]));
            newCard.setDensity(Float.valueOf(countryData[DENSITY_COLLUMN]));
            newCard.setArea(Float.valueOf(countryData[AREA_COLLUMN]));
            newCard.setArea(Float.valueOf(MEDIAN_AGE_COLLUMN));
            deck.addCard(newCard);
        }

        br.close();
    }

    private void dealCards(){
        int playerNumber = 0;
        for(Card nextCard: deck.getCards()){
            plyers.get(playerNumber % this.numberOfPlayers).addCardsToStock(nextCard);
            playerNumber++;
        }
    }

    public void runGame(){
        while(!this.isWon){
            this.isDraw = true;
            while (isDraw) {    
                moveCardsToHand();
                revealActiveHand();
                chooseCategory();
                revealAllCards();
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
        this.handCards.clear();
        getHandCards();
        sortByCategory();
        markIfDraw();
    }

    private void getHandCards() {
        for(Player player: this.players)
            this.handCards.add(player.getHandPile().getTop());
    }

    private void sortByCategory(){
        if(this.category == "1")
            Collections.sort(this.handCards, Comparator.comparing((Card card) -> card.getArea()));
        else if (this.category == "2")
            Collections.sort(this.handCards, Comparator.comparing((Card card) -> card.getDensity()));
        else if (this.category == "3")
            Collections.sort(this.handCards, Comparator.comparing((Card card) -> card.getPopulation()));
        else
            Collections.sort(this.handCards, Comparator.comparing((Card card) -> card.getMedianAge()));

    }

    private void markIfDraw(){
        // should save draw in separate class that remembers isDraw and player indexes

    }

    private void moveCardsToWinningPlayer(){

    }
}