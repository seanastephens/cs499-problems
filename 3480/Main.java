import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String lines = in.nextLine();
        int times = Integer.parseInt(lines);
        while(times --> 0){
            in.nextLine();
            System.out.println(grundyMagic(parseLine(in.nextLine())) ? "John" : "Brother");
        }
        
    }

    public static int[] parseLine(String x){
        String[] a = x.split(" ");
        int[] temp = new int[a.length];
        for (int i=0;i<a.length;i++){
            temp[i] = Integer.parseInt(a[i]);
        }
        return temp;
    }

    public static boolean grundyMagic(int[] nums){
        int running = 0;
        for(int i=0;i<nums.length;i++){
            running ^= nums[i];
        }
        running--;
        return (running != 0);
    }
}
