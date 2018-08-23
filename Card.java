//to be Card

public class Card {
    private boolean isFaceUp;
    private String countryName;
    private int area;
    private int population;
    private int density;
    private int medianAge;
    private int ownerIndex;

    public Card() {
        this.isFaceUp = false;
    }

    public boolean getIsFaceUp() {
        return this.isFaceUp;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getArea() {
        return this.area;
    }

    public int getPopulation() {
        return this.population;
    }

    public int getDensity() {
        return this.density;
    }

    public int getMedianAge() {
        return this.medianAge;
    }

    public int getOwnerIndex() {
        return ownerIndex;
    }

    public void setFaceUp() {
        this.isFaceUp = true;
    }

    public void setFaceDown() {
        this.isFaceUp = false;
    }

    public void flip() {
        this.isFaceUp = !this.isFaceUp;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public void setMedianAge(int medianAge) {
        this.medianAge = medianAge;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setOwnerIndex(int index) {
        this.ownerIndex = index;
    }
    
}