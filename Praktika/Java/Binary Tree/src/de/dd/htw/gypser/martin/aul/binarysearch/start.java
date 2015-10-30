package de.dd.htw.gypser.martin.aul.binarysearch;

public class start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		// TODO Auto-generated method stub
		System.out.println("Building Tree");
		
		BinaryTree bTree = new BinaryTree(null, 5);
		bTree.addValue(3);
		bTree.addValue(7);
		bTree.addValue(6);
		bTree.addValue(8);
		bTree.addValue(1);
		bTree.addValue(2);
		

		System.out.println("Done");
		
		int value = 5;
		System.out.println("Searching for Value: " + value);
		BinaryTree result = bTree.searchValue(value);
		if(result != null) {
			System.out.println("Value found!");
		} else {
			System.out.println("Value not found!");
		}

		// If you remove the binary tree root then bTree will still point at the old root
		// a bTree.getLeftSide().getParent() will get you the new root
		// Could be fixed in the future
		System.out.println("Removing Value " + value);
		if(bTree.removeValue(value)) {
			System.out.println("Value removed");
		} else {
			System.out.println("Value has not been removed (is it in the tree?)");
		}
		
		System.out.println("Application done");
	}

}
