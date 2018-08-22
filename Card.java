
import java.lang.Comparable;


//to be Card
public class Card implements Comparable<Card> {
    private boolean isFaceUp;
    private String countryName;
    private float area;
    private float population;
    private float density;
    private float medianAge;

    public Card(String countryName, float population, float density, float area, float medianAge) {
        this.isFaceUp = false;
        this.countryName = countryName;
        this.area = area;
        this.population = population;
        this.density = density;
        this.medianAge = medianAge;
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
    
    @Override
    public int compareTo(Card card){
        if(this.getArea() < card.getArea()) return -1;
        if(this.getArea() > card.getArea()) return 1;
        else return 0;
    }


}