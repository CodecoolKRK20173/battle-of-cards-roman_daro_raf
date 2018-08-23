import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private int winningPlayerIndex;
    private int numberOfCards;
    

    public Game(int numberOfPlayers, int numberOfCards, String... names){
        // for(String name: names) System.out.println(name);
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfCards = numberOfCards;
        this.winningPlayerIndex = -1;
        this.isWon = false;
        this.isDraw = true;
        this.handCards = new ArrayList<>();
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.currentPlayer = 0;
        loadDeck();
        setPlayers(names);
        // setPlayers();
        dealCards();
    }

    private void loadDeck() {
        final int COUNTRY_NAME_COLLUMN = 0;
        final int POPULATION_COLLUMN = 1;
        final int DENSITY_COLLUMN = 2;
        final int AREA_COLLUMN = 3;
        final int MEDIAN_AGE_COLLUMN = 4; 

        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("countries.txt")));
            String line = br.readLine();
            while (line != null) {
                String[] countryData = line.split("\t");
                // for(String data: countryData) System.out.printf("%s ", data);
                // System.out.println();
                Card newCard = new Card();
                newCard.setCountryName(countryData[COUNTRY_NAME_COLLUMN]);
                newCard.setPopulation(Integer.valueOf(countryData[POPULATION_COLLUMN]));
                newCard.setDensity(Integer.valueOf(countryData[DENSITY_COLLUMN]));
                newCard.setArea(Integer.valueOf(countryData[AREA_COLLUMN]));
                newCard.setArea(Integer.valueOf(MEDIAN_AGE_COLLUMN));
                deck.addCard(newCard);
                line = br.readLine();
            }
            br.close();
        } catch(IOException e){
            System.out.println("File not found");
        }
        
    }

    private void setPlayers( String[] names){
        for(int i = 0; i < this.numberOfPlayers; i++ ){
            this.players.add(new HumanPlayer(names[i], i));
        }
    }

    private void setPlayers(){
        for(int i = 0; i < this.numberOfPlayers; i++ ){
            this.players.add(new HumanPlayer(i));
        }
    }

    private void dealCards(){
        int playerNumber = 0;
        for(Card nextCard: deck.getCards()){
            players.get(playerNumber % this.numberOfPlayers).addCardToStock(nextCard);
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
        System.out.printf("%s choose category: ", this.players.get(this.currentPlayer).getName());
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            this.category = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.printf("Please enter number from 1 - %d", this.numberOfPlayers);     // MOVE THIS TO VIEW
        }
    }

    private void revealAllCards(){
        // TO DO
    }

    private void compareCards(){
        this.handCards.clear();
        getHandCards();
        sortByCategory();
        markIfDraw();
        setWinningPlayer();
    }

    private void getHandCards() {
        for(Player player: this.players)
            this.handCards.add(player.getHandPile().getTop());
    }

    private void sortByCategory(){
        if(this.category == 1)
            this.handCards.sort(Comparator.comparing(Card::getArea));
        else if (this.category == 2)
            this.handCards.sort(Comparator.comparing(Card::getDensity));
        else if (this.category == 3)
            this.handCards.sort(Comparator.comparing(Card::getPopulation));
        else
            this.handCards.sort(Comparator.comparing(Card::getMedianAge));

    }

    private void markIfDraw(){
        // should save draw in separate class that remembers isDraw and player indexes
        this.isDraw = false;
    }

    private void setWinningPlayer(){
        this.winningPlayerIndex = this.handCards.get(0).getOwnerIndex();
    }

    private void moveCardsToWinningPlayer(){
        for(Player player: this.players){
            this.players.get(this.winningPlayerIndex).addCardToStock(player.getHandPile());
        }
    }

    private void setActiveAsWinning(){
        this.currentPlayer = this.winningPlayerIndex;
    }

    private void checkIfWon(){
        if(this.players.get(this.winningPlayerIndex).getStockPile().getSize() >= this.numberOfCards){
            this.isWon = true;
            // VIEW WILL PRINT WINNING MESSAGE WITH WINNING PLAYER NAME
        }
    }
}