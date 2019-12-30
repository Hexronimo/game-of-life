package life;

import life.Cell;
import life.Field;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SwingField extends JPanel implements Runnable {
    private Field field;
    private int fieldSize;

    private JLabel generations;
    private JLabel alive;
    private JPanel panelField;
    private int fieldPanelWidth;

    private List<Cell> cells;

    private boolean interrupted = false;
    int speed = 200;

    public SwingField(int fieldPanelWidth){
        this.fieldPanelWidth = fieldPanelWidth;
        setLayout(null);

        generations = new JLabel();
        generations.setName("GenerationLabel");

        alive = new JLabel();
        alive.setName("AliveLabel");
        generations.setBounds(190, 3, 200,20);
        alive.setBounds(190,20,200,20);
        add(generations);
        add(alive);

        panelField = new JPanel();
        panelField.setBounds(190,40, fieldPanelWidth, fieldPanelWidth);
        add(panelField);
    }

    public void setRandomField(Integer fieldSize, Long seed){
        if (field != null && fieldSize == null) {
            fieldSize = this.fieldSize;
        }
        if (field != null && seed == null) {
            seed = 1l;
        }
        field = new Field(fieldSize,seed);
        this.fieldSize = fieldSize;
    }

    public void setPredefinedField(Integer fieldSize, boolean[][] data) {

    }

    public void buildField() {

        if (field == null) {
            setRandomField(20, 2653l);
        }
        generations.setText("Generation #1");
        alive.setText("Alive: " + field.countAlive());
        panelField.removeAll();
        int cellWidth = fieldPanelWidth/fieldSize;
        cells = new ArrayList<>(fieldSize*fieldSize);
        for (int i = 0; i < fieldSize*fieldSize; i++){
            Cell c;
            if (cellWidth > 2) c = new BorderedCell(cellWidth);
            else c = new BorderlessCell(cellWidth);
            panelField.add(c);
            cells.add(c);
        }
        panelField.setLayout(new GridLayout(fieldSize,fieldSize,0,0));
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void drawField(){
        int count = 0;
        for (int y = 0; y < field.getWidth(); y++){
            for (int x = 0; x < field.getWidth(); x++){
                if (field.getCurrent().getStateOfXY(x,y)) {
                    cells.get(count).changeColor(Color.BLACK);
                } else {
                    cells.get(count).changeColor(this.getBackground());
                }
                count++;
            }
        }
    }

    @Override
    public void run(){
        interrupted = false;
        while(!interrupted) {
            field.evolve();
            setLabels();
            drawField();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {}
        }
    }

    public void interrupt() {
        interrupted = true;
    }

    private void setLabels(){
        generations.setText("Generation #" + field.getGeneration());
        alive.setText("Alive: " + field.countAlive());
    }
}
