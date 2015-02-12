package btree;

import java.util.ArrayList;
import java.util.List;

public class BTreeLeafNode extends BTreeNode {
	
	List<Integer> keys;
	
	public BTreeLeafNode(int v) {
		this.keys = new ArrayList<Integer>();
		this.keys.add(v);
	}
	
	public boolean search(int key) {
		return keys.contains(key);
	}

}
