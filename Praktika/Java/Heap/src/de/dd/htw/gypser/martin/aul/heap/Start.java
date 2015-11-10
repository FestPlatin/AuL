package de.dd.htw.gypser.martin.aul.heap;

import java.util.*;

public class Start
{
	public static void main(String[] args)
	{
		System.out.println("Setup Heaps");
		HeapNode node4 = new HeapNode(4);
		HeapNode node8 = new HeapNode(8);
		HeapNode node5 = new HeapNode(5);
		HeapNode node6 = new HeapNode(6);
		HeapNode node7 = new HeapNode(7);
		HeapNode node3 = new HeapNode(3);
		HeapNode node2 = new HeapNode(2);
		HeapNode node9 = new HeapNode(9);
		HeapNode node13 = new HeapNode(13);
		HeapNode node14 = new HeapNode(14);

		System.out.println("Merging Heaps");		
		HeapNode result = HeapManager.merge(node4, node5);
		result = HeapManager.merge(result, node8);
		result = HeapManager.merge(result, node6);
		result = HeapManager.merge(result, node2);
		result = HeapManager.merge(result, node7);
		result = HeapManager.merge(result, node3);
		result = HeapManager.merge(result, node9);
		
		HeapNode result2 = HeapManager.merge(node14, node13);
		result = HeapManager.merge(result, result2);
		
		System.out.println("Done");
	}
}
