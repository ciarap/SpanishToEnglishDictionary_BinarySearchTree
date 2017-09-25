
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the translator. No translation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "Dictionary" to do all the real work.
 * 
 * @author Ciara Power
 */
public class UserInterface
implements ActionListener    // interface which listens for key presses by user
{
	private Dictionary dictionary;

	private JFrame frame;       // frame of translator window
	private JTextField spanishWord;   // the white text box to display spanish word entered
	private JTextField translation;  // the white text box to display translation 
	private JTextField spanishMod;   // the white text box to display spanish word entered as an addition to dictionary
	private JTextField englishMod; // the white text box to display english word entered as an addition to dictionary
	private JTextField spanishDelete;
	
	private boolean debug=false;

	/**
	 * Create a user interface for a given dictionary.
	 */
	public UserInterface(Dictionary dict)
	{
		dictionary =dict;
		makeFrame();    //call to method which creates the window
		frame.setVisible(true);    //display window created

	}
	/**
	 * Make this interface visible again. (Has no effect if it is already
	 * visible.)
	 */
	public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	private void makeFrame()
	{
		frame = new JFrame("Spanish To English Translator");    // initialise frame 

		JPanel contentPane = (JPanel)frame.getContentPane();
		contentPane.setPreferredSize(new Dimension(900, 650));    //set sizes
		contentPane.setLayout(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));
		contentPane.setBackground(Color.WHITE);    // white background behind button panel on calculator

		Font font1 = new Font("Comic Sans MS", Font.BOLD, 24);    // font 
		Font font2 = new Font("Comic Sans MS", Font.PLAIN, 22);    // font 
		
		//PANEL 1
		JPanel panel1 = new JPanel(new GridLayout(3, 2));    // search translation panel
		Border blackline = BorderFactory.createLineBorder(Color.black);   // black border for panel
		TitledBorder title = BorderFactory.createTitledBorder(blackline, "SEARCH A SPANISH TO ENGLISH TRANSLATION:"); //titled border created  with the black line border
		title.setTitleFont(font1);  //border title font set 
		title.setTitleJustification(TitledBorder.CENTER);  //title in center of border
		panel1.setBorder(title);  //set this border on the translation panel
		
		///P1 ROW 1
		spanishWord = new JTextField();    // field to enter the word to be translated 
		JLabel label=new JLabel("Enter Spanish Word here:");
		label.setFont(font1);
		spanishWord.setFont(font2);    // font used for spanishWord text entered
		spanishWord.setPreferredSize( new Dimension( 300, 50 ) );   // text field size
		panel1.add(label);  
		panel1.add(spanishWord);
		
		///P1 ROW 2
		panel1.add(new JLabel()); //add empty jLabel to fill left column with the appearance of blank space
		addButton(panel1, "Translate");
		
		///P1 ROW 3
		JLabel label2=new JLabel("English Translation(s):");
		label2.setFont(font1);
		translation=new JTextField();
		translation.setFont(font2);    // font used for display
		translation.setPreferredSize( new Dimension( 300, 50 ) );   // text field size
		panel1.add(label2);
		panel1.add(translation);
		contentPane.add(panel1, BorderLayout.NORTH);   // translation panel placed at top of window

		//P2 Row 1
		JPanel panel2 = new JPanel(new BorderLayout()); 
		panel2.setLayout(new BorderLayout(0, 0));
		addButton(panel2, "Clear All Text Fields");  // button stretches screen
		contentPane.add(panel2, BorderLayout.CENTER);  

		//PANEL 3
		JPanel panel4=new JPanel(new GridLayout(2,1));
		TitledBorder title4 = BorderFactory.createTitledBorder(blackline, "MODIFY DICTIONARY:");
		title4.setTitleFont(font1);
		title4.setTitleJustification(TitledBorder.CENTER);
		panel4.setBorder(title4);
		
		JPanel panel3 = new JPanel(new GridLayout(3, 2)); 
		TitledBorder title2 = BorderFactory.createTitledBorder(blackline, "ADD TRANSLATION TO DICTIONARY:");
		title2.setTitleFont(font2);
		title2.setTitleJustification(TitledBorder.CENTER);
		panel3.setBorder(title2);

		//P3 ROW 1
		spanishMod = new JTextField();
		spanishMod.setFont(font2);    // font used for textfield
		spanishMod.setPreferredSize( new Dimension( 300, 50 ) );   // display size
		JLabel label3=new JLabel("Enter Spanish Word here:");
		label3.setFont(font1);
		panel3.add(label3);
		panel3.add(spanishMod);
		
		//P3 ROW 2
		englishMod = new JTextField();
		englishMod.setFont(font2);    // font used for display
		englishMod.setPreferredSize( new Dimension( 300, 50 ) );   // display size
		JLabel label4=new JLabel("Enter English Translation(s):");
		label4.setFont(font1);
		panel3.add(label4);
		panel3.add(englishMod);
		
		//P3 ROW 3
		panel3.add( new JLabel());  //empty space appearance on left
		addButton(panel3, "Add");
		
		JPanel panel5 = new JPanel(new GridLayout(2, 2)); 
		TitledBorder title5 = BorderFactory.createTitledBorder(blackline, "DELETE TRANSLATION FROM DICTIONARY:");
		title5.setTitleFont(font2);
		title5.setTitleJustification(TitledBorder.CENTER);
		panel5.setBorder(title5);

		//P3 ROW 1
		spanishDelete = new JTextField();
		spanishDelete.setFont(font2);    // font used for textfield
		spanishDelete.setPreferredSize( new Dimension( 300, 50 ) );   // display size
		JLabel label5=new JLabel("Enter Spanish Word here:");
		label5.setFont(font1);
		panel5.add(label5);
		panel5.add(spanishDelete);

		//P3 ROW 2
		panel5.add( new JLabel());  //empty space appearance on left
		addButton(panel5, "Delete");
		
		panel4.add(panel3,BorderLayout.NORTH);
		panel4.add(panel5,BorderLayout.SOUTH);
		contentPane.add(panel4, BorderLayout.SOUTH); 

		frame.pack();

	}

	/**
	 * Add a button to the button panel.
	 */
	private void addButton(Container panel, String buttonText)
	{
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		button.setBackground(new Color(214,135,253));    //color set to a purple tone
		button.setFont(new Font("Comic Sans MS", Font.BOLD, 26));   //font changed to comic sans ms , and size changed
		button.setForeground(Color.WHITE);  // the text appearing on each button set to white
		panel.add(button);
	}

	/**
	 * An interface action has been performed. Find out what it was and
	 * handle it.
	 */
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();

		if(command.equals("Clear All Text Fields"))
		{
			if(debug){
				System.out.println("---------\n CLEAR ALL TEXT FIELDS\n---------");
			}
			dictionary.clear();      // clear all text fields 
			spanishMod.setText("");
			englishMod.setText("");
		}
		else if(command.equals("Translate") ){
			if(debug){
				System.out.println("---------\n TRANSLATE\n---------");
			}
			dictionary.setSpanishWord(spanishWord.getText());      
			Node match=(Node) dictionary.findMatch(dictionary.getSpanishWord());   //get match 
			if(match==null){
				dictionary.setTranslation("No Matches");   //no matches were found if null value returned 
			}
			else{  //match found
				dictionary.setTranslation(""+match.getEnglish()+"	(Traversals: "+dictionary.treeTraversal()+")");  // display match english word and traversals taken to find it up and down heap
			}
		}
		else if (command.equals("Add")){
			if(debug){
				System.out.println("---------\n ADD\n---------");
			}
			if(!spanishMod.getText().isEmpty()&& !englishMod.getText().isEmpty()){   // if both text fields have text entered
				dictionary.setSpanishWord("");
				dictionary.setTranslation("");
				try {
					if(debug){
						System.out.println(spanishMod.getText()+":"+englishMod.getText());
					}
					dictionary.add(spanishMod.getText(),englishMod.getText());   // add the new translation pairing
				} catch (Exception e) {
					if(debug){				
						System.out.println("Error: Could not add and save translation");
					}
				}
				spanishMod.setText("");  // when added, reset text fields to blank to indicate it has been entered into dictionary
				englishMod.setText("");
			}
			
			else{
				JOptionPane.showMessageDialog(frame, "Please enter a spanish word and a translation!","Error",0);
			}
		}
		else if (command.equals("Delete")){
			if(debug){
				System.out.println("---------\n DELETE\n---------");
			}
			if(!spanishDelete.getText().isEmpty()){
				dictionary.deleteNode(new Node(spanishDelete.getText(),null,null,null),dictionary.getNode());
				spanishDelete.setText("");
			}
			else{
				JOptionPane.showMessageDialog(frame, "Please enter a spanish word to delete!","Error",0);
			}
		}

		redisplay();  // display updated for each button press
	}

	public JFrame getFrame() {
		return frame;
	}
	/**
	 * Update the interface display to show the current value of the 
	 * translator.
	 */
	private void redisplay()
	{
		spanishWord.setText(""+dictionary.getSpanishWord());
		translation.setText("" + dictionary.getTranslation());
	}


}
