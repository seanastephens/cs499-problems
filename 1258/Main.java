import java.util.ArrayList;
import java.util.*;

public class Main{
    private static boolean DEBUG = true;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in); 
        int size = Integer.parseInt(in.nextLine());
        int[][] m = parseInput(in, size);
        System.out.println(lengthOfWire(m, size));
    }

    public static int[][] parseInput(Scanner in, int size){
        int[][] m = new int[size][size];

        for(int i=0;i<size;i++){
            int numberInLine = 0;
            for (int j=0;j<size;j++){
                m[i][j] = in.nextInt();
            }
        }
        return m;
    }

    public static int[] parseLine(String x){
        String[] a = x.split(" ");
        int[] temp = new int[a.length];
        for (int i=0;i<a.length;i++){
            temp[i] = Integer.parseInt(a[i]);
        }
        return temp;
    }

    public static int lengthOfWire(int[][] m, int size){
        //Spanning tree on m
        ArrayList S = new ArrayList(size);
        int[] D = new int[size];
        //Setting up with the 0 index as the initial
        for (int i=0;i<size;i++){
            if(m[0][i]< m[i][0]){
                D[i] = m[0][i];
            } else {
                D[i] = m[i][0];
            }
        }
        S.add(0);
        int minSpanDist = 0;
        while(S.size()< size){
            if(DEBUG) System.out.println("--------------------------");
            if(DEBUG) System.out.println("D: "+Arrays.toString(D));
            if(DEBUG) System.out.println("S: "+S.toString());
            int smallestNew = smallestIndex(D, S);
            if(DEBUG) System.out.println("Adding: "+smallestNew);
            int[] lineOfInterest = m[smallestNew];
            int[] secondLineOfInterest = new int[size];
            for (int i=0;i<size;i++){
                secondLineOfInterest[i] = m[i][smallestNew];
            }
            if(DEBUG) System.out.println("Working with :\n"+ Arrays.toString(lineOfInterest));
            S.add(smallestNew);
            for (int i=0;i<size;i++){
                // Update the cost based on new node
                if( !S.contains(i) && lineOfInterest[i] < D[i]) {
                    if(DEBUG) System.out.println("  ->Updating: "+i);
                    D[i] = lineOfInterest[i];
                }
                if (!S.contains(i) && secondLineOfInterest[i] < D[i]) {
                    if(DEBUG) System.out.println("  ->Updating with column: "+i);
                    D[i] = secondLineOfInterest[i];
                }
            }
        }
        if(DEBUG) System.out.println("Min Distance Calculation");
        for(int i=0;i<D.length; i++){
            if(DEBUG) System.out.println(" ->Adding: "+D[i]);
            minSpanDist += D[i];
        }
        return minSpanDist;

    }

    public static int smallestIndex(int[] lineOfInterest, ArrayList IgnoreSet){
        if(DEBUG) System.out.println("Calculating the smallest index");
        if(DEBUG) System.out.println(Arrays.toString(lineOfInterest));
        int smallestIndex = -1;
        int smallest = Integer.MAX_VALUE;
        for(int i=0;i<lineOfInterest.length;i++){
            if(!IgnoreSet.contains(i) && lineOfInterest[i]<smallest){
                if(DEBUG) System.out.println("  Ignore set didn't have i, and it was smaller, i: "+i);
                smallest = lineOfInterest[i];
                smallestIndex = i;
            }
        }
        if(DEBUG) System.out.println(" Returning the smallest distance: "+smallestIndex);
        return smallestIndex;
    }
}
