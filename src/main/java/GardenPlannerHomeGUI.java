/*Configuration of the start menu GUI
it also triggers creation of the second GUI window when new button is clicked
CREATED BY: KELSEY STIFF
* */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GardenPlannerHomeGUI extends JFrame {
    private JButton openSavedGardenButton; //feature to open saved garden not implemented yet
    private JPanel homeScreen;
    private JButton newGardenButton;
    private JTable viewSavedGardens; //feature to open saved garden not implemented yet


    GardenPlannerHomeGUI(){
        setContentPane(homeScreen);
        setPreferredSize(new Dimension(500,500));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Listeners();
    }

    private void ShowSavedGardens(){
        //This method will call database to show saved gardens, not implemented.
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
