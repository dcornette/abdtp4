package btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreeLeafNode extends BTreeNode {
	
	public BTreeLeafNode(List<Integer> values, int order, BTreeNode parent) {
		this.keys = values;
		this.order = order;
		this.parent = parent;
	}
	
	public void addValue(int value) {
		if(!this.isFull()) {
			this.keys.add(value);
			Collections.sort(this.keys);
		} else {
			this.keys.add(value);
			Collections.sort(this.keys);
			
			// process complex insert
			// Creation nouvelle feuille
			List<Integer> initialListForNewLeafNode = new ArrayList<Integer>();
			for(int i=this.order+1; i<this.getKeys().size(); i++) {
				initialListForNewLeafNode.add(this.getKeys().get(i));
			}
			BTreeLeafNode newLeafNode = new BTreeLeafNode(initialListForNewLeafNode, order, this.getParent());
			
			// Ajout element + feuille dans noeud parent
			BTreeInnerNode newInnerNode;
			if(this.isRoot()) {
				List<BTreeNode> initialChildrenList = new ArrayList<BTreeNode>();
				initialChildrenList.add(this);
				initialChildrenList.add(newLeafNode);
				
				List<Integer> initialKeyList = new ArrayList<Integer>();
				initialKeyList.add(this.keys.get(this.order));
				
				newInnerNode = new BTreeInnerNode(initialChildrenList, initialKeyList, this.order, null);
			} else {
				newInnerNode = (BTreeInnerNode) this.parent;
				newInnerNode.addkey(this.keys.get(this.order), newLeafNode);
			}
			
			// Supression des clés dans la feuille
			for(int i=this.order; i<this.getKeys().size(); i++) {
				this.getKeys().remove(i);
			}			
			
			// Mise à jour des parents
			this.setParent(newInnerNode);
			newLeafNode.setParent(newInnerNode);
		}
	}
}