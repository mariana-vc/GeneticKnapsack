package genatic.knapsack.algorithm;

import java.util.ArrayList;
import java.util.Random;


 public class Run_genetics {
	 Random rand;
	 ArrayList<individual> population;
         ArrayList<Pair> items ;
	double crossOver_Probability;
	double mutation_Probability;
	int knapsackWeight ;
	int[] select_index=new int [2];
	int[]  selection_result=new int[]{0,0};
 individual Run(int iterationNum, int popSize, double _crossOver_Probability, double _mutation_Probability,ArrayList<Pair> _items,int _knapsackWeight ){
		knapsackWeight=_knapsackWeight;
                items = _items; 
                population = new ArrayList<individual>();
		crossOver_Probability = _crossOver_Probability;
		mutation_Probability = _mutation_Probability;
		rand = new Random(System.currentTimeMillis());
		
		for(int i=0;i<popSize;++i)
			population.add(new individual(items,knapsackWeight));
		
		while((iterationNum--)>0){
			double totalFitness = 0.0;
			for(int i=0;i<popSize;++i)
				totalFitness += population.get(i).FitnessFun();
			
			double total_Probability = 0.0;
		
			for(int i=0;i<popSize;++i){
				population.get(i).selectionProb = population.get(i).fitness_Value / totalFitness;
				population.get(i).cumulativeProb = (total_Probability += population.get(i).selectionProb);
			}
			
			selection_result=SelectParents();
			CrossOver(selection_result[0], selection_result[1]);
			Mutate(selection_result[0], selection_result[1]);
		}
		
		int index = 0;
		
		double bestFitness = population.get(0).FitnessFun();
		for(int i=1;i<popSize;++i){
			double tempFitness = population.get(i).FitnessFun();
			if(tempFitness > bestFitness) index = i;
		}
		
		return population.get(index);
	}
	
	int[] SelectParents(){
		
		select_index[0]=0;
		select_index[1]=0;
		
		double random_Probability = rand.nextDouble();
		for(int i=0;i<population.size();i++){
			if(random_Probability <= population.get(i).cumulativeProb){
				select_index[0] = select_index[1] = i;
				break;
			}
		}
		
		while(select_index[0] == select_index[1]){
			random_Probability = rand.nextDouble()*100;
                        //random_Probability = random_Probability %population.get(population.size()-1).cumulativeProb ;
			for(int i=0;i<population.size();i++){
                           
                         //   System.out.println(select_index[0]+" "+select_index[1]+" "+random_Probability+" "+population.get(i).cumulativeProb+1);
				if(random_Probability <= population.get(i).cumulativeProb+1){
					select_index[1] = i;
					break;
				}
			}
                         
		}
		
	
	return select_index;
	}
	
	void CrossOver(int parent_Index1, int parent_Index2){
		if(rand.nextDouble() <= crossOver_Probability) 
		{
		int crossover_index = rand.nextInt(individual.items_length-2);
		individual parent1 = population.get(parent_Index1);
		individual parent2 = population.get(parent_Index2);
		individual offspring1 = new individual(false,items,knapsackWeight);
		individual offspring2 = new individual(false,items,knapsackWeight);
		
		for(int i=0;i<=crossover_index;++i){
			offspring1.genes[i] = parent1.genes[i];
			offspring2.genes[i] = parent2.genes[i];
		}
		
		for(int i=crossover_index+1;i<individual.items_length;++i){
			offspring1.genes[i] = parent2.genes[i];
			offspring2.genes[i] = parent1.genes[i];
		}
		
		population.add(parent_Index1, offspring1);
		population.remove(parent_Index1+1);
		population.add(parent_Index2, offspring2);
		population.remove(parent_Index2+1);
	}
	}
	void Mutate(int index1, int index2){
		individual c1 = population.get(index1);
		individual c2 = population.get(index2);
		
		for(int i=0;i<individual.items_length;++i){
			if(rand.nextDouble() <= mutation_Probability)
				c1.genes[i]=(byte)(c1.genes[i]);
				if (c1.genes[i]==0)
						c1.genes[i]=1;
				else
			     c1.genes[i]=0;
				
				
				if(rand.nextDouble() <= mutation_Probability)
					c2.genes[i]=(byte)(c2.genes[i]);
					if (c2.genes[i]==0)
							c2.genes[i]=1;
					else
						c2.genes[i]=0;
				}
	}
	 
}
