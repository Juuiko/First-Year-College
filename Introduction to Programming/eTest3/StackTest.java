package eTest3;

public class StackTest {

	public static void main(String[] args) {
			Stack stack = new Stack();
			stack.push(1);
			System.out.print("Printing entire array: " + stack.peek() + " ");
			stack.push(2);
			System.out.print(stack.peek() + " ");
			stack.push(3);
			System.out.print(stack.peek() + " ");
			stack.push(4);
			System.out.print(stack.peek() + " ");
			stack.push(5);
			System.out.print(stack.peek() + " ");
			stack.push(6);
			System.out.print(stack.peek() + " ");
			stack.push(7);
			System.out.print(stack.peek() + " ");
			stack.push(8);
			System.out.print(stack.peek() + " ");
			stack.push(9);
			System.out.print(stack.peek() + " ");
			stack.push(10);
			System.out.println(stack.peek());
			
			stack.pop();
			stack.pop();
			System.out.println("Pop twice -> top number: " + stack.peek());
			
			System.out.println("Searching for 6 to get it's position from the top: " + stack.search(6));
			
			stack.move(3);
			System.out.println("Moving 3 to the top of the stack: " + stack.peek());
			
			System.out.print("Poping entire stack: " + stack.peek() + " ");
			stack.pop();
			System.out.print(stack.peek() + " ");
			stack.pop();
			System.out.print(stack.peek() + " ");
			stack.pop();
			System.out.print(stack.peek() + " ");
			stack.pop();
			System.out.print(stack.peek() + " ");
			stack.pop();
			System.out.print(stack.peek() + " ");
			stack.pop();
			System.out.print(stack.peek() + " ");
			stack.pop();
			System.out.print(stack.peek() + " ");
			stack.pop();
			
	}

}
