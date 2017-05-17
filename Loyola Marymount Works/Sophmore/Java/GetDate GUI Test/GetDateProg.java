//Stage D.5: responding to the click of a button.
import java.awt.    BorderLayout;
import java.awt.    Container;
import javax.swing. JButton;
import javax.swing. JFrame;
import javax.swing. JTextField;
public class GetDateProg {
    public static void main(String[] args) {
        final JFrame tJFrame = new JFrame("GetDateProg: Stage D");
        final JTextField tJTextField = new JTextField("Welcome, Hypebeast.", 35);
        final JButton tJButton  = new JButton("Get Shit.");
        final JButtonListener tJButtonListener = new JButtonListener();
        tJButton.addActionListener(tJButtonListener);
        final Container tContentPane = tJFrame.getContentPane();
        tContentPane.add(tJTextField, BorderLayout.NORTH);
        tContentPane.add(tJButton, BorderLayout.SOUTH);
        tJFrame.pack();
        tJFrame.setVisible(true);
    }
}