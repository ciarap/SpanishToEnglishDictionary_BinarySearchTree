
import java.io.File;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The main part of the translator.
 * 
 * @author  Ciara Power
 */
public class Dictionary {
	
	private static  boolean debug = true; 
	       
	private static Node root=new Node(null,null,null, null);
	private static ArrayList<Node> nodes=new ArrayList<Node>();
	private static Dictionary dict;      
	private String spanishWord="";    // Spanish word entered for translating
	private String translation="";     // translated
	private File  dictionary = new File("dictionary.xml");   // xml file for saved dictionary heap
	private  Serializer serializer = new XMLSerializer(dictionary);   //to load and save xml
	private static BinaryTreeIndentPrint printer=new BinaryTreeIndentPrint();
	private static JFrame frame;




	public static void main(String[] args) throws Exception {
		dict=new Dictionary();
		UserInterface dictInterface=new UserInterface(dict);
		frame=dictInterface.getFrame();
		dict.load("short.txt");// pass txt file to load method
        if (debug){
        	//System.out.println("--------- LOADED HEAP : ---------\n"+nodes.toString()+"\n---------");
          printer.printPreOrder(root," ","B");
        }
	}
	
	public Node getNode() {     //returns heap
		return root;
	}

	public void setNode(Node node) {   //sets heap 
		this.root = node;
	}

	public void load (String fileName) throws Exception{    //method to load from txt or xml file
		if (debug){
        	System.out.println("---------\n LOAD \n---------");
        }
		if (dictionary.isFile())   // if xml file exists
		{
			serializer.read();   //read xml file
			nodes=(ArrayList<Node>) serializer.pop();   //pop the heap stored in xml to global heap variable
			 if (debug){
		        	System.out.println("---------\n XML File Loaded \n---------");
		        }
		}
		else{
			readFile(fileName);  // if no xml file, read the txt file 
			//write();   //save the heap to xml once txt file loaded
			 if (debug){
		        	System.out.println("---------\n TXT File Loaded \n---------");
		       }
		}
		
	}
	
	public  void readFile(String fileName) throws Exception{   //method to read the txt file of dictionary words, takes filename in as parameter
		File in=new File(fileName);
		Scanner inTerm = new Scanner(in);
		String delims = "[?]";//each field in the file is separated(delimited) by a ?.
		while (inTerm.hasNextLine()) {     //while the txt file has not reached an end
			String termDetails = inTerm.nextLine();
			// parse term details string
			String[] termTokens = termDetails.split(delims);// split the line of data into a String[] by the ? divider
			if (termTokens.length == 2) {     // each token should have a spanish word and english translations 
			if (debug){
				System.out.println(termTokens[0]+" : "+termTokens[1]);
			}
			 Node node=new Node(termTokens[0],termTokens[1].trim(),null,null);
			 nodes.add(node);
             if(nodes.size()==1){
            	setNode(node);
             }
             else{ 
            	 add(node,root);
             }
			}
		}
		if (debug){
		System.out.println("---------\n Heap size: "+nodes.size()+"\n---------");
		}
		inTerm.close();
	}

	
	private void add(Node node,Node baseNode) {
		if (node.compareTo(baseNode)<0){
			if(baseNode.getLeftTree()!=null){
			add(node,baseNode.getLeftTree());
			}
			else{
				baseNode.setLeftTree(node);
			}
		}
    	if (node.compareTo(baseNode)>0){
    		if(baseNode.getRightTree()!=null){
    			add(node,baseNode.getRightTree());
    			}
    			else{
    				baseNode.setRightTree(node);
    			}
		}
	}
	
	public void deleteNode(Node node,Node baseNode){
		if (baseNode==null){
			if(debug){
				System.out.println("Tree empty - Nothing to delete");
			}	
		}
		else{
			if (node.compareTo(baseNode)<0){
				if(baseNode.getLeftTree()!=null){
					if (node.compareTo(baseNode.getLeftTree())==0){
						if(baseNode.getLeftTree().getLeftTree()==null && baseNode.getLeftTree().getRightTree()==null){
						baseNode.setLeftTree(null);
						}
						else if(baseNode.getLeftTree().getLeftTree()!=null && baseNode.getLeftTree().getRightTree()==null){
							baseNode.setLeftTree(baseNode.getLeftTree().getLeftTree());
						}
						else if(baseNode.getLeftTree().getLeftTree()==null && baseNode.getLeftTree().getRightTree()!=null){
							baseNode.setRightTree(baseNode.getLeftTree().getRightTree());
						}
						 if (debug){
					        	//System.out.println("--------- LOADED HEAP : ---------\n"+nodes.toString()+"\n---------");
					          printer.printPreOrder(root," ","B");
					        }
					}
					else{
					deleteNode(node,baseNode.getLeftTree());
					}
				}
				else{
					JOptionPane.showMessageDialog(frame,"This spanish word is not in the dictionary!","Error",0);
				}
			}
			else if (node.compareTo(baseNode)>0){
				if(baseNode.getRightTree()!=null){
					if (node.compareTo(baseNode.getRightTree())==0){
						if(baseNode.getRightTree().getLeftTree()==null && baseNode.getRightTree().getRightTree()==null){
							baseNode.setRightTree(null);
							}
						else if(baseNode.getRightTree().getLeftTree()!=null && baseNode.getRightTree().getRightTree()==null){
							baseNode.setLeftTree(baseNode.getRightTree().getLeftTree());
						}
						else if(baseNode.getRightTree().getLeftTree()==null && baseNode.getRightTree().getRightTree()!=null){
							baseNode.setRightTree(baseNode.getRightTree().getRightTree());
						}
						
						 if (debug){
					        	//System.out.println("--------- LOADED HEAP : ---------\n"+nodes.toString()+"\n---------");
					          printer.printPreOrder(root," ","B");
					        }
					}
					else{
					deleteNode(node,baseNode.getRightTree());
					}
				}
				else{
					JOptionPane.showMessageDialog(frame,"This spanish word is not in the dictionary!","Error",0);
				}
			}
		}
	}
	
	
	public void clear()   //clear strings for translation and spanish word
	{
		spanishWord = "";
		translation="";

	}

	public Node findMatch(String spanish) {      // return a pair with spanish word that matches the spanish parameter
		Node item=new Node(spanish, "",null,null);    //create pair with the required spanish word, english not required
		if (debug){
			System.out.println("---------\n"+item.toString()+"\n---------");
		}
		return null;   //call search method and pass through the pair created above, 
		 															// passes through the starting point in heap, and booleans to indicate the left and right children heaps were not searched yet	
	}
	
	public void add(String spanish,String english) throws Exception {   //method to add translation to dictionary
		add(new Node(spanish,english,null,null),root);
		   if (debug){
	        	//System.out.println("--------- LOADED HEAP : ---------\n"+nodes.toString()+"\n---------");
	          printer.printPreOrder(root," ","B");
	        }
		//write();  // save to xml, to save any changes 
	}

	public String getSpanishWord() {   //get the spanish word entered to be translated
		 if (debug){
	        	System.out.println("---------\nGET Spanish Word: "+ spanishWord+"\n---------");
	        }
		return spanishWord;
	}

	public void setSpanishWord(String spanishWord) {   //set the spanish word with the parameter entered
		if (debug){
        	System.out.println("---------\nSET Spanish Word: "+ spanishWord+"\n---------");
        }
		this.spanishWord = spanishWord;
	}

	public String getTranslation() {   //get the translation
		if (debug){
        	System.out.println("---------\nGET Translation: "+ translation+"\n---------");
        }
		return translation;
	}

	public void setTranslation(String translation) {    //set the translation
		if (debug){
        	System.out.println("---------\nSET Translation: "+ translation+"\n---------");
        }
		this.translation = translation;
	}
	
	public int treeTraversal(){    //returns the tree traversal integer at that point in time from heap object
		if (debug){
        	System.out.println("---------\nGET Tree Traversal from heap: "+null+"\n---------");
        }
		return 0;   
	}

	public void write() throws Exception {    // saves heap to xml
		if (debug){
        	System.out.println("---------\n WRITE \n---------");
        }
		serializer.push(nodes);
		serializer.write(); 

	}

	public File getDictionary() {
		return dictionary;
	}


}
