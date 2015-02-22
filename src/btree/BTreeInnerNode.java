package btree;

import java.util.List;

public class BTreeInnerNode extends BTreeNode {
	
	List<BTreeNode> children;
	List<Integer> keys;
	
	public BTreeInnerNode(List<BTreeNode> children, List<Integer> keys) {
		this.children = children;
		this.keys = keys;
	}

	public List<BTreeNode> getNodes() {
		return children;
	}

	public void setNodes(List<BTreeNode> nodes) {
		this.children = nodes;
	}
	
	public boolean search(int key) {
		return this.keys.contains(key);
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
}