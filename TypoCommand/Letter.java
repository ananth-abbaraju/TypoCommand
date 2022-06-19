import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;

public class Letter {

    private int xPos, yPos, size, speed, xSpeed;
    private String letter;
    private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    
    public Letter() {
        xPos = (int) (Math.random() * 900) + 50;
        yPos = 50; 
        letter = alphabet[(int)(Math.random() * 26)];
        size = 50;
        speed = 3;
        xSpeed = 0;
    }
    
    //getters
    public int getXPos() {
        return xPos;
    }
    public int getYPos() {
        return yPos;
    }
    public int getSize() {
        return size;
    }
    public int getSpeed() {
        return speed;
    }
    public int getXSpeed() {
        return xSpeed;
    }
    public String getLetter() {
        return letter;
    }

    //setters
    public void setXSpeed(int xSpeed) {}
    
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public void nextLetter() {
        letter = alphabet[(int)(Math.random() * 26)];
    }

    public boolean isStrong() {
        return false;
    }
    public int getLives(){
        return 0;
    }

    public void removeLife() {}

    public void leap() {}

    public void notStrong() {}

    public boolean isDiagonal() {
        return false;
    }

    public void reIndex() {}

    public boolean isFast() {
        return false;
    }
}
