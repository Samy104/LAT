package layouts;

public class Mapping {
	private String str = null;
	private Object obj = null;
	
	Mapping(String s, Object o)
	{
		str = s;
		obj = o;
	}
	
	public String getString()
	{
		return str;
	}
	
	public Object getObject()
	{
		return obj;
	}
}
