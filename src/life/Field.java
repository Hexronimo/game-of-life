package life;

import java.util.Random;

public class Field {
    private final long SEED;
    private int width;
    private char picAlive;
    private char picDead;

    private Data current;
    private Data upcoming;


    public Field(int width, long seed){
        this.width = width;
        this.SEED = seed;
        picAlive = 'O';
        picDead = ' ';
        generate();
    }

    public void generate() {
        Random random = new Random(SEED);
        current = new Data(width);
        upcoming = new Data(width);
        for (int y = 0; y < width; y++){
            for (int x = 0; x < width; x++) {
                current.setStateOfXY(x,y,random.nextBoolean());
            }
        }
    }

    public void evolve(){
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                if (current.getStateOfXY(x,y)) { //if Alive
                    if (countNeighbours(x, y) > 3  || countNeighbours(x, y) < 2) upcoming.setStateOfXY(x,y,false);
                    else upcoming.setStateOfXY(x,y,true);
                } else { //if Dead
                    if (countNeighbours(x, y) == 3) upcoming.setStateOfXY(x,y,true);
                    else upcoming.setStateOfXY(x,y,false);
                }
            }
        }
        current = upcoming.clone();
        upcoming = new Data(width);
    }

    private int countNeighbours(int x, int y){
        int count = 0;
        for(int i = y - 1; i <= y + 1; i++){
            for(int j = x - 1; j <= x + 1; j++){
                if (j == x && i == y) continue;
                int thisX = j;
                int thisY = i;
                if (thisX < 0) thisX = width - 1;
                else if (thisX >= width) thisX = 0;
                if (thisY < 0) thisY = width - 1;
                else if (thisY >= width) thisY = 0;
                if (current.getStateOfXY(thisX, thisY)) count++;
            }
        }
        return count;
    }

    public void draw(){
        for (int y = 0; y < width; y++){
            for (int x = 0; x < width; x++) {
                System.out.print(current.getStateOfXY(x,y)?picAlive : picDead);
            }
            System.out.println();
        }
    }
}
