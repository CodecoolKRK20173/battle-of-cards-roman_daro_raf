//to be Card

public class Card {
    private boolean isFaceUp;
    private float area;
    private float population;
    private float density;
    private float medianAge;

    public Card(float area, float population, float density, float medianAge) {
        this.isFaceUp = false;
        this.area = area;
        this.population = population;
        this.density = density;
        this.medianAge = medianAge;
    }

    public boolean getIsFaceUp() {
        return this.isFaceUp;
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
    
}