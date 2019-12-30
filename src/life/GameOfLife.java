package life;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GameOfLife extends JFrame {

    private int fieldPanelWidth;

    public GameOfLife(int fieldPanelWidth, Controller controller){
        super("Game of Life");
        this.fieldPanelWidth = fieldPanelWidth;
        setSize(fieldPanelWidth + 160 + 30, fieldPanelWidth + 70);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel leftMenu = new JPanel(new GridLayout(16,1, 0,3));
        leftMenu.setBounds(15,5, 160, fieldPanelWidth + 65 );

        JPanel controlButtons = new JPanel();
        controlButtons.setLayout(new GridLayout(1,2, 5, 0));

        JButton bPlayPause = new JButton("▶");
        bPlayPause.setName("PlayToggleButton");
        bPlayPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JButton)(e.getSource())).getText().equals("▮▮")) {
                    ((JButton) (e.getSource())).setText("▶");
                    controller.stopGeneration();
                } else {
                    ((JButton) (e.getSource())).setText("▮▮");
                    controller.startGeneration();
                }
            }
        });

        controlButtons.add(bPlayPause);
        leftMenu.add(controlButtons);

        leftMenu.add(new JLabel()); //separator

        JLabel lFieldSize = new JLabel("New field size");
        leftMenu.add(lFieldSize);
        JTextField iFieldSize = new JTextField();
        leftMenu.add(iFieldSize);
        JLabel lSeed = new JLabel("New seed");
        leftMenu.add(lSeed);
        JTextField iFieldSeed = new JTextField();
        leftMenu.add(iFieldSeed);


        JButton bRestart = new JButton("Recreate");
        bRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String v1 = iFieldSize.getText();
                String v2 = iFieldSeed.getText();
                controller.setNewField(v1, v2);
                bPlayPause.setText("▶");
                controller.requestDraw();
            }
        });
        leftMenu.add(bRestart);
        JPanel saveLoadButtons = new JPanel();
        saveLoadButtons.setLayout(new GridLayout(1,2, 3, 0));
        JButton bSave = new JButton("Save");
        JButton bLoad = new JButton("Load");

        saveLoadButtons.add(bSave);
        saveLoadButtons.add(bLoad);
        leftMenu.add(saveLoadButtons);

        leftMenu.add(new JLabel()); //separator

        leftMenu.add(new JLabel("Speed"));
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 100, 2000, 100);
        slider.setValue(100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.setSpeed(((JSlider)e.getSource()).getValue());
            }
        });
        leftMenu.add(slider);
        leftMenu.add(new JLabel("Cells color"));
        final DefaultComboBoxModel colorName = new DefaultComboBoxModel();
        colorName.addAll(EnumColors.getAll());
        JComboBox colorList = new JComboBox(colorName);
        colorList.setSelectedIndex(0);
        colorList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        leftMenu.add(colorList);
        leftMenu.add(new JLabel()); //separator

        add(leftMenu);
    }

    public void setFieldPanel(JPanel panel){
        add(panel);
        setVisible(true);
    }


    public static void main(String[] args) {
        int size = 100; // default width of field (how much cells in a row)
        int fieldPanelWidth = 400; // default Width of game field panel
        long seed = 10;
        int speed = 100;

        SwingField swingField = new SwingField(fieldPanelWidth);
        swingField.setSpeed(speed);
        swingField.setRandomField(size,seed);
        swingField.buildField();
        Controller controller = new Controller(swingField);
        GameOfLife gameOfLife = new GameOfLife(fieldPanelWidth, controller);
        gameOfLife.setFieldPanel(swingField);
        controller.requestDraw();
    }

}
