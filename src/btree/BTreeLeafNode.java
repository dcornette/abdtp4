package btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreeLeafNode extends BTreeNode {
	
	public BTreeLeafNode(List<Integer> values, int order, BTreeInnerNode parent) {
		this.keys = values;
		this.order = order;
		this.parent = parent;
	}
	
	public void addValue(int value) {
		// On ajoute la valeur et on tri
		this.keys.add(value);
		Collections.sort(this.keys);
		
		// Si la feuille était pleine, on l'éclate
		if(this.keys.size() > (2*this.order)) {
			// Creation nouvelle feuille
			List<Integer> initialListForNewLeafNode = new ArrayList<Integer>();
			for(int i=this.order; i<this.getKeys().size(); i++) {
				initialListForNewLeafNode.add(this.getKeys().get(i));
			}
			BTreeLeafNode newLeafNode = new BTreeLeafNode(initialListForNewLeafNode, order, this.getParent());
			
			// Ajout element + feuille dans noeud parent
			BTreeInnerNode newFatherNode;
			if(this.isRoot()) {
				List<BTreeNode> initialChildrenList = new ArrayList<BTreeNode>();
				initialChildrenList.add(this);
				initialChildrenList.add(newLeafNode);
				
				List<Integer> initialKeyList = new ArrayList<Integer>();
				initialKeyList.add(this.keys.get(this.order));
				
				newFatherNode = new BTreeInnerNode(initialChildrenList, initialKeyList, this.order, null);
			} else {
				newFatherNode = this.parent;
				newFatherNode.addkey(this.keys.get(this.order), newLeafNode);
			}
			
			// Supression des clés dans la feuille
			for(int i=this.order, j=this.getKeys().size(); i<j; i++) {
				this.getKeys().remove(this.order);
			}			
			
			// Mise à jour des parents
			this.setParent(newFatherNode);
			newLeafNode.setParent(newFatherNode);
		}
	}

	@Override
	public String toString() {
		String leafNode = "[ ";
		for(Integer key : this.keys) {
			leafNode += key + " ";
		}
		leafNode += "]";
		return leafNode;
	}
}