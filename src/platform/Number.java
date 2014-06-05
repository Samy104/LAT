package platform;

public class Number {
	
	private int value;
	
	public Number(int val)
	{
		this.value = val;
	}
	
	public int getOccurance()
	{
		return dao.Max.getOccurence(this.value);
	}
	
	
	
	
	
	public int getValue()
	{
		return this.value;
	}
	public void setValue(int val)
	{
		this.value = val;
	}

}
