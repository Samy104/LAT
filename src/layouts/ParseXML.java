package layouts;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* Class created by Samy Lemcelli
 * This class is called statically to parse an XML document and retrieves an object in return.
 * The user must know what the desired object is for the method called
 * 
 * 
 * 
 * 
 */

public class ParseXML {

	public ParseXML()
	{
		
	}
	/* This method will parse the XML and detect what is contained within the tags and created the corresponding objects within.
	 * It will automate the creation of panels from a XML files so that View files manage interactions
	 * 
	 * XML attributes:"Type,Height,Width,X,Y,Action,actionparam"
	 * Types:"content,label,txtfield,button,list"
	 * 
	 * 
	 */
	public static JFrame ParseXMLToFrame(String xml)
	{
		JFrame custom = new JFrame();
		
		
		try {
	         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	         XmlPullParser parser = factory.newPullParser();

	         InputStream is = new FileInputStream(xml);
	         parser.setInput(is, null);
	         
	         int eventType = parser.getEventType();
	         
	         int id = -1;
	         String tag;
	         
		 while (eventType != XmlPullParser.END_DOCUMENT) 
		 {
			JPanel currentPan = new JPanel();
			tag = parser.getName();
			int width = 0;
			int height = 0;
			int x = 0;
			int y = 0;
			String action = null;
			String[] actionParam = null;
			String name = "";
			String text = "";
			
			if(eventType == XmlPullParser.START_TAG) 
			{
				if(parser.getAttributeCount() == 0)
				{
					System.out.println("Every tag should have at least one attribute");
				}
				else
				{
					for(int i=0;i<parser.getAttributeCount();i++)
					{
						switch(parser.getAttributeName(i))
						{
							case "width":
								width = Integer.parseInt(parser.getAttributeValue(i));
								break;
							case "height":
								height = Integer.parseInt(parser.getAttributeValue(i));
								break;
							case "x":
								x = Integer.parseInt(parser.getAttributeValue(i));
								break;
							case "y":
								y = Integer.parseInt(parser.getAttributeValue(i));
								break;
							case "action":
								action = parser.getAttributeValue(i);
								break;
							case "actionParam":
								actionParam = parser.getAttributeValue(i).split(", ");
								break;
							case "name":
								name = parser.getAttributeValue(i);
								break;
							case "text":
								text = parser.getAttributeValue(i);
								break;
							default:
									break;
						}
					}
					// On peut alors ajouter les attributs et elements
					if (tag.equals("Frame"))
					{
						custom.setBounds(new Rectangle(width,height));
		       		}
					else if(parser.getAttributeType(0).equals("content"))
					{
						currentPan = new JPanel();
						currentPan.setName(name);
						currentPan.setBounds(x, y, width, height);
					}
					else if(parser.getAttributeType(0).equals("label"))
					{
						JLabel currLabel = new JLabel(text);
						currLabel.setBounds(x, y, width, height);
						currentPan.add(currLabel);
					}
					else if(parser.getAttributeType(0).equals("button"))
					{
						JButton currButton = new JButton(text);
						currButton.setBounds(x, y, width, height);
						if(actionParam.length != 0 && action != null)
						{
							custom.getComponents().toString();
							currButton.addActionListener((ActionListener)Class.forName(action).getDeclaredConstructor(String.class).newInstance(actionParam));
						}
						else if(action != null)
						{
							currButton.addActionListener((ActionListener) Class.forName(action).newInstance());
						}
						
					}
					else if(parser.getAttributeType(0).equals("textfield"))
					{
						JTextField currText = new JTextField();
						currText.setBounds(x, y, width, height);
					}
					
					
					
					
				}
		       	
				   	
			 }
			 else if (eventType == XmlPullParser.TEXT && id >= 0) 
			 {
				 if (tag != null)
				 {                                    
					 if (tag.equals(""))
					 {
						 // = parser.getText();
		             }  
				 }              
			 }
			 else if (eventType == XmlPullParser.END_TAG) 
			 {                              
				 tag = null;
				 if (parser.getName().equals(tag) && id >= 0)
				 {
		        	   
				 }
				 custom.add(currentPan);
				 custom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 }
			 
			 eventType = parser.next();            
		 }
		}
		catch (XmlPullParserException e) {
			System.out.println(e);   
		}
		catch (IOException e) {
			System.out.println("IOException while parsing " + xml); 
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The class was instantiated improperly");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The class was called illegaly");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The class was not found for the dynamic call");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println("The argument was invalid for the dynamic call");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			System.out.println("Error within the invocation call");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			System.out.println("The method was not found.");
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			System.out.println("Security was compromised.");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
