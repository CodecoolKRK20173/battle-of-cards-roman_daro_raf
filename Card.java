//to be Card

public class Card {
    private boolean isFaceUp;
    private String countryName;
    private float area;
    private float population;
    private float density;
    private float medianAge;

    public Card() {
        this.isFaceUp = false;
    }

    public boolean getIsFaceUp() {
        return this.isFaceUp;
    }

    public String getCountryName() {
        return countryName;
    }

    public float getArea() {
        return this.area;
    }

    public float getPopulation() {
        return this.population;
    }

    public float getDensity() {
        return this.density;
    }

    public float medianAge() {
        return this.medianAge;
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

    public void setArea(float area) {
        this.area = area;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public void setMedianAge(float medianAge) {
        this.medianAge = medianAge;
    }

    public void setPopulation(float population) {
        this.population = population;
    }
    
}