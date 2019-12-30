package life;

import java.text.ParseException;

public class Controller {
    private SwingField model;
    private boolean isPlaying = false;
    private Thread thread;

    public Controller(SwingField model){
        this.model = model;
    }
    public void requestDraw(){
        model.drawField();
    }
    public void startGeneration(){
        thread = new Thread(this.model);
        thread.start();
    }
    public void stopGeneration(){
        model.interrupt();
    }
    public void setSpeed(int speed){
        model.setSpeed(speed);
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
        if (width != null && (width < 3 || width > 200)) width = 100;
        model.setRandomField(width, seed);
        model.buildField();
    }
}
