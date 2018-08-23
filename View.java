import java.util.ArrayList;
import java.util.Scanner;

public class View {
    private final int CARD_WIDTH = 20;
    private String topAndDownRow = repeat("#", CARD_WIDTH + 2) + "\n";
    private int amountOfPlayers = 0;
    private String[] playersNames;

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public String[] getPlayersNames() {
        return playersNames;
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        printMainMenu();
        boolean isRunning = true;
        
        while (isRunning) {
            isRunning = false;
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                howManyPlayers();
                createPlayersNames(this.amountOfPlayers);
                break;
                case "0":
                break;
                default: 
                System.out.println("Unknown choice! Try again...");
                isRunning = true;
                
            }
        }

        sc.close();

    }

    private void printMainMenu() {
        String[] menuOptions = {"1. New game", "0. Exit"};

        for (String s: menuOptions) System.out.println(s);
    }

    private void howManyPlayers() {
        System.out.println("How many players will play? (2 - 4)");
        this.amountOfPlayers = getIntInput();

        if (!(this.amountOfPlayers >= 2) && !(this.amountOfPlayers <= 4)) {
            System.out.println("Wrong choice! Choose number of players from 2 to 4.");
            howManyPlayers();
        }
    }

    private void createPlayersNames(int amountOfPlayers) {
        this.playersNames = new String[amountOfPlayers];
        Scanner scanner = new Scanner(System.in);

        if (getAmountOfPlayers() > 1 && getAmountOfPlayers() < 5) {
            
            for (int i = 0; i < amountOfPlayers; i++) {
                System.out.println("Type name of player number " + (i + 1) + ":");
                String name = scanner.nextLine();
                this.playersNames[i] = name;
            }
        }

    }


    public String backSuit() {
        String backSuit = "";

        backSuit += topAndDownRow;
        for (int i = 0; i < 5; i++) {
            backSuit += String.format("#%" + CARD_WIDTH + "s#\n", " ");
        }
        backSuit += topAndDownRow;

        return backSuit;
    }

    public String frontSuit(Card card) {
        String frontSuit = "";

        frontSuit += topAndDownRow;
        frontSuit += String.format("#%" + (CARD_WIDTH-1) + "s #\n", card.getCountryName());
        frontSuit += String.format("#%," + (CARD_WIDTH-1) + "d #\n", card.getPopulation());
        frontSuit += String.format("#%," + (CARD_WIDTH-1) + "d #\n", card.getDensity());
        frontSuit += String.format("#%," + (CARD_WIDTH-1) + "d #\n", card.getArea());
        frontSuit += String.format("#%," + (CARD_WIDTH-1) + "d #\n", card.getMedianAge());
        frontSuit += topAndDownRow;

        return frontSuit;
    }

    public void printWinningPlayer(int winningPlayerIndex, ArrayList<Player> players) {
        System.out.println("\nThis turn won: " + players.get( winningPlayerIndex).getName() + "\n");
    }

    private String tableView(ArrayList<Card> handCards, int currentPlayer, ArrayList<Player> players) {
        String[][] table = new String[handCards.size() + 1][7];

        fillTableWithLegend(table);
        fillTableWithCards(handCards, table);

        return tableToString(handCards, table, currentPlayer, players);

    }

    private void fillTableWithLegend(String[][] table) {
        String[] legend = { "Legend:", "Name", "Population", "Density", "Area", "Median Age", "" };

        for (int i = 0; i < legend.length; i++) {
            table[0][i] = legend[i];
        }
    }

    private void fillTableWithCards(ArrayList<Card> handCards, String[][] table) {
        int index = 1;

        for (Card c : handCards) {
            String currentCardSuit = "";

            if (c.getIsFaceUp()) {
                currentCardSuit = frontSuit(c);
            } else {
                currentCardSuit = backSuit();
            }

            String[] currentCardSuitArray = currentCardSuit.split("\n");

            for (int i = 0; i < currentCardSuitArray.length; i++) {
                table[index][i] = currentCardSuitArray[i];
            }

            index++;
        }
    }

    private String tableToString(ArrayList<Card> handCards, String[][] table, int currentPlayer, ArrayList<Player> players) {
        String tableViewString = "";

        tableViewString += "Current player: " + players.get(currentPlayer).getName() + "\n\n";

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < handCards.size() + 1; j++) {
                tableViewString += String.format("%20s\t", table[j][i]);
            }
            tableViewString += "\n";
        }

        tableViewString += String.format("%20s\t", " ");

        for (Player p: players) {
            if (p.getIsActive()){
                tableViewString += String.format("%20s\t", p.getName() + ": " + p.getStockPile().getSize());
            }
        }


        return tableViewString;
    }

    public void printTableView(ArrayList<Card> handCards, int currentPlayer, ArrayList<Player> players) {
        System.out.println(tableView(handCards, currentPlayer, players));
    }

    private String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }

    private int getIntInput() {
        Scanner intScanner = new Scanner(System.in);
        String intString;
        boolean isNotInteger = true;
        int number = 0;

        while (isNotInteger) {
            intString = intScanner.nextLine();

            try {
                number = Integer.parseInt(intString);
                isNotInteger = false;
            } catch (NumberFormatException e) {
                System.out.println("Error! Type an integer...");
                isNotInteger = true;
            }
        }
        return number;
    }

}