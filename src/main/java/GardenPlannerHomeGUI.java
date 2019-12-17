import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GardenPlannerHomeGUI extends JFrame {
    private JButton openSavedGardenButton;
    private JPanel homeScreen;
    private JButton newGardenButton;
    private JTable viewSavedGardens;


    GardenPlannerHomeGUI(){
        setContentPane(homeScreen);
        setPreferredSize(new Dimension(500,500));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Listeners();
    }

    private void ShowSavedGardens(){
        //shows saved gardens in jtable for selection
    }

    private void Listeners() {
        newGardenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GardenPlannerCreateGUI createScreen = new GardenPlannerCreateGUI();
            }
        });
    }

}
