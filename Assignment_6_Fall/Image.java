import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Image extends JPanel implements ActionListener, Comparable {

    BufferedImage gorsel, sortedImage;
    javax.swing.Timer timer;
    int currentDiagonal = 0;
    static int frameHeight;
    static int frameWidth;
    double resizeFactor = 1.0;
    String[] imagePaths = {"file.jpeg", "ferit.jpeg"};
    int currentImageIndex = 0;
    int[] iterationCounts;
    BufferedImage[] sortedImages;

    private static class Pixel {
        int x, y;
        Color color;
        Pixel(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public Image() {
        timer = new javax.swing.Timer(10, this);
        iterationCounts = new int[imagePaths.length];
        sortedImages = new BufferedImage[imagePaths.length];
    }

    private void sortDiagonal(BufferedImage img, int diagonal) {
        int width = img.getWidth();
        int height = img.getHeight();
    
        // Define the bounds of the diagonal within the image
        int startX = Math.max(0, diagonal - height + 1);
        int startY = Math.max(0, height - 1 - diagonal);
        int endX = Math.min(width - 1, diagonal);
    
        // Calculate the length of the diagonal
        int len = endX - startX + 1;
    
        // Create a list to store pixel information for the diagonal
        List<Pixel> diagonalPixels = new ArrayList<Pixel>();
    
        // Extract pixel information along the diagonal
        for (int i = 0; i < len; i++) {
            int x = startX + i;
            int y = startY + i;
            diagonalPixels.add(new Pixel(x, y, new Color(img.getRGB(x, y))));
        }
    
        // Sort the diagonal pixels based on brightness in descending order
        diagonalPixels.sort(Comparator.comparingInt(p -> -calculateBrightness(p.color)));
    
        // Apply the sorted pixels back to the image
        for (int i = 0; i < len; i++) {
            Pixel pixel = diagonalPixels.get(i);
            int x = startX + i;
            int y = startY + i;
            img.setRGB(x, y, pixel.color.getRGB());
        }
    }
    
    

    public void addImage() {
        try {
            gorsel = ImageIO.read(new File(imagePaths[currentImageIndex]));
            if (sortedImages[currentImageIndex] == null) {
                sortedImage = new BufferedImage(gorsel.getWidth(), gorsel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics g = sortedImage.getGraphics();
                g.drawImage(gorsel, 0, 0, null);
                g.dispose();
                sortedImages[currentImageIndex] = sortedImage;
            } else {
                sortedImage = sortedImages[currentImageIndex];
            }
            currentDiagonal = iterationCounts[currentImageIndex];
            frameHeight = gorsel.getHeight();
            frameWidth = gorsel.getWidth();
            timer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public void reImage() {
        currentDiagonal = 0;
        sortedImage.getGraphics().drawImage(gorsel, 0, 0, null);
        timer.restart();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sortedImage != null) {
            int newWidth = (int) (resizeFactor * sortedImage.getWidth());
            int newHeight = (int) (resizeFactor * sortedImage.getHeight());
            g.drawImage(sortedImage, 0, 0, newWidth, newHeight, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int totalDiagonals = frameWidth + frameHeight - 1;
        if (currentDiagonal < totalDiagonals) {
            sortDiagonal(sortedImage, totalDiagonals - currentDiagonal - 1);
            currentDiagonal++;
            iterationCounts[currentImageIndex] = currentDiagonal; 
            repaint();
        } else {
            timer.stop();
        }
    }

    

    private int calculateBrightness(Color color) {
        return (int) (color.getRed() * 0.2126 + color.getGreen() * 0.7152 + color.getBlue() * 0.0722);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Image img = new Image();
            img.addImage();
            JFrame frame = new JFrame();
            frame.setContentPane(img);
            frame.setSize(frameWidth + 16, frameHeight + 39);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        img.currentImageIndex = (img.currentImageIndex - 1 + img.imagePaths.length) % img.imagePaths.length;
                        img.currentDiagonal = img.iterationCounts[img.currentImageIndex]; 
                        img.addImage();
                    } 
                    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        img.currentImageIndex = (img.currentImageIndex + 1) % img.imagePaths.length;
                        img.currentDiagonal = img.iterationCounts[img.currentImageIndex]; 
                        img.addImage();
                    } 
                    
                    else if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
                        img.reImage();
                    }
                }
            });

            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    img.resizeFactor = Math.min(
                            (double) frame.getWidth() / img.gorsel.getWidth(),
                            (double) frame.getHeight() / img.gorsel.getHeight()
                    );
                    img.repaint();
                }
            });
        });
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    
}
