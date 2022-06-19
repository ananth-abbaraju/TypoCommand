import javax.swing.*;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.SwingUtilities;
import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;

public class LaserCannon {

    private int xPos, yPos;
    private LetterContainer container;

    public LaserCannon(TypoCommand p)  {
        xPos = 500;
        yPos = 625;
        container = null;
    }

    public void paint(Graphics g) {

        int[] x = {425, 500, 575};
        int[] y = {750, 625, 750};
        g.setColor(Color.GREEN);
        g.fillPolygon(x, y, 3);

        if(container != null) {
            g.setColor(Color.RED);
            g.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);
            g.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);

            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(5));
            g2.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);
            g2.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);

            container = null;
        }
    }   

    public void shoot(Graphics g, LetterContainer container) {
    
        this.container = container;
        g.setColor(Color.RED);
        g.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);
        g.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);
        g2.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);
        g.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);
        g.drawLine(xPos, yPos, container.getXPos() + 25, container.getYPos() +25);

        paint(g);     
    }
}
