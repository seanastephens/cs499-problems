import java.util.*;


public class Main{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String line = s.next();
        int x = Integer.parseInt(line);
        System.out.println(checkNumber(x));
    }

    public static int checkNumber(int x){
        int count = 1;
        for (int i=2;i<2*(Math.pow(x, .5)) ; i++){
            //System.out.println("Checking i: "+i);
            if (i%2 == 0){
                if (x%i ==(i/2) && i*(i+1)/2 <= x){
                    count += 1;
                    //System.out.println("Added to count");
                }
            }
            else if (x%i == 0 && i*(i+1)/2 <= x){
                count += 1;
                //System.out.println("Added to count");
            }
        }
        return count;
    }
}
