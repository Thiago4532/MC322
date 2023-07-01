package lab05;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// Facilitar a leitura e evitar o uso de múltiplos scanners
public class ReadUtil {
    // É seguro usar um scanner estático, pois o código não exige sincronização.
    private final static Scanner scanner = new Scanner(System.in);
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Scanner getScanner() { return scanner; }

    public static String readString(String msg) {
        System.out.print(msg);
        String str;
        for(;;) {
            str = scanner.nextLine();
            if (!str.isEmpty())
                break;
        }
        return str;
    }
    public static String readString() {
        return readString("");
    }

    public static int readInt(String msg) {
        System.out.print(msg);
        return scanner.nextInt();
    }
    public static int readInt() {
        return scanner.nextInt();
    }

    public static LocalDate readLocalDate(String msg) {
        System.out.print(msg);
        try {
            return LocalDate.parse(scanner.nextLine(), FORMATTER);
        } catch (Exception e) {
            return null;
        }
    }
    public static LocalDate readLocalDate() { return readLocalDate(""); }
}
