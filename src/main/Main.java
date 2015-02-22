package main;

import btree.BTree;

public class Main {
	
	public static void main(String args[]){
		BTree btree = new BTree(2, 0);
		btree.search(5, btree.getRoot());
	}
}