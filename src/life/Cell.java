package life;

import javax.swing.*;
import java.awt.*;

public abstract class Cell extends JPanel {

    Color color = Color.BLACK;

    public void changeColor(Color color){
        this.color = color;
        paintComponent(this.getGraphics());
    }
}
