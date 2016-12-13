import java.io.*;
import java.util.*;

public class Main {

public static void main(String[] args) {
  Scanner s = new Scanner(System.in);
  int cents = 0;
  while(s.hasNextLine()) {
      String line = s.nextLine();
      String[] pieces = line.split("[.]");
      cents += Integer.parseInt(pieces[0]) * 100;
      cents += Integer.parseInt(pieces[1]);
  }
  if(cents % 12 >= 6) cents += 12;
  cents /= 12;
  System.out.printf("$%d.%02d", cents / 100, cents % 100);
}

}
