package de.dd.htw.gypser.martin.aul.heap;

public class HeapManager
{
	private static int recursionDeepCounter = 0;

	static public HeapNode merge(HeapNode heap1, HeapNode heap2)
	{
		System.out.println("Recursion Deepness: " + recursionDeepCounter++);	
		HeapNode heap = null;

		HeapNode smallerHeapNode;
		HeapNode otherHeapNode;
		if (heap1.getValue() > heap2.getValue())
		{
			smallerHeapNode = heap2;
			otherHeapNode = heap1;
		}
		else
		{
			smallerHeapNode = heap1;
			otherHeapNode = heap2;
		}

		if (smallerHeapNode.getRightSide() != null)
		{
			heap = merge(otherHeapNode, smallerHeapNode.getRightSide());
			smallerHeapNode.setRightSide(heap);
			setDistance(smallerHeapNode, heap);
			heap = smallerHeapNode;
		}
		else
		{
			if (otherHeapNode.getRightSide() != null)
			{
				heap = merge(otherHeapNode.getRightSide(), smallerHeapNode);	
				otherHeapNode.setRightSide(null);
				heap.setRightSide(otherHeapNode);
			}
			else
			{
				smallerHeapNode.setRightSide(otherHeapNode);

				setDistance(smallerHeapNode, otherHeapNode);

				heap = smallerHeapNode;
			}
		}

		return heap;
	}

	private static void setDistance(HeapNode smallerHeapNode, HeapNode otherHeapNode)
	{
		int distanceToLeaf = 0;
		if (smallerHeapNode.getLeftSide() != null)
		{
			distanceToLeaf = smallerHeapNode.getLeftSide().getDistanceToLeaf();
		}

		if (distanceToLeaf < otherHeapNode.getDistanceToLeaf())
		{
			HeapNode temp = smallerHeapNode.getLeftSide();
			smallerHeapNode.setLeftSide(otherHeapNode);
			smallerHeapNode.setRightSide(temp);
			smallerHeapNode.setDistanceToLeaf(distanceToLeaf + 1);
		}
		else
		{
			smallerHeapNode.setDistanceToLeaf(otherHeapNode.getDistanceToLeaf() + 1);
		}
	}
}
