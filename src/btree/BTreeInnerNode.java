package btree;

import java.util.List;

public class BTreeInnerNode extends BTreeNode {
	
	public final static int NB_MAX_CHILDREN = 4;
	
	List<BTreeNode> children;
	List<Integer> keys;
	
	public BTreeInnerNode(List<BTreeNode> children) {
		this.children = children;
	}
	
	public boolean search(int key) {
		for(int i = 0; i < this.keys.size(); i++) {
			if (key < keys.get(i)) {
				this.children.get(i).search(key);
			}
		}
		return false;
	}

}