package platform;

import java.util.Arrays;

import random.Generate;
import random.NumRange;

public class Chain {
	
	private Number[] list;
	private int maximumNumber;
	private int amountNumbers;
	
	public Chain(int max, int amount)
	{
		this.maximumNumber = max;
		this.amountNumbers = amount;
	}
	
	public Chain GenerateChain(String method)
	{
		if(method.toLowerCase().equals("random"))
		{
			NumRange nr = new NumRange(this.maximumNumber, this.amountNumbers);
			nr.javaRandomList();
			list = nr.getGeneratedList();
		}
		
		return this;
	}
	
	public void confirmChain()
	{
		// Once confirmed the chain will be inserted to the database
	}
	
	public Number[] getList()
	{
		return this.list;
	}
	
	public String getListString()
	{
		if(list == null)
		{
			System.out.println("The list in the chain is empty");
		}
		
		StringBuilder outputString = new StringBuilder(this.amountNumbers);
		//outputString.append("Here is your generated list \n");
		for(int i = 0; i < this.list.length;i++)
		{
			System.out.println(this.list[i].getValue());
			outputString.append(this.list[i].getValue());
			outputString.append(" ");
		}
		return outputString.toString();
	}
}
