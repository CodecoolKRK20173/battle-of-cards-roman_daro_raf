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
        frontSuit += String.format("#%" + CARD_WIDTH + "s#\n", card.getCountryName());
        frontSuit += String.format("#%" + CARD_WIDTH + "d#\n", card.getPopulation());
        frontSuit += String.format("#%" + CARD_WIDTH + "d#\n", card.getDensity());
        frontSuit += String.format("#%" + CARD_WIDTH + "d#\n", card.getArea());
        frontSuit += String.format("#%" + CARD_WIDTH + "d#\n", card.getMedianAge());
        frontSuit += topAndDownRow;

        return frontSuit;
    }

    public String tableView(ArrayList<Card> handCards, int currentPlayer, ArrayList<Player> players) {
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
            tableViewString += String.format("%20s\t", p.getName());
        }

        return tableViewString;
    }

    private String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }

    public static void main(String[] args) {
        View nowy = new View();
        Card nowa1 = new Card();
        nowa1.setCountryName("dupa");
        nowa1.setPopulation(1);
        nowa1.setDensity(1);
        nowa1.setArea(1);
        nowa1.setMedianAge(1);
        nowa1.setFaceUp();
        Card nowa2 = new Card();
        nowa2.setCountryName("dupa");
        nowa2.setPopulation(200);
        nowa2.setDensity(200);
        nowa2.setArea(200);
        nowa2.setMedianAge(200);
        nowa2.setFaceUp();
        Card nowa3 = new Card();
        nowa3.setCountryName("dupahuj");
        nowa3.setPopulation(4100);
        nowa3.setDensity(4100);
        nowa3.setArea(4100);
        nowa3.setMedianAge(4200);
        nowa3.setFaceUp();

        Card nowa4 = new Card();
        nowa4.setCountryName("dupahuj");
        nowa4.setPopulation(4100);
        nowa4.setDensity(4100);
        nowa4.setArea(4100);
        nowa4.setMedianAge(4200);
        nowa4.setFaceDown();

        ArrayList<Card> dupaaa = new ArrayList<Card>();
        dupaaa.add(nowa1);
        dupaaa.add(nowa2);
        dupaaa.add(nowa3);
        dupaaa.add(nowa4);

        // System.out.println(nowy.frontSuit(nowa));
        // System.out.println(nowy.backSuit());

        System.out.println(nowy.tableView(dupaaa, 2, new ArrayList<Player>(new Player("DUPAAAAAAAAAA"))));
    }
}