import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Hi,\nplease input the path to the data file:");
        String filePath = in.nextLine();
        System.out.println("\t*****");

        CountingTime.counting(filePath)
                .forEach(System.out::println);
    }
}
