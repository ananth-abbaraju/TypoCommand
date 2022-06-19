public class FastLetter extends Letter {

    private int size, speed;

    public FastLetter() {
        size = 50;
        speed = 7;
    }

    public int getSpeed() {
        return speed;
    }
    
    public boolean isFast() {
        return true;
    }
}
