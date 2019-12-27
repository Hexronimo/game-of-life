package life;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Field field = new Field(scanner.nextInt(), scanner.nextInt());
        int generations = 10;
        scanner.close();
        for (int i = 0; i < generations; i++){
            System.out.printf("Generation #%s\n", i);
            field.evolve();
            System.out.printf("Alive: %s\n", field.countAlive());
            field.draw();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            }
            catch (IOException | InterruptedException e) {}
        }

    }

}