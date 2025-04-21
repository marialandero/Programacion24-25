package ExpresionesRegulares;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prueba {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("^\\d+:\\p{L}[\\s]*:\\d{9}$");
        Matcher m = p.matcher("1:maria:564345736735");
        if (m.matches()) {
            System.out.printf("Sí, contiene el patrón desde la posición %d a la %d.\n", m.start(), m.end());
        }
        else {
            System.out.println("No contiene el patrón.");
        }
    }
}
