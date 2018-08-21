import java.util.ArrayList;

public class Game{
    private int currentPlayer;
    private ArrayList<Player> players;
    private Pile deck;

    public Game(int numberOfPlayers, String... names){
        
        setPlayers(numberOfPlayers, names);
        this.deck = loadDeck();
        this.currentPlayer = 1;
        dealCards();
    }

    private void setPlayers(int numberOfPlayers, String[] names){
        for(int i = 1; i <= numberOfPlayers; i++ ){
            players.add(new Player("Player", i));
        }
    }

    private Pile loadDeck(){
        // TO DO
        return null;
    }

    private void dealCards(){

    }

    public void runGame(){

    }

    
}