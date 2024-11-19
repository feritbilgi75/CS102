import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu
 */
public class Menu extends JFrame {

    public boolean navigate = false;
    JPanel menuPanel; // Class level variable used 
    JLabel label1 = new JLabel("Name:");
    JLabel label2 = new JLabel("Speed:");
    JTextField nameText;
    JTextField speedText;
    JButton startButton = new JButton("Start");
    int height = 200;

    public Menu() {
        menuPanel = new JPanel(); 
        nameText = new JTextField();
        speedText = new JTextField();
        setSize(300, height);

        JPanel textPanel1 = new JPanel();
        JPanel textPanel2 = new JPanel();

        navigate = false;
        menuPanel.setLayout(new GridLayout(3, 1));

        textPanel1.setLayout(new GridLayout(1, 2));
        textPanel1.add(label1);
        textPanel1.add(nameText);
        menuPanel.add(textPanel1);

        textPanel2.setLayout(new GridLayout(1, 2));
        textPanel2.add(label2);
        textPanel2.add(speedText);
        menuPanel.add(textPanel2);

        menuPanel.add(startButton);

        startButton.addActionListener(new startListener());
        setTitle("Ship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(menuPanel); // Adds the menu panel to the JFrame
        setVisible(true); // Makes the frame visible
    }

    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) { // Catching the specific exception
            return false;
        }
    }
    

    class startListener implements ActionListener {

        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (nameText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty", "Message", JOptionPane.WARNING_MESSAGE);
            } else if (speedText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Speed cannot be empty", "Message", JOptionPane.WARNING_MESSAGE);
            } else if (!isNumber(speedText.getText())) {
                JOptionPane.showMessageDialog(null, "Speed must be a number", "Message", JOptionPane.WARNING_MESSAGE);
            } 
            else {
                int speed = Integer.parseInt(speedText.getText()); 
                String name = nameText.getText(); 
                JFrame gameFrame = new GameFrame(speed, name); 
                gameFrame.setVisible(true); 
                dispose(); // Close the current window
            }
            
        }
    }

    
}
