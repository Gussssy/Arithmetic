public class Arithmetic{
	
	int target,bedrock;
	int[] numbers;
	boolean normal,sucess;
	int firstValue;


	public static void main(String args[]){

		//Scanner scan = new Scanner();
		
		int[] a = new int[] {5,4,3,2,2,2};
		String path = "5";
		
		//makde the game
		Arithmetic game = new Arithmetic(a,104);
		
		//play the game
		game.traverseLR(game.firstValue, -1,0,1,-1, path);

	}

	public Arithmetic(int[] numbers, int target){
		this.numbers = numbers;
		firstValue = numbers[0];
		this.target = target;
		sucess = false;
		bedrock = numbers.length -1;
	}

	public void traverse(int value, int total, int depth, int multilog, int addlog, String path){
		
		System.out.println("At Depth: " + depth + ", value: " + value + ", addlog: " + addlog + ", multilog: " +  multilog + ", total: " +  total);

		//Base Case
		if(sucess){
			return;
		}

		//Pruning
		int runningTotal = getTotal(total, multilog,addlog);
		if(runningTotal > target){
			System.out.println("Running Total Exceeds Target " + runningTotal + " > " + target);
			return;
		}

		//Bedrock Case
		if(depth == bedrock){
			System.out.println("At bedrock, Path: " +path+ "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			total = getTotal(total, addlog, multilog);
			System.out.println("Total: " + total);
			if (total == target){
				System.out.println("! ! ! ! ! SUCESS ! ! ! ! !");
				sucess = true;
			}
			return;
		}

		//Root case
		if(total == -1){
			addlog = value;
			total = 0;
		}

		//Shared 'Child' variables
		int childDepth = depth + 1;
		int childValue = numbers[childDepth];

		//Specific 'Child' Variables
		int leftAddlog,leftMultilog,leftTotal;
		String leftPath = path + "*" + childValue;
		int rightAddlog, rightMultilog, rightTotal;
		String rightPath = path + "+" + childValue;

		//ADDITION
		if(addlog == -1){
			//**
			leftMultilog = childValue * multilog;
			leftAddlog = -1;
			leftTotal = total; //no change to total 
		}else{
			//+*
			leftMultilog = childValue * value;
			leftAddlog = -1; //to indicate last op was *
			leftTotal = total + addlog - value; //inherits total + remainder of addlog after extraction of parent value 
		}
		//Traverse Left
		traverse(childValue, leftTotal, childDepth,leftMultilog,leftAddlog, leftPath);


		//MULTIPLICATION
		if(addlog == -1){
			//*+
			rightTotal = total + multilog; //end of a multi bracket, add multilog to child total
			rightMultilog = -1; // to indicate last operation was +
			rightAddlog = childValue; //add childs value to childs addlog to start additon bracket
		}else{
			//++
			rightTotal = total; //add bracket not terminated
			rightAddlog = addlog + childValue; //add childs value to growing addlog
			rightMultilog = -1; //to indicate last operation mas multi
		}
		//Traverse Right
		traverse(childValue, rightTotal, childDepth,rightMultilog,rightAddlog, rightPath);
	}

	

	public void traverseLR(int value, int total, int depth, int multilog, int addlog, String path){
		
		System.out.println("Yo");
		System.out.println("At Depth: " + depth + ", value: " + value + ", addlog: " + addlog + ", multilog: " +  multilog + ", total: " +  total);

		//Base Case
		if(sucess){
			return;
		}

		//Pruning
		int runningTotal = getTotalLR(total, multilog,addlog);
		if(runningTotal > target){
			System.out.println("Running Total Exceeds Target " + runningTotal + " > " + target);
			return;
		}

		//Bedrock Case
		if(depth == bedrock){
			System.out.println("At bedrock, Path: " +path+ "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			total = getTotalLR(total, addlog, multilog);
			System.out.println("Total: " + total);
			if (total == target){
				System.out.println("! ! ! ! ! SUCESS ! ! ! ! !");
				//sucess = true;
			}
			return;
		}

		//Root case
		if(total == -1){
			addlog = value;
			multilog = 1;
			total = 1;
		}

		//Shared 'Child' variables
		int childDepth = depth + 1;
		int childValue = numbers[childDepth];

		//Specific 'Child' Variables
		int leftAddlog,leftMultilog,leftTotal;
		String leftPath = path + "*" + childValue;
		int rightAddlog, rightMultilog, rightTotal;
		String rightPath = path + "+" + childValue;

		//ADDITION
		if(addlog == -1){
			//**
			leftMultilog = childValue * multilog;
			leftAddlog = -1;
			leftTotal = total; //no change to total 
		}else{
			//+*
			leftMultilog = childValue; //assign child value to multilog, not a preference operation
			leftAddlog = -1; //to indicate last op was *
			leftTotal = addlog * total; //terminate add block, multiply total by addlog (which includes parent value)
		}
		//Traverse Left
		traverseLR(childValue, leftTotal, childDepth,leftMultilog,leftAddlog, leftPath);


		//MULTIPLICATION
		if(addlog == -1){
			//*+
			rightTotal = (multilog/value) * total; //undo last multi as addition preference, child gets new total as bracket terminated
			rightMultilog = -1; // to indicate last operation was +
			rightAddlog = value + childValue; //add ecxtracted parent value and childs value to childs addlog, addition is preference 
		}else{
			//++
			rightTotal = total; //add bracket not terminated
			rightAddlog = addlog + childValue; //add childs value to growing addlog
			rightMultilog = -1; //to indicate last operation mas multi
		}
		//Traverse Right
		traverseLR(childValue, rightTotal, childDepth,rightMultilog,rightAddlog, rightPath);
	}

	public int getTotal(int total, int multilog, int addlog){
		if(addlog == -1){
			total += multilog;
		}else{
			total += addlog;
		}
		return total;
	}

	public int getTotalLR(int total, int multilog, int addlog){
		if(addlog == -1){
			total *= multilog;
		}else{
			total *= addlog;
		}
		return total;
	}

	
}