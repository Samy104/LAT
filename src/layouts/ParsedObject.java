package layouts;

/* This object is used in the ParseXML to instantiate GUI objects.
 * 
 * 
 */

public class ParsedObject {
	private String tag = null;
	private int width = 0;
	private int height = 0;
	private int x = 0;
	private int y = 0;
	private int scale = 1;
	private String action = null;
	private String type = null;
	private String[] actionParam = null;
	private String[] values;
	private String title = null;
	private String name = null;
	private String text = null;
	private String ident = null;
	private String backgroundUrl = null;
	private String backgroundColor = null;
	private boolean visible = true;
	private boolean decorated = true;
	
	
	public ParsedObject(String tag)
	{
		this.tag = tag;
	}
	
	public void applyScale()
	{
		this.height = this.height*scale;
		this.width = this.width*scale;
		this.x = this.x*scale;
		this.y = this.y*scale;
	}
	public void setPosition(int x, int y)
	{
		this.x = x*scale;
		this.y = y*scale;
	}
	
	public void setScale(int scale)
	{
		this.scale = scale;
	}
	public void setScale(int oldS, int newS)
	{
		this.scale = oldS/newS;
	}
	public int getScale()
	{
		return this.scale;
	}
	
	// Begin standard accessors/mutators
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	public String getTag()
	{
		return this.tag;
	}
	public void setAction(String action)
	{
		this.action = action;
	}
	public String getAction()
	{
		return this.action;
	}
	public void setActionParam(String[] actionParam)
	{
		this.actionParam = actionParam;
	}
	public String[] getActionParam()
	{
		return this.actionParam;
	}
	public void setValues(String[] split) {
		this.values = split;
	}
	public String[] getValues()
	{
		return this.values;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getType()
	{
		return this.type;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return this.title;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setId(String id)
	{
		this.ident = id;
	}
	public String getId()
	{
		return this.ident;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public String getText()
	{
		return this.text;
	}
	public void setBackgroundUrl(String background)
	{
		this.backgroundUrl = background;
	}
	public String getBackgroundUrl()
	{
		return this.backgroundUrl;
	}
	public void setBackgroundColor(String backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
	public String getBackgroundColor()
	{
		return this.backgroundColor;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getWidth()
	{
		return this.width;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	public int getHeight()
	{
		return this.height;
	}
	public void setPosX(int x)
	{
		this.x = x;
	}
	public int getPosX()
	{
		return this.x;
	}
	public void setPosY(int y)
	{
		this.y = y;
	}
	public int getPosY()
	{
		return this.y;
	}
	public void setVisible(boolean vis)
	{
		this.visible = vis;
	}
	public boolean isVisible()
	{
		return this.visible;
	}
	public void setDecorated(boolean decor)
	{
		this.decorated = decor;
	}
	public boolean isDecorated() 
	{
		return this.decorated;
	}
}
