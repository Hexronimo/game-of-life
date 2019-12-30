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
    RAINBOW;



     public static List<EnumColors> getAll(){
        List<EnumColors> list = new ArrayList<>();
        list.add(BLACK);
        list.add(RED);
        list.add(ORANGE);
        list.add(YELLOW);
        list.add(GREEN);
        list.add(LIGHTBLUE);
        list.add(BLUE);
        list.add(VIOLET);
        list.add(RAINBOW);
        return list;
    }
}
