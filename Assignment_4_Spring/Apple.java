import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;


public class Apple extends JComponent implements InteractableDrawing {

    int bombX = 500;
    Random random = new Random();
    int bombY = random.nextInt(500);
    boolean outOfBounds = false;



    @Override
    public boolean intersects(Ship s) {
        boolean intersects = false;
        if (((Math.abs(s.x - bombX) < 80) && (s.x - bombX < 0)) && ((Math.abs(s.y - bombY) < 30) && (s.y - bombY < 0))){
            intersects = true;
        }
        else if (((Math.abs(s.x - bombX) < 40) && (s.x - bombX > 0)) && ((Math.abs(s.y - bombY) < 40) && (s.y - bombY > 0))){
            intersects = true;
        }
        return intersects;
    }

    @Override
    public void interact(Ship s) {
        if (intersects(s)){
            s.score++;
            bombX = -5;
            moveLeft(1000);
        }
    }

    @Override
    public boolean moveLeft(int speed) {
        if (bombX > 0){
            bombX -= speed;
            repaint();
        }
        
        else{
            outOfBounds = true;
        }
        return outOfBounds;
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.GRAY);
        g.fillRect(bombX, bombY, 40, 40);
        g.setColor(Color.RED);
        g.fillOval(bombX, bombY, 40, 40);
        
        
    }
    
}
