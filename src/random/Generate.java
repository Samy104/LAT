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
		for(int i = 0; i < this.numberRequested; i++)
		{
			if(number == this.generatedList[i].getValue())
			{
				return true;
			}
		}
		return false;
	}
	
	private void sortList()
	{
		Arrays.sort(this.generatedList);
	}
}
