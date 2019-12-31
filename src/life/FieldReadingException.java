package life;

public class FieldReadingException extends Exception {

    public FieldReadingException() {
        super("Error while reading file.\n" +
                "Please, choose txt file with following content (just values without < > ):\n" +
                "< symbol of Alive cell, e.g. X > \n" +
                "< symbol of Dead cell (it can be space or anything else), e.g. 0 > \n" +
                "0XX0X0X0XXX00X0X0X0X0X0XXXX0X0X00X <field in one line> \n" +
                "OR \n" +
                "0XX0 <field as matrix>\n" +
                "0X0X\n" +
                "XXXX\n" +
                "0000");
    }
}
