package main;

import btree.BTree;
import btree.DuplicateValueException;

public class Main {
	
	public static void main(String args[]) throws DuplicateValueException {
		int[] values = {12, 6, 8, 4, 13, 2, 5, 3, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 23, 25, 26};
		int[] valuesDel = {13, 5, 4, 0, 2, 8};
		BTree btree = new BTree(2,5,0);
		
		for(int v : values) {
			System.out.println("add " + v);
			btree.insert(v);	
			System.out.println(btree);
		}
		
		for(int v : valuesDel) {
			System.out.println("del " + v);
			btree.delete(v);	
			System.out.println(btree);
		}
	}
}