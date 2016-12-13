mport java.io.*;
import java.util.*;

public class Main {

public static void main(String[] args) {
  Scanner s = new Scanner(System.in);
  while(s.hasNextLine()) {
      String line = s.nextLine();
      if(line.equals("0.00")) break;
      double overhang = Double.parseDouble(line);
      int cards = how_many_cards(overhang);
      System.out.printf("%d card(s)\n", cards);
  }
}

public static int how_many_cards(double overhang) {
    int n = 1;
    double d = 0.5;
    for(; d < overhang; ++n, d += 1.0/(n+1));
    return n;
}

}
