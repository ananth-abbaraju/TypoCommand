public class DiagonalLetter extends Letter {

    private int size, speed, xSpeed, cityIndex;

    public DiagonalLetter() {
        size = 50;
        speed = 3;

        if(super.getXPos() >= 500) {
            xSpeed = -2;
        } else {
            xSpeed = 2;
        }
    }

    public int getSpeed() {
        return speed;
    }
    public int getXSpeed() {
        return xSpeed;
    }
    public int getCityindex() {
        reIndex();
        return cityIndex;
    }
    
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
   
    public boolean isDiagonal() {
        return true;
    }
}

