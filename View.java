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
        frontSuit += card.getCountryName() + "\n";
        frontSuit += card.getPopulation() + "\n";
        frontSuit += card.getDensity() + "\n";
        frontSuit += card.getArea() + "\n";
        frontSuit += card.getMedianAge() + "\n";
        frontSuit += upAndDownRow;


        return frontSuit;
    }
}