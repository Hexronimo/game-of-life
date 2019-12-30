package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Field field = new Field(scanner.nextInt(), scanner.nextInt());
        int generations = scanner.nextInt();
        scanner.close();
        for (int i = 0; i < generations; i++){
            field.evolve();
        }
        field.draw();

    }

}
