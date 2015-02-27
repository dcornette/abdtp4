package btree;

import java.util.List;

public abstract class BTreeNode {
	
	protected List<Integer> keys;
	protected int order;
	protected BTreeInnerNode parent;
	
	public boolean search(int key) {
		return keys.contains(key);
	}
	
	public boolean isFull() {
		return this.keys.size() >= (2*this.order);
	}

	public boolean isRoot() {
		return this.parent == null;
	}
	
	public List<Integer> getKeys() {
		return keys;
	}

	public void setKeys(List<Integer> keys) {
		this.keys = keys;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public BTreeInnerNode getParent() {
		return parent;
	}

	public void setParent(BTreeInnerNode parent) {
		this.parent = parent;
	}
	
	public int getDepth() {
		BTreeNode current = this;
		int depth = 1;
		while(current.getParent() != null) {
			current = current.getParent();
			depth++;
		}
		return depth;
	}
}