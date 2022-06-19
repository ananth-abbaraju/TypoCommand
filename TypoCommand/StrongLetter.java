public class StrongLetter extends Letter{

    private int size, speed, xSpeed, lives;
    private boolean strong;

    public StrongLetter() {
        size = 50;
        speed = 3;
        xSpeed = 0;
        lives = 3;
        strong = true;
    }

    public int getSpeed() {
        return speed;
    }
    public boolean isStrong() {
        return strong;
    }
    public int getLives() {
       return lives;
    }
    public void removeLife() {
       lives--;
    }
    public void notStrong() {
       this.strong = false;
    }
}
