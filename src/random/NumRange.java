package random;

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
		int currentNumber = 0;
		while(currentNumber < this.numberRequested) 
		{
			int generatedInt = rand.nextInt(numberRange);
			if(!checkDuplicateNumber(generatedInt))
			{
				generatedList[currentNumber] = new Number(generatedInt);
				currentNumber++;
			}
		}
	}
	
	
}
