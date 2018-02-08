import java.util.regex.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Arithmetic{

	public Node root;
	public int[] numbers;
	public int bedrock,target;
	public boolean order;
	public Version v = new Normal();

	
	public static void main(String[] args){

		System.out.println("Welcome to Arithmetic");
		int[] a = {5,4,3,2};
		Arithmetic problem = new Arithmetic(a, 11, true);
		problem.traverse(problem.root);

	}

	public Arithmetic(int[] numbers, int target, boolean order){

		this.numbers = numbers;
		this.target = target;
		this.order = order;
		bedrock = numbers.length -1;
		root = new Node(numbers[0]);
	}

	public void traverse(Node node){

		System.out.println("\n\nAt: " + node + ". Total = " + node.total);

		if(node.total == -1){
			node.total = node.value;
		}



		//If at bedrock
		if(node.depth == bedrock){
			if(node.backlog != -1){
				node.total +=  node.backlog; 

			}
			System.out.println("AT BEDROCK, total: " + node.total + "\n ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
			return;
		}

		//if(total => target){//PRUNE}

		//Dealing with the LEFT node
		if(node.left == null ){
			
			//make the new node, backlog and total will be set later
			int below = node.depth +1;
			node.left = new Node(node, numbers[below], below);
			node.left.path += "*" + node.left.value;
			

			//If last operation was *
			if(node.backlog == -1){
				
				//v.multiMulti(node);
				System.out.println("**");
				node.left.total = node.total * node.left.value;
				node.left.backlog = -1;
				
			}else{
				//else last operation was + 
				System.out.println(" +* ");
				int tempBacklog = node.backlog;
				tempBacklog = tempBacklog - node.value; //extract from backlog
				node.left.total = node.value * node.left.value; //multiply parent and child values
				node.left.total += node.total + tempBacklog;  // add the total and backlog.
				node.left.backlog = -1; //to indicate last operation was *
			}
		
			//now traverse left
			System.out.println("Going Left");
			traverse(node.left);
		}

		//Dealing with the RIGHT node
		if(node.right == null ){
			
			//make the new right ndoe
			int below = node.depth +1;
			node.right = new Node(node, numbers[below], below);
			node.right.path += "+" + node.right.value;
			
			if(node.backlog == -1){
				System.out.println("*+");
				// * + 
				//System.out.println("Pre *+, Parent Node: " + node.toString());
				//System.out.println("Giving up here..." + node.backlog);
				//must overwrite backlog as was -1
				//System.out.println("BackLog before: *+ " + node.right.backlog);
				node.right.backlog = node.right.value; //sets childs value to its backlog
				//System.out.println("BackLog after: + setting "  + node.right.backlog);
				node.right.total = node.total; //inherits same total
				//System.out.println("Post *+, Right child node: " + node.right.toString());
				System.out.println("*+ backlog: " + node.right.backlog);
			}else{
				// + +
				System.out.println("++");
				System.out.println("Node: " + node.toString());
				System.out.println("Nodes backlog: " + node.backlog);
				System.out.println("Right nodes value: " + node.right.value);
				System.out.println("Childs backlog value before doing anything: " + node.right.backlog);
				node.right.backlog = node.right.value + node.backlog;
				
				System.out.println("Childs backlog after adding parents backlog and childs value: " + node.right.backlog );
				node.right.total = node.total;
				System.out.println("++ child backlog: " + node.right.backlog);
				System.out.println("Child node after ++ : " + node.right);
				//traverse(node.right);
			}

			System.out.println("Going Right");
			traverse(node.right);
		}
	}

	public int addWaiting(ArrayList<Integer> list){
		int x = 0;
		for(Integer i : list ){
			x += i;
		}
		return x;
	}
}
