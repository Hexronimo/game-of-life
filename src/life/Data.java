package life;

public class Data implements Cloneable {
    private boolean[][] cells;

    public Data(int width){
        cells = new boolean[width][width];
    }

    public void setStateOfXY (int x,int y,boolean isAlive) {
        cells[x][y] = isAlive;
    }

    public boolean getStateOfXY(int x, int y) {
        return cells[x][y];
    }

    @Override
    protected Data clone() {
            Data d = null;
            try {
                d = (Data) super.clone();
            } catch (CloneNotSupportedException e) {

            }
            return d;
    }
}
