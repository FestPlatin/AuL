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
			smallerHeapNode.setRightSide(null);
			smallerHeapNode.setDistanceToLeaf(1);
			heap = merge(heap, smallerHeapNode);
		}
		else
		{
			if (otherHeapNode.getRightSide() != null)
			{
				heap = merge(otherHeapNode.getRightSide(), smallerHeapNode);
				otherHeapNode.setRightSide(null);
				otherHeapNode.setDistanceToLeaf(1);
				heap = merge(heap, otherHeapNode);
			}
			else
			{
				smallerHeapNode.setRightSide(otherHeapNode);

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

				heap = smallerHeapNode;
			}
		}

		return heap;
	}
}
