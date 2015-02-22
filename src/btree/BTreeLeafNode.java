package btree;

import java.util.List;

public class BTreeLeafNode extends BTreeNode {
	
	List<Integer> keys;
	
	public BTreeLeafNode(List<Integer> values) {
		this.keys = values;
	}

	public List<Integer> getKeys() {
		return keys;
	}

	public void setKeys(List<Integer> keys) {
		this.keys = keys;
	}
	
	public boolean search(int key) {
		return keys.contains(key);
	}
}