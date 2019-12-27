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
            Data d = new Data(cells[0].length);
            for(int i = 0; i < cells[0].length; i++){
                for(int j = 0; j < cells[0].length; j++) {
                    d.setStateOfXY(j,i,cells[j][i]);
                }
            }
            return d;
    }
}
