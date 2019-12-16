import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GardenPlannerCreateGUI extends JFrame{

    private JPanel mainPanel;
    private JTable gardenPlot;
    private JSlider gardenHeightSlider;
    private JSlider gardenWidthSlider;
    private JLabel showGardenWidth;
    private JLabel showGardenHeight;
    private JList plants;
    private JTextField gardenPlotName;
    private JLabel showGardenName;
    private JPanel GardenSpecPanel;
    private JPanel gardenPlotPanel;
    private JButton CLEARButton;
    private JButton saveButton;
    private Object selection;
    private String gardenNameStr;
    //private ImageIcon carrot = new ImageIcon("images/carrot.png");



    GardenPlannerCreateGUI(){
        gardenPlotName.getText();
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(1200,800));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setupUI();

        gardenHeightSlider.setValue(2);
        gardenWidthSlider.setValue(2);

        listeners();
    }



    private void listeners() {



        gardenPlotName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                gardenNameStr = gardenPlotName.getText();
                showGardenName.setText(gardenNameStr);
            }
            @Override
            public void removeUpdate(DocumentEvent e) { }
            @Override
            public void changedUpdate(DocumentEvent e) { }
        });

        gardenHeightSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String showHeightSliderValue = gardenHeightSlider.getValue() + " Sq Ft";
                showGardenHeight.setText(showHeightSliderValue);
                createGardenPlot();

            }
        });

        gardenWidthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String showWidthSliderValue = gardenWidthSlider.getValue() + " Sq Ft";
                showGardenWidth.setText(showWidthSliderValue);
                createGardenPlot();
            }
        });

        plants.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selection = plants.getSelectedValue();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        gardenPlot.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = gardenPlot.rowAtPoint(e.getPoint());
                int column = gardenPlot.columnAtPoint(e.getPoint());
                gardenPlot.setValueAt(selection,row,column);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writeGardenImageToFile(createImageOfGardenPlot(gardenPlot));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });



    }

    private void setupUI(){

        gardenHeightSlider.setValue(10);
        gardenWidthSlider.setValue(10);

        gardenPlot.setRowHeight(50);

        //List Set up
        DefaultListModel plantListModel = new DefaultListModel();
        plants.setModel(plantListModel);
        plants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        plantListModel.addElement("Carrot");
        plantListModel.addElement("Pepper");
        plantListModel.addElement("Cucumber");


        //Height and width label start at 0 sq ft
        showGardenHeight.setText("0 Sq Ft");
        showGardenWidth.setText("0 Sq Ft");

        //height and width sliders only go from 1-10
        gardenWidthSlider.setMinimum(1);
        gardenWidthSlider.setMaximum(10);
        gardenHeightSlider.setMinimum(1);
        gardenHeightSlider.setMaximum(10);


    }

    //this method created an resizable table based off of user input from Jslider
    public void createGardenPlot(){
        int numRows = gardenHeightSlider.getValue();
        int numCols = gardenWidthSlider.getValue();
        DefaultTableModel gardenPlotModel = new DefaultTableModel(numRows, numCols);
        gardenPlot.setModel(gardenPlotModel);
    }

    public static BufferedImage createImageOfGardenPlot(JTable gardenPlot) {
            JTableHeader tableHeader = gardenPlot.getTableHeader();
            int totalWidth = tableHeader.getWidth() + gardenPlot.getWidth();
            int totalHeight = tableHeader.getHeight() + gardenPlot.getHeight();
            BufferedImage gardenPlotImage = new BufferedImage(totalWidth, totalHeight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2D = (Graphics2D) gardenPlotImage.getGraphics();
            tableHeader.paint(g2D);
            g2D.translate(0, tableHeader.getHeight());
            gardenPlot.paint(g2D);
            return gardenPlotImage;
        }

        public File writeGardenImageToFile(BufferedImage gardenPlotImage) throws IOException {
            String fileName = gardenPlotName.getText();
            File file = new File(fileName + ".png");
            ImageIO.write(gardenPlotImage, "png", file);
            return file;
        }

        public void saveGarden(File gardenImage){
            String gardenName = gardenPlotName.getText();
            if (gardenName == null || gardenName.trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Please enter a title for the new movie");
                return;
            }

           // GardenPlannerDB.addSavedGarden(gardenName,gardenImage);

        }


}
