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
            for (int j=0;j<size;j++){
                m[i][j] = in.nextInt();
		if((j < size - 1 && in.hasNext("\n")) || (j == size - 1 && in.hasNext("\n[0-9]+\n"))) {
			if(DEBUG) System.out.println("special case");
			in.next("\n");
			String temp = in.next("[0-9]+");
			m[i][j] *= Math.pow(10, temp.length());
			m[i][j] += Integer.parseInt(temp);
		}
            }
        }
        return m;
    }

    public static int lengthOfWire(int[][] m, int size){
        //Spanning tree on m
        List<Integer> S = new ArrayList<Integer>(size);
        int[] D = new int[size];
        //Setting up with the 0 index as the initial
        for (int i=0;i<size;i++){
            D[i] = m[0][i];
        }
        S.add(0);
        while(S.size() < size){
            if(DEBUG) System.out.println("--------------------------");
            if(DEBUG) System.out.println("D: "+Arrays.toString(D));
            if(DEBUG) System.out.println("S: "+S.toString());
            int smallestNew = smallestIndex(D, S);
            if(DEBUG) System.out.println("Adding: "+smallestNew);
            int[] lineOfInterest = m[smallestNew];
            if(DEBUG) System.out.println("Working with :\n"+ Arrays.toString(lineOfInterest));
            S.add(smallestNew);
            for (int i=0;i<size;i++){
                // Update the cost based on new node
                if( !S.contains(i) && lineOfInterest[i] < D[i]) {
                    if(DEBUG) System.out.println("  ->Updating: "+i);
                    D[i] = lineOfInterest[i];
                }
            }
        }
        int minSpanDist = 0;
        if(DEBUG) System.out.println("Min Distance Calculation");
        for(int i=0;i<D.length; i++){
            if(DEBUG) System.out.println(" ->Adding: "+D[i]);
            minSpanDist += D[i];
        }
        return minSpanDist;

    }

    public static int smallestIndex(int[] lineOfInterest, List<Integer> IgnoreSet){
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
