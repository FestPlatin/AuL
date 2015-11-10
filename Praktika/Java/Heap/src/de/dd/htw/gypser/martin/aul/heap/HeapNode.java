package de.dd.htw.gypser.martin.aul.heap;

public class HeapNode
{
	private int value;	
	private int distanceToLeaf;
	private HeapNode leftSide;
	private HeapNode rightSide;

	public HeapNode(int value)
	{
		this.leftSide = null;
		this.rightSide = null;	
		this.distanceToLeaf = 1;
		this.value = value;
	}

	public void setDistanceToLeaf(int distanceToLeaf)
	{
		this.distanceToLeaf = distanceToLeaf;
	}

	public int getDistanceToLeaf()
	{
		return distanceToLeaf;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}

	public void setLeftSide(HeapNode leftSide)
	{
		this.leftSide = leftSide;
	}

	public HeapNode getLeftSide()
	{
		return leftSide;
	}

	public void setRightSide(HeapNode rightSide)
	{
		this.rightSide = rightSide;
	}

	public HeapNode getRightSide()
	{
		return rightSide;
	}
	
	
}
