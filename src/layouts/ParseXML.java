package layouts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.omg.CORBA.Current;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* Class created by Samy Lemcelli
 * This class is called statically to parse an XML document and retrieves an object in return.
 * The user must know what the desired object is for the method called
 * 
 */

public class ParseXML {

	public ParseXML()
	{
		
	}
	/* This method will parse the XML and detect what is contained within the tags and created the corresponding objects within.
	 * It will automate the creation of panels from a XML files so that View files manage interactions
	 * 
	 * XML attributes: reference to the first switch.
	 * Types:"content,label,txtfield,passfield,button"
	 * 
	 * 
	 */
	public static JFrame ParseXMLToFrame(String xml)
	{
		JFrame custom = new JFrame();
		JPanel currentPan = null;
		JMenuBar currMenuBar = null;
		ArrayList<Object> hierarchy = new ArrayList<Object>();
		ArrayList<Mapping> ObjectText = new ArrayList<Mapping>();
		
		try {
	         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	         XmlPullParser parser = factory.newPullParser();
	         
	         if(! new File(xml).isFile())
	         {
	        	 System.out.println("The path " + xml + " does not point to a file.");
	         }
	         InputStream is = new FileInputStream(xml);
	         parser.setInput(is, null);
	         
	         int eventType = parser.getEventType();
	         
	         String tag;
	         
		 while (eventType != XmlPullParser.END_DOCUMENT) 
		 {
			ParsedObject parsed = new ParsedObject(parser.getName());
			int scale=1;
			
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
						// Here lies the list of attributes that are taken into account. If they are not set they have default values.
						switch(parser.getAttributeName(i))
						{
							case "width":
								parsed.setWidth(Integer.parseInt(parser.getAttributeValue(i)));
								break;
							case "height":
								parsed.setHeight(Integer.parseInt(parser.getAttributeValue(i)));
								break;
							case "x":
								parsed.setPosX(Integer.parseInt(parser.getAttributeValue(i)));
								break;
							case "y":
								parsed.setPosY(Integer.parseInt(parser.getAttributeValue(i)));
								break;
							case "action":
								parsed.setAction(parser.getAttributeValue(i));
								break;
							case "actionParam":
								parsed.setActionParam(parser.getAttributeValue(i).split(", "));
								break;
							case "actionParams":
								parsed.setActionParam(parser.getAttributeValue(i).split(", "));
								break;
							case "name":
								parsed.setName(parser.getAttributeValue(i));
								break;
							case "text":
								parsed.setText(parser.getAttributeValue(i));
								break;
							case "type":
								parsed.setType(parser.getAttributeValue(i));
								break;
							case "title":
								parsed.setTitle(parser.getAttributeValue(i));
								break;
							case "values":
								parsed.setValues(parser.getAttributeValue(i).split(", "));
								break;
							case "id":
								parsed.setId(parser.getAttributeValue(i));
								break;
							case "backgroundUrl":
								parsed.setBackgroundUrl(parser.getAttributeValue(i));
								break;
							case "backgroundColor":
								parsed.setBackgroundColor(parser.getAttributeValue(i));
								break;
							case "visible":
								parsed.setVisible(parser.getAttributeValue(i).equals("true"));
								break;
							case "decorated":
								parsed.setDecorated(parser.getAttributeValue(i).equals("true"));
								break;
							default:
								System.out.println("The attribute "+parser.getAttributeName(i)+" wasn't valid");
								break;
						}
					}
					
					// Call the scale function
					parsed.applyScale();
					
					
					// On peut alors ajouter les attributs et elements
					//System.out.println(type);
					//System.out.println(parser.getName());
					if (parsed.getTag().toLowerCase().equals("frame"))
					{
						parsed.setScale((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), parsed.getWidth());
						scale = parsed.getScale();
						custom.setLayout(null);
						custom.setBounds(new Rectangle(parsed.getWidth(),parsed.getHeight()));
						custom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						custom.setTitle(parsed.getTitle());
						if(!parsed.isDecorated())
						{
							custom.setUndecorated(true);
						}
						
						hierarchy.add(custom);
						/*System.out.println(parsed.getBackgroundUrl());
						if(parsed.getBackgroundUrl() != null && new File(parsed.getBackgroundUrl()).isFile() )
						{
							BackgroundPanel bgp = new BackgroundPanel(ImageIO.read(new File(parsed.getBackgroundUrl())), BackgroundPanel.SCALED);
							custom.add(bgp);
							hierarchy.add(bgp);
						}
						else if(parsed.getBackgroundUrl() != null)
						{
							System.out.println("The path " + parsed.getBackgroundUrl() + " does not point to a file.");
						}*/
						ObjectText.add(new Mapping("frame",custom));
		       		}
					else if(parsed.getType() == null)
					{
						System.out.println("The type you try to push is not valid for : " + parsed.getTag());
					}
					else
					{
						Component current = (Component) GUIBuilder.BuildGUIElement(parsed, hierarchy, ObjectText);
						current.setVisible(parsed.isVisible());
						
						if(hierarchy.size() == 1)
						{
							custom.add(current);
						}
						else
						{
							((Container)hierarchy.get(hierarchy.size()-2)).add(current);
						}
					}
				}   	
			 }
			 else if (eventType == XmlPullParser.TEXT && (hierarchy.size()) > 0) 
			 {
				 // Implement text later to, if wanted, include the Text as value to the last hierarchic element.(optional)         
			 }
			 else if (eventType == XmlPullParser.END_TAG) 
			 { 
				 if(hierarchy.size() != 0)
				 {
					 hierarchy.remove(hierarchy.size()-1);
					 
				 }		 
			 }
			 
			 eventType = parser.next();            
		 }
		}
		catch (XmlPullParserException e) {
			System.out.println(e);   
		}
		catch (IOException e) {
			System.out.println("IOException while parsing " + xml); 
		} 
		catch (SecurityException e) {
			// TODO Auto-generated catch block
			System.out.println("Security was compromised.");
			e.printStackTrace();
		}
		custom.revalidate();
		custom.repaint();
		custom.setVisible(true);
		
		return custom;
	}
	
	
	public static Object getValueFromArray(ArrayList<Mapping> a, String toSearch)
	{
		Object researchedString = null;
		for(int i = 0; i < a.size(); i++)
		{
			//System.out.println("Does " + a.get(i).getString() + " Equal " +toSearch);
			if(a.get(i).getString().equals(toSearch))
			{
				researchedString = a.get(i).getObject();
			}
		}
		
		return researchedString;
	}
	
}
