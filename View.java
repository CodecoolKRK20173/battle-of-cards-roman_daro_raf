import java.util.ArrayList;

public class View{
    final int CARD_WIDTH = 13;
    String upAndDownRow = "###############\n";

    public String backSuit() {
        String backSuit = "";

        backSuit += upAndDownRow;
        for (int i = 0; i < 5; i++) {
            backSuit += String.format("#%" + CARD_WIDTH + "s#\n", " ");
        }
        backSuit += upAndDownRow;

        return backSuit;
    }

    public String frontSuit(Card card) {
        String frontSuit = "";
        
        frontSuit += upAndDownRow;
        frontSuit += String.format("#%" + CARD_WIDTH + "s#\n", card.getCountryName());
        frontSuit += String.format("#%" + CARD_WIDTH + "f#\n", card.getPopulation());
        frontSuit += String.format("#%" + CARD_WIDTH + "f#\n", card.getDensity());
        frontSuit += String.format("#%" + CARD_WIDTH + "f#\n", card.getArea());
        frontSuit += String.format("#%" + CARD_WIDTH + "f#\n", card.getMedianAge());
        frontSuit += upAndDownRow;


        return frontSuit;
    }

    public String tableView(ArrayList<Card> handCards) {
        String[] legend = {"Legend:", "Name", "Population", "Density", "Area", "Median Age"};
        String[][] table = new String[7][handCards.size() + 1];

        for (int i = 0; i < legend.length; i++) {
            table[0][i] = legend[i];
        }
    }

    public static void main(String[] args) {
        Card nowa = new Card();
        View nowy = new View();
        nowa.setCountryName("dupa");
        nowa.setPopulation(100);
        nowa.setDensity(100);
        nowa.setArea(100);
        nowa.setMedianAge(200);

        System.out.println(nowy.frontSuit(nowa));
        System.out.println(nowy.backSuit());
    }
}