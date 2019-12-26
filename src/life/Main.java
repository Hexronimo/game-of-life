package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Field field = new Field(scanner.nextInt(), scanner.nextInt());
        int generations = scanner.nextInt();
        scanner.close();
        field.draw();
        System.out.println("------------------------------");
        System.out.println();
        for (int i = 0; i < generations-1; i++){
            field.evolve();
            field.draw();
            System.out.println("------------------------------");
            System.out.println();
        }
        field.draw();
    }

}
