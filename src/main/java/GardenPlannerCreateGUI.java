/*
Configuration of the create Screen gui
CREATED BY: KELSEY STIFF
*/



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
import java.io.*;




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
    private JButton exportButton;
    private JButton saveButton;
    private Object selection;
    private String gardenNameStr;


    GardenPlannerCreateGUI(){
        //GUI window set up
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(1200,800));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setupUI();
        listeners();
    }


    //this method hold all listeners for GUI components
    private void listeners() {

        //This Listeners gets text from the name text field and displays it in the name jlabel above the garden
        //The input is also used to name the Document once saved
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

        //resizing the height of the table
        gardenHeightSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String showHeightSliderValue = gardenHeightSlider.getValue() + " Sq Ft";
                showGardenHeight.setText(showHeightSliderValue);
                createGardenPlot();

            }
        });

        //resizing the width of the table
        gardenWidthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                String showWidthSliderValue = gardenWidthSlider.getValue() + " Sq Ft";
                showGardenWidth.setText(showWidthSliderValue);
                createGardenPlot();
            }
        });

        //gets selected list item, stores it in a variable
        plants.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selection = plants.getSelectedValue();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

        //populates selected table cell with selected list item
        gardenPlot.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = gardenPlot.rowAtPoint(e.getPoint());
                int column = gardenPlot.columnAtPoint(e.getPoint());
                gardenPlot.setValueAt(selection,row,column);
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });


        //when export button is clicked, table is saved as an image file
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gardenName = gardenPlotName.getText();
                if (gardenName == null || gardenName.trim().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Enter a Name for your garden");
                } else {
                    JOptionPane.showMessageDialog(rootPane, gardenName + " was saved as an Image");
                    try {
                        writeGardenImageToFile(createImageOfGardenPlot(gardenPlot));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
    }

    private void setupUI(){

        gardenHeightSlider.setValue(2);
        gardenWidthSlider.setValue(2);

        gardenPlot.setRowHeight(50);

        //List Set up
        DefaultListModel plantListModel = new DefaultListModel();
        plants.setModel(plantListModel);
        plants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        plantListModel.addElement("Carrot");
        plantListModel.addElement("Pepper");
        plantListModel.addElement("Cucumber");
        plantListModel.addElement("Tomato");
        plantListModel.addElement("Zuchinni");
        plantListModel.addElement("Potato");
        plantListModel.addElement("Watermelon");
        plantListModel.addElement("Asparagus");
        plantListModel.addElement("Lettuce");
        plantListModel.addElement("Strawberries");
        plantListModel.addElement("raspberries");
        plantListModel.addElement("Onion");


        //Height and width label start at 0 sq ft
        showGardenHeight.setText("0 Sq Ft");
        showGardenWidth.setText("0 Sq Ft");

        //height and width sliders only go from 1-10
        gardenWidthSlider.setMinimum(1);
        gardenWidthSlider.setMaximum(10);
        gardenHeightSlider.setMinimum(1);
        gardenHeightSlider.setMaximum(10);
    }

    //this method created an resizable table based off of user input from Jsliders
    public void createGardenPlot(){
        int numRows = gardenHeightSlider.getValue();
        int numCols = gardenWidthSlider.getValue();
        DefaultTableModel gardenPlotModel = new DefaultTableModel(numRows, numCols);
        gardenPlot.setModel(gardenPlotModel);
    }


    //this method creates an image from a Jtable
    public static BufferedImage createImageOfGardenPlot(JTable gardenPlot) {
            JTableHeader tableHeader = gardenPlot.getTableHeader();
            int totalWidth = tableHeader.getWidth() + gardenPlot.getWidth(); //gets width of table assigns it to int variable
            int totalHeight = tableHeader.getHeight() + gardenPlot.getHeight(); //gets height of table assigns it to int variable
            BufferedImage gardenPlotImage = new BufferedImage(totalWidth, totalHeight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g2D = (Graphics2D) gardenPlotImage.getGraphics();
            tableHeader.paint(g2D);
            g2D.translate(0, tableHeader.getHeight());
            gardenPlot.paint(g2D);
            return gardenPlotImage;
        }

        //writes image of jtable to a file
        public File writeGardenImageToFile(BufferedImage gardenPlotImage) throws IOException {
            String fileName = gardenPlotName.getText();
            File file = new File(fileName + ".BMP");
            ImageIO.write(gardenPlotImage, "bmp", file);
            return file;
        }
}
