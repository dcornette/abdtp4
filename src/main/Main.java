package main;

import btree.BTree;

public class Main {
	
	public static void main(String args[]){
		BTree btree = new BTree(2, 0);
		
		System.out.println("test : "+btree.search(10, btree.getRoot()));
	}
}