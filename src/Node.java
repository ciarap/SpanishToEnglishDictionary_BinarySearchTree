
public class Node implements BinaryTreeInterface,Comparable<Object>{

	private Node leftTree;
	private Node rightTree;
	private String spanish;
	private String english;
	
	public Node(String spanish,String english, Node leftTree, Node rightTree) {
		this.spanish=spanish;
		this.english=english;
		this.leftTree = leftTree;
		this.rightTree = rightTree;
	}


	public Node getLeftTree() {
		return leftTree;
	}

	public void setLeftTree(Node leftTree) {
		this.leftTree = leftTree;
	}

	public Node getRightTree() {
		return rightTree;
	}

	public void setRightTree(Node rightTree) {
		this.rightTree = rightTree;
	}
	public String getSpanish() {
		return spanish;
	}
	public void setSpanish(String spanish) {
		this.spanish = spanish;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}

	@Override
	public int compareTo(Object arg0) {   // compares pair objects by spanish word, as this is how the dictionary heap is used
		return this.getSpanish().compareTo(((Node) arg0).getSpanish());

	}



	@Override
	public String toString() {
		return "Node [leftTree=" + leftTree + ", rightTree=" + rightTree + ", spanish=" + spanish + ", english="
				+ english + "]";
	}


	@Override
	public Object getRootData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfNodes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTree(Object rootData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTree(Object rootData, BinaryTreeInterface leftTree, BinaryTreeInterface rightTree) {
		// TODO Auto-generated method stub
		
	}
	
	
}
