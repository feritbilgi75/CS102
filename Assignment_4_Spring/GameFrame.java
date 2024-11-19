import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameFrame extends JFrame {

    Ship ship;
    public GameFrame(int speed, String name) {
        setTitle("Life: " + 3 + " Score: " + 0);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel(speed, name);
        add(gamePanel);

        setVisible(true);
    }

    class GamePanel extends JPanel implements ActionListener, MouseMotionListener {
        int speed;
        String name;
        Timer timer;
         
        ArrayList<InteractableDrawing> objects = new ArrayList<>();
        Random random = new Random();
        int life = 3;
        int score = 0;

        public GamePanel(int speed, String name) {
            this.speed = speed;
            this.name = name;
            ship = new Ship(name); // Initialize the ship with the player's name
            setBackground(Color.BLUE);
            addMouseMotionListener(this);

            // Timer for game loop
            timer = new Timer(1000 / 60, this); // Aim for 60 FPS
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, getWidth(), getHeight());

            // Draw game objects
            ship.draw(g); // Ship drawing logic
            for (InteractableDrawing obj : objects) {
                obj.draw(g); // Draw each game object
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Add new objects randomly
            int counter = 0;
            if (random.nextInt(100) < 2) { // Makes slow spawning of bombs and apples because of timer fps setting (1000/60).
                if (random.nextBoolean()) {
                    objects.add(new Bomb()); // Add Bomb
                    counter++;
                    if (counter > 2){
                        objects.add(new Apple());
                        counter = 0;
                    }
                } else {
                    objects.add(new Apple()); // Add Apple
                }
            }
            if (random.nextInt(100) < 2) { 
                if (random.nextBoolean()) {
                    objects.add(new Bomb()); // Add Bomb
                } else {
                    objects.add(new Apple()); // Add Apple
                }
            }
            

            // Move and check collisions
            Iterator<InteractableDrawing> iterator = objects.iterator();
            while (iterator.hasNext()) {
                InteractableDrawing obj = iterator.next();
                if (obj.moveLeft(speed)) {
                    iterator.remove();
                } else {
                    if (obj.intersects(ship)){
                        obj.interact(ship);
                        iterator.remove();
                        setTitle("Life: " + ship.life + " Score: " + ship.score);

                    }
                    
                }
            }

            // Check game over condition
            if (ship.life <= 0) {
                gameOver();
            }

            repaint();
        }

        private void gameOver() {
            timer.stop();
            int option = JOptionPane.showConfirmDialog(null, "Score: " + ship.score + ", do you want play again?", "Select an Option", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                System.exit(0);
            }
        }

        private void restartGame() {
            ship.life = 3;
            ship.score = 0;
            objects.clear();
            ship = new Ship(name); // Reset or reinitialize ship
            setTitle("Life: " + 3 + " Score: " + 0);
            timer.restart();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            ship.setPosition(e.getX(), e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }
    }
    
    
    
    
}
