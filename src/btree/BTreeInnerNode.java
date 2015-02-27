package btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BTreeInnerNode extends BTreeNode {
	
	private List<BTreeNode> children;
	
	public BTreeInnerNode(List<BTreeNode> children, List<Integer> keys, int order, BTreeNode parent) {
		this.children = children;
		this.keys = keys;
		this.order = order;
		
		for(BTreeNode child : children) {
			child.setParent(this);
		}
	}
	
	/**
	 * Donne la position d'une clé dans le noeud
	 * @param key
	 * @return
	 */
	public int getIndexByKey(int key) {
		if(this.search(key)) {
			return (this.keys.indexOf(key) + 1);
		} 
		for(int i=0; i<this.keys.size(); i++) {
			if(key < this.keys.get(i)) {
				return i;
			}
		}
		return (this.children.size() - 1);
	}
	
	public void addkey(int key, BTreeNode child) {
		// On ajoute la clé + l'enfant et on tri
		this.keys.add(key);
		this.children.add(child);
		Collections.sort(this.keys);		
		Collections.sort(this.children, new Comparator<BTreeNode>() {
			public int compare(BTreeNode child1, BTreeNode child2) {
				if(child1.getKeys().get(0) < child2.getKeys().get(0)) {
					return -1;
				}
				if(child1.getKeys().get(0) > child2.getKeys().get(0)) {
					return 1;
				}
				return 0;
			}
		});
		
		// Si le noeud était plein, on l'éclate
		if(this.keys.size() > (2*this.order)) {
			// Creation nouveau noeud
			List<Integer> initialKeysForNewInnerNode = new ArrayList<Integer>();
			for(int i=this.order+1; i<this.getKeys().size(); i++) {
				initialKeysForNewInnerNode.add(this.getKeys().get(i));
			}
			
			List<BTreeNode> initialChildrenForNewInnerNode = new ArrayList<BTreeNode>();
			for(int i=this.order+1; i<this.getChildren().size(); i++) {
				initialChildrenForNewInnerNode.add(this.getChildren().get(i));
			}
			
			BTreeInnerNode newInnerNode = new BTreeInnerNode(
					initialChildrenForNewInnerNode, initialKeysForNewInnerNode, order, this.getParent());
			
			// Ajout element + noeud dans noeud parent
			BTreeInnerNode newFatherNode;
			if(this.isRoot()) {
				List<BTreeNode> initialChildrenList = new ArrayList<BTreeNode>();
				initialChildrenList.add(this);
				initialChildrenList.add(newInnerNode);
				
				List<Integer> initialKeyList = new ArrayList<Integer>();
				initialKeyList.add(this.keys.get(this.order));
				
				newFatherNode = new BTreeInnerNode(initialChildrenList, initialKeyList, this.order, null);
			} else {
				newFatherNode = this.parent;
				newFatherNode.addkey(this.keys.get(this.order), newInnerNode);
			}
			
			// Supression des clés dans le noeud + les enfants
			for(int i=this.order, j=this.getKeys().size(); i<j; i++) {
				this.getKeys().remove(this.order);
			}	
			for(int i=this.order+1, j=this.getChildren().size(); i<j; i++) {
				this.getChildren().remove(this.order+1);
			}
		}
	}
	
	@Override
	public String toString() {
		String innerNode = "[ ";
		for(Integer key : this.keys) {
			innerNode += key + " ";
		}
		innerNode += "]";
		return innerNode;
	}
	
	public List<BTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<BTreeNode> nodes) {
		this.children = nodes;
	}
}