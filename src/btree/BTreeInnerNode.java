package btree;

import java.util.List;

public class BTreeInnerNode extends BTreeNode {
	
	private List<BTreeNode> children;
	
	public BTreeInnerNode(List<BTreeNode> children, List<Integer> keys, int order, BTreeNode parent) {
		this.children = children;
		this.keys = keys;
		this.order = order;
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
		// TODO
		
//		this.keys.add(value);
//		this.keys.sort(new Comparator<Integer>() {
//			public int compare(Integer key1, Integer key2) {
//				if(key1 < key2) {
//					return -1;
//				}
//				if(key1 > key2) {
//					return 1;
//				}
//				return 0;
//			}
//		});
//		Collections.sort(this.keys);
	}
	
	public List<BTreeNode> getNodes() {
		return children;
	}

	public void setNodes(List<BTreeNode> nodes) {
		this.children = nodes;
	}
}