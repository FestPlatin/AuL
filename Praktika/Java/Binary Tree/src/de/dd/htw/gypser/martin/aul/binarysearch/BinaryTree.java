package de.dd.htw.gypser.martin.aul.binarysearch;

public class BinaryTree {
	private BinaryTree leftSide = null;
	private BinaryTree rightSide = null;
	private BinaryTree parent = null;
	private int currentValue;
	
	public BinaryTree(BinaryTree parent, int value) {
		this.currentValue = value;
		this.parent = parent;
	}

	public void setParent(BinaryTree parent)
	{
		this.parent = parent;
	}

	public BinaryTree getParent()
	{
		return parent;
	}
	
	public boolean addValue(int value) {
		boolean added = false;
		
		if(value > currentValue) {
			if(rightSide != null) {
				added = rightSide.addValue(value);
			} else {
				rightSide = new BinaryTree(this, value);
				added = true;
			}
		} 
		
		if(value < currentValue) {
			if(leftSide != null) {
				added = leftSide.addValue(value);
			} else {
				leftSide = new BinaryTree(this, value);
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
		
		if(bTree != null) {
			if(bTree.getLeftSide() == null && bTree.getRightSide() == null) {
				setParentSide(bTree.parent, null, value);
				removed = true;
			} else {
				if(bTree.getRightSide() == null) {
					setParentSide(bTree.parent, bTree.getLeftSide(), value);
				} else if(bTree.getLeftSide() == null) {
					setParentSide(bTree.parent, bTree.getRightSide(), value);
				} else {
					BinaryTree temp = bTree.getRightSide();
					while(temp.getLeftSide() != null) {
						temp = temp.getLeftSide();
					}
					temp.removeValue(temp.getCurrentValue());
					setParentSide(bTree.parent, temp, value);
					BinaryTree rightSide = bTree.getRightSide();
					BinaryTree leftSide = bTree.getLeftSide();
					temp.setLeftSide(leftSide);
					temp.setRightSide(rightSide);
					if(rightSide != null) rightSide.setParent(temp);
					if(leftSide != null) leftSide.setParent(temp);
					
				}
				removed = true;
			}
		}
		
		return removed;
	}
	
	private void setParentSide(BinaryTree parent, BinaryTree bTree, int value) {
		if(bTree != null) {
			bTree.setParent(parent);
		}
		if(parent != null) {
			BinaryTree rightSide = parent.getRightSide();
			BinaryTree leftSide = parent.getLeftSide();
			if (leftSide != null && leftSide.getCurrentValue() == value)
 			{
				parent.getLeftSide().setParent(bTree);
				parent.setLeftSide(bTree);
			} 
			if (rightSide != null && rightSide.getCurrentValue() == value)
 			{
				parent.getRightSide().setParent(bTree);
				parent.setRightSide(bTree);
			}
		}
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
