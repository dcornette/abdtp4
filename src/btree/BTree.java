package btree;

public class BTree {

	BTreeNode root;
	
	public BTree() {
		root = null;
	}
	
	public BTreeNode search() {
		return null;
	}
	
	public void insert(int v) {
		if (root == null)
			root = new BTreeLeafNode(v);
	}
	
}
