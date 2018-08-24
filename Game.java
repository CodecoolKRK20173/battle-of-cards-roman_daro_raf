import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Game{
    final int POPULATION_COLLUMN = 1;
    final int DENSITY_COLLUMN = 2;
    final int AREA_COLLUMN = 3;
    final int MEDIAN_AGE_COLLUMN = 4; 
    private int currentPlayer;
    private int numberOfPlayers;
    private ArrayList<Player> players;
    private Deck deck;
    private boolean isWon;
    private int category;
    private ArrayList<Card> handCards;
    private boolean isDraw;
    private int winningPlayerIndex;
    private int numberOfCards;
    private View view;


    public Game(){
        this.winningPlayerIndex = -1;
        this.isWon = false;
        this.isDraw = true;
        this.handCards = new ArrayList<>();
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.currentPlayer = 0;
        this.view = new View();
        loadDeck();
    }
    

    public Game(int numberOfPlayers, int numberOfCards, String... names){
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfCards = numberOfCards;
        this.winningPlayerIndex = -1;
        this.isWon = false;
        this.isDraw = true;
        this.handCards = new ArrayList<>();
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.currentPlayer = 0;
        this.view = new View();
        loadDeck();
        setPlayers(names);
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
                Card newCard = new Card();
                newCard.setCountryName(countryData[COUNTRY_NAME_COLLUMN]);
                newCard.setPopulation(Integer.valueOf(countryData[POPULATION_COLLUMN]));
                newCard.setDensity(Integer.valueOf(countryData[DENSITY_COLLUMN]));
                newCard.setArea(Integer.valueOf(countryData[AREA_COLLUMN]));
                newCard.setMedianAge(Integer.valueOf(countryData[MEDIAN_AGE_COLLUMN]));
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

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    private void dealCards(){
        this.deck.shuffleListOfCards();
        int playerNumber = 0;
        for(Card nextCard: deck.getCards()){
            players.get(playerNumber % this.numberOfPlayers).addCardToStock(nextCard);
            playerNumber++;
        }
    }

    public void runGame(){
        view.menu();
        if (view.getAmountOfPlayers() > 1 && view.getAmountOfPlayers() < 5) {
            setNumberOfPlayers(view.getAmountOfPlayers());
            setNumberOfCards(36);
            setPlayers(view.getPlayersNames());
            dealCards();
            while(!this.isWon){
                this.isDraw = true;
                while (isDraw) {    
                    this.handCards.clear();
                    moveCardsToHand();
                    revealActiveHand();                    
                    getHandCards();
                    this.view.printTableView(handCards, currentPlayer, players);
                    chooseCategory();
                    revealAllCards();
                    this.view.printTableView(handCards, currentPlayer, players);
                    compareCards();
                    view.printWinningPlayer(winningPlayerIndex, players);
                    
                }
                moveCardsToWinningPlayer();
                deactivateLoosers();
                setActiveAsWinning();
                checkIfWon();
            }
        }
    }

    private void deactivateLoosers(){
        for(Player player: this.players) {
            if(player.getStockPile().getSize() <= 0)
                player.setIsActive(false);
        }
    }

    private void moveCardsToHand(){
        for (Player player : this.players) {
            if(player.getStockPile().getSize() > 0)
                player.fromStockToHand();
        }
    }

    private void revealActiveHand(){
        this.players.get(this.currentPlayer).revealHand();
    }



    private void chooseCategory() {
        System.out.println(String.format("%s choose category: ", this.players.get(this.currentPlayer).getName()));
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            this.category = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            System.out.printf("Please enter number from 1 - %d", this.numberOfPlayers);
        }
    }

    private void revealAllCards(){
        for (Player p: players) {
            if(p.getIsActive()) p.revealHand();
        }
    }

    private void getHandCards() {
        for(Player player: this.players)
            if (player.getIsActive()) this.handCards.add(player.getHandPile().getTop());
    }

    private void compareCards(){
        sortByCategory();
        markIfDraw();
        setWinningPlayer();
    }


    private void sortByCategory(){
        if(this.category == this.POPULATION_COLLUMN)
            this.handCards.sort(Comparator.comparing(Card::getPopulation));
        else if (this.category == this.DENSITY_COLLUMN)
            this.handCards.sort(Comparator.comparing(Card::getDensity));
        else if (this.category == this.AREA_COLLUMN)
            this.handCards.sort(Comparator.comparing(Card::getArea));
        else if (this.category == this.MEDIAN_AGE_COLLUMN)
            this.handCards.sort(Comparator.comparing(Card::getMedianAge));

    }

    private void markIfDraw() {
        this.isDraw = false;
    }

    private void setWinningPlayer(){
        this.winningPlayerIndex = this.handCards.get(this.handCards.size()-1).getOwnerIndex();
    }

    private void moveCardsToWinningPlayer(){
        for(Player player: this.players){
            if(player.getIsActive()){
                player.getHandPile().cover();
                this.players.get(this.winningPlayerIndex).addCardToStock(player.getHandPile());
                player.getHandPile().clear();
            }
        }
    }

    private void setActiveAsWinning(){
        this.currentPlayer = this.winningPlayerIndex;
    }

    private void checkIfWon(){
        if(this.players.get(this.winningPlayerIndex).getStockPile().getSize() >= this.numberOfCards){
            this.isWon = true;
            System.out.printf("%s WON\n", this.players.get(this.winningPlayerIndex).getName());
        }
    }
}