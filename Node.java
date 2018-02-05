public class Node{

	public int value, depth,total, backlog;
	public Node parent;
	public Node left = null;
	public Node right = null;
	String path = "";


	//Constructor for normal node
	public Node(Node parent, int value, int depth ){

		this.parent = parent;
		this.value = value;
		this.depth = depth;
		path = parent.path;
		backlog = 999;
	}

	//Constructor for the root node, no parent, level is 0
	public Node(int value){
		parent = null;
		this.value = value;
		depth = 0;
		total = -1;
		backlog = -1;
		path = "" + value;
		System.out.println("Making the root node: " + toString());
	}

	public String toString(){
		String str = "Node value: " + value + ", depth: " + depth + ", Path:" + path + ", Total: " + total + ", backlog: " + backlog; 
		return str;
	}


}