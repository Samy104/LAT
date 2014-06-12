package random;

import java.util.Arrays;

import platform.Number;

public class Generate {
	protected Number[] generatedList;
	protected int numberRequested = 7;
	protected int numberRange = 49;
	
	public Generate(int maxArray, int lastNumber) 
	{
		generatedList = new Number[maxArray];
		this.numberRequested = maxArray;
		this.numberRange = lastNumber;
	}
	
	public Number[] getGeneratedList()
	{
		return this.generatedList;
	}
	
	public String getGeneratedListString()
	{
		sortList();
		StringBuilder outputString = new StringBuilder(this.numberRequested);
		outputString.append("Here is your generated list \n");
		for(int i = 0; i < this.numberRequested;i++)
		{
			outputString.append(this.generatedList[i] + " \n");
		}
		return outputString.toString();
	}
	
	protected boolean checkDuplicateNumber(int number) 
	{
		if(this.generatedList.length == 0)
		{
			return true;
		}
		for(int i = 0; i < this.generatedList.length  && this.generatedList[i] != null; i++)
		{
			if(number == this.generatedList[i].getValue())
			{
				return true;
			}
		}
		return false;
	}
	
	protected void sortList()
	{
		// Not the most efficient way but good for now. Create a new array with the elements and recreate a new Number list.
		int[] tempList = new int[generatedList.length];
		for(int dem = 0; dem < generatedList.length; dem++)
		{
			tempList[dem] = generatedList[dem].getValue();
		}
		Arrays.sort(tempList);
		for(int mon = 0; mon < generatedList.length; mon++)
		{
			generatedList[mon] = new Number(tempList[mon]);
		}
	}
}
