package genatic.knapsack.algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class GenaticKnapsackAlgorithm {

    public static void main(String[] args) {
                
                
                Scanner scn = new Scanner(System.in);
                int t= scn.nextInt();
                int n = scn.nextInt();
                int knapsackWeight = scn.nextInt();
                ArrayList<Pair> items = new ArrayList<Pair>();
                for(int i =0 ; i < n ; i++)
                {
                    Pair p =new Pair(scn.nextInt(),scn.nextInt());
                    items.add(p);
                }
                System.out.println(items.toString());
                System.out.println("------------------done------------");
                int  T = 10 ;
                
                Run_genetics runner = new Run_genetics();
		individual solution = runner.Run(100, 100, 0.85, 0.01,items,knapsackWeight);
		byte [] genes = solution.genes;
                for(int i =0 ; i < genes.length; i++)
                System.out.print(genes[i]);
                System.out.println("");
               System.out.println(solution.decode());
              
                
               System.out.println("Program End");
    }
}
