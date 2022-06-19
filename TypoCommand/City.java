import javax.swing.*;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.SwingUtilities;import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;

public class City {

    private int xPos, yPos, lives;

    public City(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        lives = 4;
    }

    public void paint(Graphics g) {

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(xPos, yPos, 175, 100);

        int tempX = xPos + 25;
        for(int i = 0; i < lives; i++) {
            g.setColor(Color.GREEN);
            g.fillOval(tempX, yPos + 25, 20, 20);
            tempX += 30;
        }

    }

    public void removeLife() {
        lives--;
    }
    
    public int getLives() {
        return lives;
    }
}
