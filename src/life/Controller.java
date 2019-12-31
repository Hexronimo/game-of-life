package life;

import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.util.Random;

public class Controller {
    private SwingField model;
    private Thread thread;

    public Controller(SwingField model){
        this.model = model;
    }

    public void requestDraw(){
        model.drawField();
    }

    public void startGeneration(){
        thread = new Thread(model);
        thread.start();
    }

    public void stopGeneration(){
        model.interrupt();
    }

    public void setSpeed(int speed){
        model.setSpeed(speed);
    }

    public void requestColorChange(EnumColors color){
        EnumColors.setColorFunction(color, model);
    }

    public void setColor(Color c) {
        model.setCellColor(c);
    }
    public void setNewField(String v1, String v2){
        stopGeneration();
        v1 = v1.trim();
        v2 = v2.trim();
        Integer width = null;
        try {
            width = Integer.parseInt(v1);
        } catch(Exception e) {}
        Long seed = null;
        try {
            seed = Long.parseLong(v2);
        } catch(Exception e) {}
        if (width == null || width < 3 || width > 200) width = 3 + (int)(Math.random() * 197);
        if (seed == null) seed = (long)(Math.random() * Long.MAX_VALUE);
        model.setRandomField(width, seed);
        model.buildField();
    }

    public void requestRestart(){
        model.restart();
    }


    public void load(File file) throws FieldReadingException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String aliveSymbol = br.readLine();
            String deadSymbol = br.readLine();
            if (aliveSymbol == null || deadSymbol == null) throw new FieldReadingException();
            if (aliveSymbol.trim().length() > 1 || deadSymbol.trim().length() > 1) throw new FieldReadingException();
            if (aliveSymbol.trim().length() == 0) aliveSymbol = " ";
            if (deadSymbol.trim().length() == 0) deadSymbol = " ";

            if (deadSymbol.equals(aliveSymbol)) throw new FieldReadingException();

            StringBuilder fieldData = new StringBuilder(br.readLine());
            String s = "";

            while ((s = br.readLine()) != null){
                fieldData.append(s);
            }
            String rawData = fieldData.toString();
            rawData = rawData.replace("\n", "");
            if (!rawData.matches("[" + aliveSymbol + "" + deadSymbol + "]{9,}")) throw new FieldReadingException();

            int width = (int) Math.sqrt(rawData.length());
            if (width * width != rawData.length()) throw new FieldReadingException();
            boolean[][] data = new boolean[width][width];

            int pos = 0;
            for (int y = 0; y < width; y++) {
                for (int x = 0; x < width; x++) {
                    if ((rawData.charAt(pos) + "").equals(aliveSymbol)) data[x][y] = true;
                    else data[x][y] = false;
                    pos++;
                }
            }

            model.setPredefinedField(data);
            model.buildField();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
