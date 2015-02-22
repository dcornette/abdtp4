package btree;

import java.util.ArrayList;
import java.util.List;

public class BTree {

	private BTreeNode root;
	private int order;
	
	public BTree(int order, int initialValue) {
		List<Integer> initialList = new ArrayList<Integer>();
		initialList.add(initialValue);
		this.root = new BTreeLeafNode(initialList);
		this.order = order;
	}
	
	/**
	 * Algorithme de recherche d'une clé dans l'arbre
	 * @param key
	 * @param node
	 * @return BTreeNode le noeud qui contient la clé ou null si la clé n'existe pas dans l'arbre
	 */
	public BTreeNode search(int key, BTreeNode node) {	
		if(node instanceof BTreeLeafNode) {
			if(node.search(key)) {
				return node;			
			} else {
				return null;		
			}
		}
		BTreeInnerNode innerNode = (BTreeInnerNode) node;
		return this.search(key, innerNode.getNodes().get(innerNode.getIndexByKey(key)));
	}
	
	public void insert(int value) {
		
	}
	
	public void delete(int value) {
		
	}
	
	public BTreeNode getRoot() {
		return root;
	}

	public void setRoot(BTreeNode root) {
		this.root = root;
	}

	public int getOrder() {
		return order;
	}
}