package genatic.knapsack.algorithm;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class individual {
	public byte genes[];
        ArrayList<Pair> items ;
	public double fitness_Value;
	public double selectionProb;
	public double cumulativeProb;
	public int sackWeight ;
	public static int items_length ; 
	
        public individual( ArrayList<Pair> _items, int _sackWeight ){
            sackWeight=_sackWeight;
            items = _items;
            items_length = _items.size() ;
            genes = new byte[items_length];
            for(int i = 0 ; i < items.size() ; i++)
            {
                byte gene ;
                Random randomGenerator = new Random();
                int rndNum = randomGenerator.nextInt(); 
                //rndNum*=100 ;
                if (rndNum %2 == 0)gene =1 ;
                else gene = 0 ;
                genes[i]= gene ;
            }
		
	}
        public individual(boolean flag,ArrayList<Pair> _items, int _sackWeight){
            sackWeight=_sackWeight;
            items = _items;
		genes = new byte[items_length];
	}
        void flipbit()
        {
            for (int i =0 ; i < genes.length ; i++)
            {
                if(genes[i]==1){genes[i] = 0 ; break;}
            }
        }
	public double FitnessFun(){
            int res =0,weights=0 ; 
	do{   
            weights =0 ;
            res=0;
            for(int i =0 ; i < genes.length ; i++)
                {
                    if (genes[i] == 1){
                        res+=items.get(i).benefit ;
                        weights+=items.get(i).weight;
                    }
                }
            if (weights > sackWeight)
            {
                flipbit();
            }
            }while(weights > sackWeight);
            //if (weights > sackWeight){ return 0 ;}
                fitness_Value = res ;
                return res;
	}
        String decode()
        {
            String res = "";
            for(int i =0 ; i < genes.length; i++)
            {
                //System.out.println(genes[i]+" "+items.get(i));
                if (genes[i] == 1){
                    res+=items.get(i).toString();
                    res+="\n";
                    
                }
            }
            return res ;
        }
	
	
}
