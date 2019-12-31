package life;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public enum EnumColors {
    BLACK,
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    LIGHTBLUE,
    BLUE,
    VIOLET,
    BW_PULSE;

    private static boolean interrupted;

    public static void setColorFunction(EnumColors color, SwingField field){
        interrupted = true;
        switch (color){
            case RED: field.setCellColor(new Color(235, 0, 53, 158)); return;
            case ORANGE: field.setCellColor(new Color(235, 79, 0, 158)); return;
            case YELLOW: field.setCellColor(new Color(235, 212, 0, 158)); return;
            case GREEN: field.setCellColor(new Color(0, 235, 15, 158)); return;
            case BLUE: field.setCellColor(new Color(0, 42, 235, 158)); return;
            case LIGHTBLUE: field.setCellColor(new Color(0, 203, 235, 158)); return;
            case VIOLET: field.setCellColor(new Color(64, 0, 235, 158)); return;

            case BW_PULSE: {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        interrupted = false;
                        int r = 100, g = 100, b = 100;
                        int dif = -10;
                        while(!interrupted){
                            if (r <= 0) {
                                dif = 10;
                                r = 0; g = 0; b = 0;
                            }
                            if (r >= 220) {
                                dif = -10;
                                r = 220; g = 220; b = 220;
                            }
                            field.setCellColor(new Color(r=r+dif,g=g+dif,b=b+dif));
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            field.drawField();
                        }
                    }
                });
                thread.start();
                return;
            }
            default: field.setCellColor(Color.BLACK);
        }
    }

     public static List<EnumColors> getAll(){
        List<EnumColors> list = new ArrayList<>();
        list.add(BLACK);
        list.add(BW_PULSE);
        list.add(RED);
        list.add(ORANGE);
        list.add(YELLOW);
        list.add(GREEN);
        list.add(LIGHTBLUE);
        list.add(BLUE);
        list.add(VIOLET);
        return list;
    }

}
