//Stage D: a class whose actionPerformed method writes to standard output.
import java.awt.event. ActionEvent;
import java.awt.event. ActionListener;
import java.util.      Date;
public class JButtonListener implements ActionListener {
    public JButtonListener() {

    }

    public void actionPerformed(final ActionEvent pActionEvent) {
        final Date tDate = new Date();
        System.out.println(tDate);
    }
}