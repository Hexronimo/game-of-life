package life;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BorderlessCell extends Cell {
    private int cellSize;
    Rectangle2D rectangle;

    public BorderlessCell(int width) {
        this.cellSize = width;
        rectangle = new Rectangle2D.Double(0, 0, cellSize, cellSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(super.color);
        g2.fillRect(0,0, cellSize, cellSize);
    }
}
