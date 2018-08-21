import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Game{
    private int currentPlayer;
    private ArrayList<Player> players;
    private Pile deck;

    public Game(int numberOfPlayers, String... names){
        
        setPlayers(numberOfPlayers, names);
        this.deck = new Pile();
        loadDeck();
        this.currentPlayer = 1;
        dealCards();
    }

    private void setPlayers(int numberOfPlayers, String[] names){
        for(int i = 1; i <= numberOfPlayers; i++ ){
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
        }

        br.close();
    }

    private void dealCards(){

    }

    public void runGame(){

    }

    
}