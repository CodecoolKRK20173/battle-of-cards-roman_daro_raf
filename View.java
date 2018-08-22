public class View{
    final int CARD_WIDTH = 13;

    public String backSuit() {
        String backSuit = "";
        String upAndDownRow = "###############";

        backSuit += upAndDownRow;
        for (int i = 0; i < 5; i++) {
            backSuit += String.format("#%" + CARD_WIDTH + "s#\n", " ");
        }
        backSuit += upAndDownRow;

        return backSuit;
    }

    public String frontSuit(Card card) {
        String frontSuit = "";
        

        return null;
    }
}