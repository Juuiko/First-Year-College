package eTest3;

import java.util.ArrayList;
import java.util.List;

public class Stack {
	
	List<Integer> stackList = new ArrayList<>();

	Stack(){
	}
	
	public void push(int input) {
		stackList.add(input);
	}
	
	public int pop() {
		if(stackList.size()>0) {
			int output = stackList.remove(stackList.size()-1);
			return output;
		}
		else return -1;
		
	}
	
	public int peek() {
		if(stackList.size()>0) {
			int output = stackList.get(stackList.size()-1);
			return output;
		}
		else return -1;
	}
	
	public int search(int input) {
		int stackNumber;
		for(int i=0; i<stackList.size(); i++) {
			stackNumber = stackList.get(i);
			if(stackNumber==input) {
				i=stackList.size()-1-i;
				return i;
			}
		}
		return -1;
	}
	
	public void move(int input) {
		int inputLocation = -1;
		if(search(input) != -1) {
			int stackNumber;
			for(int i=0; i<stackList.size(); i++) {
				stackNumber = stackList.get(i);
				if(stackNumber==input) {
					inputLocation=i;
				}
			}
			int inputNumber = stackList.remove(inputLocation);
			push(inputNumber);
		}
	}
}
