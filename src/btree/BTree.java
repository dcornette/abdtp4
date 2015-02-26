package btree;

import java.util.ArrayList;
import java.util.List;

public class BTree {

	private BTreeNode root;
	private int order;
	
	public BTree(int order, int initialValue) {
		List<Integer> initialList = new ArrayList<Integer>();
		initialList.add(initialValue);
		this.root = new BTreeLeafNode(initialList, order, null);
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
		return this.search(key, innerNode.getChildren().get(innerNode.getIndexByKey(key)));
	}
	
	/**
	 * Algorithme d'insertion d'une clé dans l'abre
	 * @param value
	 * @param node
	 * @throws DuplicateValueException
	 */
	public void insertFromNode(int value, BTreeNode node) throws DuplicateValueException {
		if(node instanceof BTreeLeafNode) {
			BTreeLeafNode leafNode = (BTreeLeafNode) node;
			if(leafNode.search(value)) {
				throw new DuplicateValueException();
			}
			leafNode.addValue(value);
			this.updateRoot();
		} else {
			BTreeInnerNode innerNode = (BTreeInnerNode) node;
			this.insertFromNode(value, innerNode.getChildren().get(innerNode.getIndexByKey(value)));
		}
	}
	
	public void insert(int value) throws DuplicateValueException {
		this.insertFromNode(value, this.root);
	}
	
	public void delete(int value) {
		
	}
	
	/**
	 * Mise à jour de la racine
	 */
	public void updateRoot() {
		while(this.root.getParent() != null) {
			this.setRoot(this.root.getParent());
		}
	}
	
	@Override
	public String toString() {
		boolean flag = false;
		BTreeNode current = this.root;
		List<BTreeNode> nodes = new ArrayList<BTreeNode>();
		
		nodes.add(current);
		nodes.add(new BTreeLeafNode(null,-1,null));
		String tree = "";
		
		while(!nodes.isEmpty()) {
			if(current.getOrder() == -1) {
				flag = true;
				nodes.remove(0);
				if(!nodes.isEmpty()) {
					current = nodes.get(0);		
				}
			} else {
				if(flag) {
					tree += "\n";
					flag = false;
				}
				tree += current;
				if(current instanceof BTreeInnerNode) {
					BTreeInnerNode currentInner = (BTreeInnerNode) current;
					for(BTreeNode child : currentInner.getChildren()) {
						nodes.add(child);
					}
					nodes.add(new BTreeLeafNode(null,-1,null));
				}
				nodes.remove(current);
				if(!nodes.isEmpty()) {
					current = nodes.get(0);		
				}				
			}
		}
		
		return tree;
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