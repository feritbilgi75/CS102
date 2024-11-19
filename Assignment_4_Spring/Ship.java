import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

public class Ship extends JComponent  {
    int x = 400;
    int y = 250;
    String name;
    int width = 80;
    int height = 30;

    int life = 3;
    int score = 0;

    public Ship(String name) {
        this.name = name;   
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g); // Call the draw method from within paintComponent
    }

    // Custom draw method to organize drawing code
    void draw(Graphics g) {
        g.setColor(Color.YELLOW); // Set color for the ship
        g.fillRect(x, y, width, height);
        g.setColor(Color.RED); // Set color for the name
        g.drawString(name, x, y + height); 
    }

    public void setPosition(int x, int y) {
        this.x = x - width / 2; // Adjust x to center the ship on the point
        this.y = y - height / 2; // Adjust y to center the ship on the point
        repaint(); // Repaint to update the ship's position on the screen
    }

  

    public void setPosition(Point point) {
        setPosition(point.x, point.y); // Reuse the existing setPosition method
    }
}
