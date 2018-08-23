import java.util.ArrayList;
import java.util.Scanner;

public class View {
    final int CARD_WIDTH = 20;
    String topAndDownRow = repeat("#", CARD_WIDTH + 2) + "\n";

    public void menu() {
        Scanner sc = new Scanner(System.in);
        printMainMenu();
        String choice = sc.nextLine();
        boolean isRunning = true;

        while (isRunning) {
            isRunning = false;

            switch (choice) {
                case "1":
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
            tableViewString += String.format("%20s\t", p.getName() + ": " + p.getStockPile().getSize());
        }

        return tableViewString;
    }

    public void printTableView(ArrayList<Card> handCards, int currentPlayer, ArrayList<Player> players) {
        System.out.println(tableView(handCards, currentPlayer, players));
    }

    private String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }

}