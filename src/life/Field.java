package life;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field {
    private long SEED;
    private int width;
    private char picAlive;
    private char picDead;

    private Data current;
    private Data upcoming;
    private int generation;


    public Field(int width, long seed) {
        this.width = width;
        this.SEED = seed;
        picAlive = 'O';
        picDead = ' ';
        Random random = new Random(SEED);
        current = new Data(width);
        upcoming = new Data(width);
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                current.setStateOfXY(x, y, random.nextBoolean());
            }
        }
        generation = 1;
    }



    public Data getCurrent(){
        return current;
    }

    public int getGeneration(){
        return generation;
    }

    public void evolve() {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                int count = countNeighbours(x, y);
                upcoming.setStateOfXY(x, y, current.getStateOfXY(x, y));
                if (current.getStateOfXY(x, y)) { //if Alive
                    if (count > 3 || count < 2) upcoming.setStateOfXY(x, y, false);
                } else { //if Dead
                    if (count == 3) upcoming.setStateOfXY(x, y, true);
                }
            }
        }
        current = upcoming.clone();
        upcoming = new Data(width);
        generation++;
    }

    public int getWidth() {
        return width;
    }

    public int countAlive() {
        int count = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                count = current.getStateOfXY(j, i) ? count + 1 : count + 0;
            }
        }
        return count;
    }

    private int countNeighbours(int x, int y) {
        int count = 0;
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (j == x && i == y) continue;
                int thisX = j;
                int thisY = i;
                if (thisX < 0) thisX = width - 1;
                if (thisX >= width) thisX = 0;
                if (thisY < 0) thisY = width - 1;
                if (thisY >= width) thisY = 0;
                if (current.getStateOfXY(thisX, thisY)) count++;
            }
        }
        return count;
    }

    public void draw() {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(current.getStateOfXY(x, y) ? picAlive : picDead);
            }
            System.out.println();
        }
    }

 }