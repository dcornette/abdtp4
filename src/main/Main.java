package main;

import btree.BTree;
import btree.DuplicateValueException;

public class Main {
	
	public static void main(String args[]) throws DuplicateValueException {
		BTree btree = new BTree(2, 0);
		
		btree.insert(12, btree.getRoot());
		btree.insert(6, btree.getRoot());
		btree.insert(8, btree.getRoot());
		btree.insert(4, btree.getRoot());
		btree.insert(13, btree.getRoot());
		btree.insert(2, btree.getRoot());
		btree.insert(5, btree.getRoot());
		btree.insert(3, btree.getRoot());
		btree.insert(14, btree.getRoot());
		btree.insert(15, btree.getRoot());
		btree.insert(16, btree.getRoot());
		btree.insert(17, btree.getRoot());
		btree.insert(18, btree.getRoot());
		btree.insert(19, btree.getRoot());
		btree.insert(20, btree.getRoot());
		btree.insert(21, btree.getRoot());
		
		System.out.println("test : "+btree.search(12, btree.getRoot()));
		System.out.println("root "+btree.getRoot());
	}
}