package main;

import btree.BTree;
import btree.DuplicateValueException;

public class Main {
	
	public static void main(String args[]) throws DuplicateValueException {
		BTree btree = new BTree(2,5,0);
		
		btree.insert(12);
		btree.insert(6);
		btree.insert(8);
		btree.insert(4);
		btree.insert(13);
		btree.insert(2);
		btree.insert(5);
		btree.insert(3);
		System.out.println(btree);
		System.out.println("DEL 13");
		btree.delete(13);
		System.out.println(btree);
		System.out.println("DEL 5");
		btree.delete(5);
		System.out.println(btree);
		System.out.println("DEL 4");
		btree.delete(4);
		System.out.println(btree);
		System.out.println("INS 7");
		btree.insert(7);
		System.out.println(btree);
		System.out.println("DEL 0");
		btree.delete(0);
		System.out.println(btree);
		System.out.println("DEL 2");
		btree.delete(2);
		System.out.println(btree);
		System.out.println("DEL 8");
		btree.delete(8);
		System.out.println(btree);
		System.out.println("DEL 7");
		btree.delete(7);
//		btree.insert(14);
//		btree.insert(15);
//		btree.insert(16);
//		btree.insert(17);
//		btree.insert(18);
//		btree.insert(19);
//		btree.insert(20);
//		btree.insert(21);
//		btree.insert(22);
//		btree.insert(24);
//		btree.insert(23);
		
		System.out.println(btree);
//		System.out.println("root "+btree.getRoot());
	}
}