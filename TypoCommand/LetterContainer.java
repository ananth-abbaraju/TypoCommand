import java.awt.Font;import javax.swing.*;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.SwingUtilities;import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;import javax.swing.JPanel;import javax.swing.JTextArea;import java.awt.Color;

public class LetterContainer extends JLabel {

    private int xPos, yPos, size, cityIndex;
    private String letter;
    private Letter letterObject;
    private boolean exploded;

    public LetterContainer(int xPos, int yPos, String letter, int fontSize, Color color, Letter reference) {

        this.xPos = xPos;
        this.yPos = yPos;
        this.letter = letter;
        size = fontSize;
        letterObject = reference;
        exploded = false;

        if(xPos < 255) {
            cityIndex = 0;
        } else if(xPos <= 425) {
            cityIndex = 1;
        } else if(xPos < 575 && xPos > 425) {
            cityIndex = 4;
        } else if(xPos < 825) {
            cityIndex = 2;
        }  else {
            cityIndex = 3;
            
        }

        setSize(50,50);
        setLocation(xPos, yPos);
        setLayout(null);
        setBackground(Color.BLACK);
        repaint();
        setVisible(true);
    }

    public void paint(Graphics g) {

        super.paint(g);
        
        if(exploded) {

            g.setColor(Color.RED);
            g.fillOval(xPos, yPos, 50, 50);
            g.setColor(Color.ORANGE);
            g.fillOval(xPos+7, yPos+7, 35, 35);

        } else {

            setSize(50,50);
            setLocation(xPos, yPos);
            JLabel tempString = new JLabel();
            g.setFont(new Font("Arial", Font.BOLD, size));

            if(letterObject.isDiagonal()) {
                tempString.setForeground(Color.BLUE);
            } else if (letterObject.isStrong()) {
                tempString.setForeground(Color.RED);
            } else if(letterObject.isFast()) {
                tempString.setForeground(Color.YELLOW);
            } else {
                tempString.setForeground(Color.WHITE);
            }

            tempString.setBounds(0,0,50,50);
            tempString.setBackground(Color.BLACK);
            tempString.setFont(new Font("Arial", Font.BOLD, 50));
            tempString.setText(letter);
            
            add(tempString);
        }

        exploded = false;
    }

    // getters
    public int getXPos() {
        return xPos;
    }
    public int getYPos() {
        return yPos;
    }
    public int getCityIndex() {
        return cityIndex;
    }
    public Letter getLetterObject() {
        return letterObject;
    }

    // setters
    public void setLetterObject(Letter newLetter) {
        this.letterObject = newLetter;
    }
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
    public void setXPos(int xPos) {
        this.xPos = xPos;
        reIndex();
    }

    public void reIndex() {
        if(getXPos() < 255) {
            cityIndex = 0;
        } else if(getXPos() <= 425) {
            cityIndex = 1;
        } else if(getXPos() < 575 && getXPos() > 425) {
            cityIndex = 4;
        } else if(getXPos() < 825) {
            cityIndex = 2;
        }  else {
            cityIndex = 3;
        }
    }

    public void explode(Graphics g) {
        exploded = true;
        paint(g);
        g.setColor(Color.RED);
        g.fillOval(xPos, yPos, 50, 50);
        g.setColor(Color.ORANGE);
        g.fillOval(xPos+7, yPos+7, 35, 35);
        exploded = false;
    }
}
