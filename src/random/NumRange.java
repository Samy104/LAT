package random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import platform.Number;

public class NumRange extends Generate{
	
	
	public NumRange(int maxArray, int lastNumber) {
		super(maxArray, lastNumber);
	}

	public void javaRandomList() 
	{
		Random rand = new Random();
		
		// Prepare sequencial arraylist to have a linear generation since we won't allow duplicates
		ArrayList<Integer> collection = new ArrayList<Integer>();
		for(int index = 1; index <= this.numberRange; index++)
		{
			collection.add(index);
		}
		
		
		for(int currentNumber = 0;currentNumber < this.numberRequested; currentNumber++) 
		{
			int generatedInt = rand.nextInt(collection.size());
			this.generatedList[currentNumber] = new Number(collection.get(generatedInt));
			collection.remove(generatedInt);
		}
		this.sortList();
	}
	
	
}
