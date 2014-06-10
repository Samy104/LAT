package layouts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import controller.*;

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
	 * XML attributes:"Type,Height,Width,X,Y,Action,actionParam"
	 * Types:"content,label,txtfield,passfield,button,list"
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
	         
	         //System.out.println("Last place before crash");
	         if(! new File(xml).isFile())
	         {
	        	 System.out.println("The path " + xml + " does not point to a file.");
	         }
	         InputStream is = new FileInputStream(xml);
	         parser.setInput(is, null);
	         
	         int eventType = parser.getEventType();
	         
	         int id = -1;
	         String tag;
	         
		 while (eventType != XmlPullParser.END_DOCUMENT) 
		 {
			
			tag = parser.getName();
			int width = 0;
			int height = 0;
			int x = 0;
			int y = 0;
			String action = null;
			String type = null;
			String[] actionParam = null;
			String title = null;
			String name = null;
			String text = null;
			String ident = null;
			
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
							case "type":
								type = parser.getAttributeValue(i);
								break;
							case "title":
								title = parser.getAttributeValue(i);
								break;
							case "id":
								ident = parser.getAttributeValue(i);
								break;
							default:
								System.out.println("The attribute "+parser.getAttributeName(i)+" wasn't valid");
								break;
						}
						
					}
					// On peut alors ajouter les attributs et elements
					//System.out.println(type);
					//System.out.println(parser.getName()+(id+1));
					if (tag.equals("Frame"))
					{
						custom.setBounds(new Rectangle(width,height));
						custom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						custom.setTitle(title);
						hierarchy.add(custom);
		       		}
					else if(type == null)
					{
						System.out.println("The type you try to push is not valid for : " + tag);
					}
					else if(type.equals("content"))
					{
						//System.out.println("Panel added: ");
						currentPan = new JPanel();
						currentPan.setLayout(null);
						currentPan.setName(name);
						currentPan.setBounds(x, y, width, height);
						currentPan.setSize(width, height);
						if(hierarchy.get(id).getClass().equals(JFrame.class))
						{
							custom.add(currentPan);
						}
						else
						{
							((JPanel) hierarchy.get(id)).add(currentPan);
						}
						hierarchy.add(currentPan);
					}
					else if(type.equals("label"))
					{
						//System.out.println("Label added: ");
						JLabel currLabel = new JLabel(text);
						currLabel.setBounds(x, y, width, height);
						currentPan.add(currLabel);
						hierarchy.add(currLabel);
					}
					else if(type.equals("button"))
					{
						//System.out.println("Button added: ");
						JButton currButton = new JButton(text);
						currButton.setBounds(x, y, width, height);
						
						if(action != null && actionParam != null && actionParam.length != 0)
						{
							Object[] paramList = new Object[actionParam.length];
							for(int i = 0; i < actionParam.length; i++)
							{
								Object paramVal = ParseXML.getValueFromArray(ObjectText,actionParam[i]);
								if(paramVal == null)
								{
									System.out.println("We could not find " + actionParam[i] + ".");
								}
								paramList[i] = paramVal;
								
							}
							
							Constructor[] cons = Class.forName("controller."+action).getConstructors();
							Class[] parameterTypes = null;
							for(int index = 0; index < cons.length; index++)
							{
								if(cons[index].getParameterTypes().length == actionParam.length)
								{
									parameterTypes = cons[index].getParameterTypes();
								}
							}
							
							//System.out.println(parameterTypes[1] + " " +paramList[1].getClass());
							if(parameterTypes == null)
							{
								System.out.println("There was a problem retrieving the parameter types of the constructor.");
							}
							else if(parameterTypes.length == actionParam.length)
							{
								currButton.addActionListener((ActionListener)Class.forName("controller."+action).getDeclaredConstructor(parameterTypes).newInstance(paramList));
							}
							else
							{
								System.out.println("The number of parameters doesn't match the constuctors");
							}
							
						}
						else if(action != null)
						{
							currButton.addActionListener((ActionListener) Class.forName(action).newInstance());
						}
						currentPan.add(currButton);		
						hierarchy.add(currButton);
					}
					else if(type.equals("textfield"))
					{
						//System.out.println("Textfield added: " + ident);
						JTextField currText = new JTextField();
						currText.setBounds(x, y, width, height);
						currentPan.add(currText);
						hierarchy.add(currText);
						ObjectText.add(new Mapping(ident,currText));
					}
					else if(type.equals("passfield"))
					{
						//System.out.println("Passfield added: " + ident);
						JPasswordField currPass = new JPasswordField();
						currPass.setBounds(x, y, width, height);
						currentPan.add(currPass);
						hierarchy.add(currPass);
						ObjectText.add(new Mapping(ident,currPass));
					}
					else if(type.equals("menubar"))
					{
						//System.out.println("Menu Bar added: " + ident);
						JPanel barPanel = new JPanel();
						barPanel.setLayout(null);
						currMenuBar = new JMenuBar();
						barPanel.setBounds(x, y, width, height);
						barPanel.add(currMenuBar);
						hierarchy.add(currMenuBar);
						if(ident != null)
						{
							ObjectText.add(new Mapping(ident,currMenuBar));
						}
						
					}
					else if(type.equals("menuitem"))
					{
						//System.out.println("Menu Item added: " + ident);
						JMenuItem currMenuItem = new JMenuItem();
						currMenuItem.setBounds(x, y, width, height);
						currMenuItem.setText(text);
						if(hierarchy.get(id).getClass().equals(JMenuBar.class))
						{
							((JMenuBar) hierarchy.get(id)).add(currMenuItem);
						}
						else
						{
							((JMenuItem) hierarchy.get(id)).add(currMenuItem);
						}
						hierarchy.add(currMenuItem);
						if(ident != null)
						{
							ObjectText.add(new Mapping(ident,currMenuItem));
						}
					}
				}
		       	
				id++;   	
			 }
			 else if (eventType == XmlPullParser.TEXT && id >= 0) 
			 {
				 // Implement text later             
			 }
			 else if (eventType == XmlPullParser.END_TAG) 
			 { 
				 hierarchy.remove(id);
				 id--;
				 /*
				 //System.out.println(type);
				 if(type != null && type.equals("content"))
				 {
					 currentPan.revalidate();
					 currentPan.repaint();
					 currentPan.setVisible(true);
					 custom.add(currentPan);
				 }
				 */				 
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
