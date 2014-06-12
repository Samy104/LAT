package layouts;

import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUIBuilder {
	
	public GUIBuilder()
	{
		
	}

	public static Object BuildGUIElement(ParsedObject parsed, ArrayList<Object> hierarchy, ArrayList<Mapping> ObjectText)
	{
		try
		{
			if(parsed.getType() == null)
			{
				System.out.println("The type you try to push is not valid for : " + parsed.getTag());
			}
			else if(parsed.getType().equals("content"))
			{
				//System.out.println("Panel added: ");
				// Define the JPanel
				JPanel currentPan = new JPanel();
				currentPan.setLayout(null);
				currentPan.setName(parsed.getName());
				currentPan.setBounds(parsed.getPosX(), parsed.getPosY(), parsed.getWidth(), parsed.getHeight());
				currentPan.setSize(parsed.getWidth(), parsed.getHeight());
				
				// Add the JPanel to the hierarchy and return the component.
				hierarchy.add(currentPan);
				return(currentPan);
			}
			else if(parsed.getType().equals("label"))
			{
				//System.out.println("Label added: ");
				//Define the JLabel
				JLabel currLabel = new JLabel(parsed.getText());
				currLabel.setBounds(parsed.getPosX(), parsed.getPosY(), parsed.getWidth(), parsed.getHeight());
				
				//Add the JLabel to the hierarchy and return the component
				hierarchy.add(currLabel);
				if(parsed.getId() != null)
				{
					ObjectText.add(new Mapping(parsed.getId(),currLabel));
				}
				return(currLabel);
			}
			else if(parsed.getType().equals("button"))
			{
				//System.out.println("Button added: ");
				//Define the JButton
				JButton currButton = new JButton(parsed.getText());
				currButton.setBounds(parsed.getPosX(), parsed.getPosY(), parsed.getWidth(), parsed.getHeight());
				
				if(parsed.getAction() != null && parsed.getActionParam() != null && parsed.getActionParam().length != 0)
				{
					Object[] paramList = new Object[parsed.getActionParam().length];
					for(int i = 0; i < parsed.getActionParam().length; i++)
					{
						Object paramVal = ParseXML.getValueFromArray(ObjectText,parsed.getActionParam()[i]);
						if(paramVal == null)
						{
							System.out.println("We could not find " + parsed.getActionParam()[i] + ".");
						}
						paramList[i] = paramVal;
						
					}
					
					Constructor[] cons = Class.forName("controller."+parsed.getAction()).getConstructors();
					Class[] parameterTypes = null;
					for(int index = 0; index < cons.length; index++)
					{
						//System.out.println(parsed.getActionParam().length + "  vs  " + cons[index].getParameterTypes().length);
						if(cons[index].getParameterTypes().length == parsed.getActionParam().length)
						{
							parameterTypes = cons[index].getParameterTypes();
						}
					}
					
					//System.out.println(parameterTypes[0] + " " +paramList[0].getClass());
					if(parameterTypes == null)
					{
						System.out.println("There was a problem retrieving the parameter types of the constructor.");
					}
					else if(parameterTypes.length == parsed.getActionParam().length)
					{
						currButton.addActionListener((ActionListener)Class.forName("controller."+parsed.getAction()).getDeclaredConstructor(parameterTypes).newInstance(paramList));
					}
					else
					{
						System.out.println("The number of parameters doesn't match the constuctors");
					}
					
				}
				else if(parsed.getAction() != null)
				{
					currButton.addActionListener((ActionListener) Class.forName(parsed.getAction()).newInstance());
				}
				//currButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "controller."+parsed.getAction());
				//currButton.getActionMap().put("controller."+parsed.getAction(), (Action) currButton.getActionListeners()[0]);
				
				// Add the JButton to the hierarchy and return the value
				hierarchy.add(currButton);
				return(currButton);		
			}
			else if(parsed.getType().equals("textfield"))
			{
				//System.out.println("Textfield added: " + ident);
				// Define the JTextField
				JTextField currText = new JTextField();
				currText.setBounds(parsed.getPosX(), parsed.getPosY(), parsed.getWidth(), parsed.getHeight());
				
				// Add the JTextField to the hierarchy, map the object to it's ID and return the value
				hierarchy.add(currText);
				if(parsed.getId() != null)
				{
					ObjectText.add(new Mapping(parsed.getId(),currText));
				}
				return(currText);
			}
			else if(parsed.getType().equals("passfield"))
			{
				//System.out.println("Passfield added: " + ident);
				// Define the JPasswordField
				JPasswordField currPass = new JPasswordField();
				currPass.setBounds(parsed.getPosX(), parsed.getPosY(), parsed.getWidth(), parsed.getHeight());
				
				// Add the JPassField to the hierarchy, map the object to it's ID and return the value
				hierarchy.add(currPass);
				if(parsed.getId() != null)
				{
					ObjectText.add(new Mapping(parsed.getId(),currPass));
				}
				return(currPass);
			}
			else if(parsed.getType().equals("combobox"))
			{
				//System.out.println("Combobox added: " + ident);
				// Define the JPasswordField
				JComboBox<String> currCB = new JComboBox<String>(parsed.getValues());
				currCB.setBounds(parsed.getPosX(), parsed.getPosY(), parsed.getWidth(), parsed.getHeight());
				
				
				// Add the JPassField to the hierarchy, map the object to it's ID and return the value
				hierarchy.add(currCB);
				if(parsed.getId() != null)
				{
					ObjectText.add(new Mapping(parsed.getId(),currCB));
				}
				return(currCB);
			}
			else if(parsed.getType().equals("menubar"))
			{
				//System.out.println("Menu Bar added: " + ident);
				//Define the JMenuBar
				JMenuBar currMenuBar = new JMenuBar();
				currMenuBar.setLayout(null);
				currMenuBar.setBounds(parsed.getPosX(), parsed.getPosY(), parsed.getWidth(), parsed.getHeight());
				
				// Add the JMenuBar to the hierarchy, map the object to it's ID(if existent) and return the value
				hierarchy.add(currMenuBar);
				if(parsed.getId() != null)
				{
					ObjectText.add(new Mapping(parsed.getId(),currMenuBar));
				}
				return currMenuBar;
			}
			else if(parsed.getType().equals("menuitem"))
			{
				//System.out.println("Menu Item added: " + ident);
				// Define JMenuItem
				JMenuItem currMenuItem = new JMenuItem();
				currMenuItem.setBounds(parsed.getPosX(), parsed.getPosY(), parsed.getWidth(), parsed.getHeight());
				currMenuItem.setText(parsed.getText());
				
				// Add the JMenuItem to the hierarchy, map the object to it's ID(if existent) and return the value
				hierarchy.add(currMenuItem);
				if(parsed.getId() != null)
				{
					ObjectText.add(new Mapping(parsed.getId(),currMenuItem));
				}
				return currMenuItem;
			}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The class was not found for the dynamic call");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println("The argument was invalid for the dynamic call");
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The class was instantiated improperly");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The class was called illegaly");
		}  catch (InvocationTargetException e) {
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
		System.out.println("GUIBuilder created an empty component associated to " + parsed.getName() + " (" + parsed.getType() + ")");
		return null;
	}
}
