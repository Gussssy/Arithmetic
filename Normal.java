public class Normal extends Version{

	
	public void multiMulti(Node node){
		System.out.println("**");
		node.left.total = node.total * node.left.value;
		node.left.backlog = -1;
		}


	
	public void addMulti(Node node){
		System.out.println(" +* ");
		int tempBacklog = node.backlog;
		tempBacklog = tempBacklog - node.value; //extract from backlog
		node.left.total = node.value * node.left.value; //multiply parent and child values
		node.left.total += node.total + tempBacklog;  // add the total and backlog.
		node.left.backlog = -1; //to indicate last operation was *
		
	}

	public void multiAdd(Node node){
		System.out.println("*+");
		node.right.backlog = node.right.value;
		node.right.total = node.total;
	}

	public void addAdd(Node node){
		System.out.println("++");
		node.right.backlog = node.right.value + node.backlog;
		node.right.total = node.total;
	}
	
	public int getTotal(Node node){
		int total; 
		if (node.backlog == -1){
			total = node.total;
		}else{
			total = node.total + node.backlog;
		}
		return total;
	}
}