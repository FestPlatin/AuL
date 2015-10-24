package de.dd.htw.gypser.martin.aul.binarysearch;

public class BinaryTree {
	private BinaryTree leftSide = null;
	private BinaryTree rightSide = null;
	private int currentValue;
	
	public BinaryTree(int value) {
		this.currentValue = value;
	}
	
	public boolean addValue(int value) {
		boolean added = false;
		
		if(value > currentValue) {
			if(rightSide != null) {
				added = rightSide.addValue(value);
			} else {
				rightSide = new BinaryTree(value);
				added = true;
			}
		} 
		
		if(value < currentValue) {
			if(leftSide != null) {
				added = leftSide.addValue(value);
			} else {
				leftSide = new BinaryTree(value);
				added = true;
			}
		}
		
		return added;
	}
	
	public BinaryTree searchValue(int value) {
		BinaryTree found = null;
		
		if(value == currentValue) found = this;
		if(value < currentValue) {
			if(leftSide != null) {
				found = leftSide.searchValue(value);
			}
		}
		if(value > currentValue) {
			if(rightSide != null) {
				found = rightSide.searchValue(value);
			}
		}
		
		return found;
	}
	
	public boolean removeValue(int value) {
		boolean removed = false;
		
		BinaryTree bTree = searchValue(value);
		
		return removed;
	}

	public BinaryTree getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(BinaryTree leftSide) {
		this.leftSide = leftSide;
	}

	public BinaryTree getRightSide() {
		return rightSide;
	}

	public void setRightSide(BinaryTree rightSide) {
		this.rightSide = rightSide;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}
}
