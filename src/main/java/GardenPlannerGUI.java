import javax.swing.*;
import java.awt.*;

public class GardenPlannerGUI extends JFrame {


    private JPanel mainPanel;
    private JSlider slider1;
    private JSlider slider2;
    private JTable table1;
    private JLabel showWidthJlabel;
    private JLabel showHeightJlabel;

    protected GardenPlannerGUI(){
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500,500));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Listeners();
    }

    private void Listeners() {

    }
}
