import javax.swing.*;
import java.awt.Color;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.*;import java.awt.image.*;import java.io.*;import java.util.ArrayList;import javax.imageio.ImageIO;import java.awt.event.KeyListener;import java.awt.event.KeyEvent;

public class TypoCommand extends JPanel {

    private static ArrayList<Letter> letterList;
    private static ArrayList<LetterContainer> containerList;
    private static ArrayList<City> cityList;
    private LaserCannon cannon;
    private LetterContainer letterHit;
    private boolean hit, direction;
    private static boolean game;
    private static Image back;
    private static JLabel scoreLabel, directionsLabel;
    private static int score;
    private static JFrame window;
    private static TypoCommand typo;

    public static void main(String[] a) throws InterruptedException {

        window = new JFrame();

        window.setSize(1000, 750);
        window.setTitle("TypoCommand - Ananth");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(Color.WHITE);
        window.setLocationRelativeTo(null);

        scoreLabel = new JLabel();
        scoreLabel.setBounds(700, 0, 300, 80);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 25));
        scoreLabel.setText("Score: ");

        directionsLabel = new JLabel();
        directionsLabel.setBounds(500, 0, 300, 80);
        directionsLabel.setForeground(Color.WHITE);
        directionsLabel.setFont(new Font("Arial", Font.BOLD, 15));
        directionsLabel.setText("Get 750 pts to win");

        typo = new TypoCommand();
        typo.add(scoreLabel);
        typo.add(directionsLabel);
        window.add(typo);
        typo.setVisible(true);
        window.setVisible(true);
        game = true;
  
        // game loop
        while(game) {

            Letter temp = randomLetter();
            if(temp != null) {
                letterList.add(temp);
                LetterContainer tempContainer = new LetterContainer(temp.getXPos(), temp.getYPos(),temp.getLetter(), temp.getSize(), Color.GREEN, temp);
                typo.add(tempContainer);
                containerList.add(tempContainer);
            }

            typo.check();
            typo.move();

            boolean dead = false;
            for(City c : cityList) {
                if(c.getLives() == 0) {
                    dead = true;
                } 
            }

            if(dead) {

                window.remove(typo);

                JPanel losePanel = new JPanel();
                losePanel.setSize(1000, 750);
                losePanel.setLocation(0, 0);
                losePanel.setLayout(null);
                losePanel.setBackground(Color.BLACK);
                losePanel.setVisible(true);

                JLabel loseLabel = new JLabel("You Lose!");
                loseLabel.setFont(new Font("Arial", Font.BOLD, 50));
                loseLabel.setForeground(Color.WHITE);
                loseLabel.setBounds(370, 275, 500, 200);
                losePanel.add(loseLabel);
                window.add(losePanel);
                window.repaint();
                game = false;
                break;

            } else if(score >= 750) {

                window.remove(typo);

                JPanel winPanel = new JPanel();
                winPanel.setSize(1000, 750);
                winPanel.setSize(1000, 750);
                winPanel.setLocation(0, 0);
                winPanel.setLayout(null);
                winPanel.setBackground(Color.BLACK);
                winPanel.setVisible(true);

                JLabel winLabel = new JLabel("You Win!");
                winLabel.setFont(new Font("Arial", Font.BOLD, 50));
                winLabel.setForeground(Color.WHITE);
                winLabel.setBounds(370, 275, 500, 200);
                winPanel.add(winLabel);
                window.add(winPanel);
                window.repaint();
                game = false;
                break;

            }

            Thread.sleep(30);

        }
    }

    public TypoCommand() throws InterruptedException {

        //instantiations
        letterList = new ArrayList<Letter>();
        containerList = new ArrayList<LetterContainer>();
        cityList = new ArrayList<City>();
        hit = false;
        direction = true;
        score = 0;

        // cities
        cityList.add(new City(5, 650));
        cityList.add(new City(255, 650));
        cityList.add(new City(575, 650));
        cityList.add(new City(825, 650));

        // cannon
        cannon = new LaserCannon(this);

        //panel features
        setSize(1000, 750);
        setLocation(0, 0);
        setLayout(null);
        setBackground(Color.BLACK);

        setVisible(true);

        addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {

                for(int i = containerList.size() - 1; i >= 0; i--) {
                    
                    if(e.getKeyText(e.getKeyCode()).equals(containerList.get(i).getLetterObject().getLetter())) {

                        hit = true;
                        letterHit = containerList.get(i);
                        move(); 

                        if(containerList.get(i).getLetterObject().isStrong()) {

                            containerList.get(i).getLetterObject().removeLife();

                            if(containerList.get(i).getLetterObject().getLives() == 1) {
                                containerList.get(i).getLetterObject().notStrong();
                            }
                        } else {
                            score+=50;
                            scoreLabel.setText("Score: " + (score));

                            containerList.get(i).removeAll();
                            remove(containerList.get(i));
                            containerList.remove(i);
                        }

                        break;
                    }

                }
            }
            public void keyReleased(KeyEvent e) {}
        });
        setFocusable(true);
    }
    public static Letter randomLetter() {
        int chance = (int)(Math.random()*100) + 1;
        
        if(chance <= 2) {
            return new Letter();
        } else if (chance <= 4) {
            return new FastLetter();
        } else if (chance <= 6) {
            return new DiagonalLetter();
        } else if (chance <= 7) {
            return new StrongLetter();
        } else {
            return null;
        }
    }

    public void paint(Graphics g) {

        super.paint(g);

        for(City c : cityList) {
            c.paint(g);
        }

        for(int i = 0; i < containerList.size(); i++) {
            containerList.get(i).repaint();
        }

        cannon.paint(g);

        if(hit) {

            cannon.shoot(g, letterHit);
            cannon.paint(g);
            letterHit.explode(g);
            cannon.paint(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(5));
            g2.drawLine(500, 625, letterHit.getXPos() + 25, letterHit.getYPos() +25);
            g.setColor(Color.RED);
            g.fillOval(letterHit.getXPos(), letterHit.getYPos(), 50, 50);
            g.setColor(Color.ORANGE);
            g.fillOval(letterHit.getXPos()+7, letterHit.getYPos()+7, 35, 35);
       
            hit = false;
            letterHit = null;

        }

        hit = false;
        letterHit = null;
    }

    public void move() {

        if(letterHit == null && hit == false) {

            for(int i = containerList.size() - 1; i >= 0; i--) {

                containerList.get(i).setYPos(containerList.get(i).getYPos() + containerList.get(i).getLetterObject().getSpeed());
                
                // for diagonal letter
                if(containerList.get(i).getXPos() + containerList.get(i).getLetterObject().getXSpeed() >= 950 || containerList.get(i).getXPos() + containerList.get(i).getLetterObject().getXSpeed() <= 50) {
                    
                    direction = !direction;
                    containerList.get(i).getLetterObject().setXSpeed(containerList.get(i).getLetterObject().getXSpeed() * -1);

                }

                containerList.get(i).setXPos(containerList.get(i).getXPos() + containerList.get(i).getLetterObject().getXSpeed());
            }

        } 

        repaint();
    }

    public void check() {

        for(int i = containerList.size() - 1; i >= 0; i--) {

            if(containerList.get(i).getYPos() >= 580) {
                
                containerList.get(i).reIndex();

                if(containerList.get(i).getCityIndex() < 4) {

                    cityList.get(containerList.get(i).getCityIndex()).removeLife();
                    containerList.get(i).removeAll();
                    remove(containerList.get(i));
                    containerList.remove(i);

                } else {

                    window.remove(typo);

                    JPanel losePanel = new JPanel();
                    losePanel.setSize(1000, 750);
                    losePanel.setLocation(0, 0);
                    losePanel.setLayout(null);
                    losePanel.setBackground(Color.BLACK);
                    losePanel.setVisible(true);

                    JLabel loseLabel = new JLabel("You Lose!");
                    loseLabel.setFont(new Font("Arial", Font.BOLD, 50));
                    loseLabel.setForeground(Color.WHITE);
                    loseLabel.setBounds(370, 275, 500, 200);
                    losePanel.add(loseLabel);
                    window.add(losePanel);
                    window.repaint();
                    game = false;

                }
            }
        } 
    }    
}