package btree;

import java.util.ArrayList;
import java.util.List;

public class BTree {

	private BTreeNode root;
	private int order;
	private int depthMax;
	
	public BTree(int order, int depthMax, int initialValue) {
		List<Integer> initialList = new ArrayList<Integer>();
		initialList.add(initialValue);
		this.root = new BTreeLeafNode(initialList, order, null);
		this.order = order;
		this.depthMax = depthMax;
	}

	/**
	 * Algorithme de recherche d'une clé dans l'arbre
	 * @param key
	 * @param node
	 * @return BTreeNode le noeud qui contient la clé ou null si la clé n'existe pas dans l'arbre
	 */
	public BTreeNode search(int key, BTreeNode node) {	
		if(node instanceof BTreeLeafNode) {
			if(node.search(key)) {
				return node;			
			} else {
				return null;		
			}
		}
		BTreeInnerNode innerNode = (BTreeInnerNode) node;
		return this.search(key, innerNode.getChildren().get(innerNode.getIndexByKey(key)));
	}
	
	/**
	 * Algorithme d'insertion d'une clé dans l'abre
	 * @param value
	 * @param node
	 * @throws DuplicateValueException
	 */
	public void insertFromNode(int value, BTreeNode node) throws DuplicateValueException {
		if(node instanceof BTreeLeafNode) {
			BTreeLeafNode leafNode = (BTreeLeafNode) node;
			if(leafNode.search(value)) {
				throw new DuplicateValueException();
			}
			leafNode.addValue(value);
			this.updateRoot();
		} else {
			BTreeInnerNode innerNode = (BTreeInnerNode) node;
			this.insertFromNode(value, innerNode.getChildren().get(innerNode.getIndexByKey(value)));
		}
	}
	
	public void insert(int value) throws DuplicateValueException {
		this.insertFromNode(value, this.root);
	}
	
	public void delete(int value) {
		
	}
	
	/**
	 * Mise à jour de la racine
	 */
	public void updateRoot() {
		while(this.root.getParent() != null) {
			this.setRoot(this.root.getParent());
		}
	}
	
	@Override
	public String toString() {
		
		String[] tree = new String[this.getDepthMax()];
		String[] lastParent = new String[this.getDepthMax()];
		String result = new String();
		
		// On récupére la liste des noeuds
		List<BTreeNode> nodes = this.getListNodes();
		
		// On initialise tree avec des strings vides
		for(int i = 0; i < tree.length; i++) {
			tree[i] = "";
		}
		
		// Rangement des noeuds par profondeur
		for(BTreeNode node : nodes) {
			System.out.println(node.getParent());
			if(!node.isRoot()) {
				if (lastParent[node.getDepth()-1] != null && !lastParent[node.getDepth()-1].equals(node.getParent().toString())) {
					tree[node.getDepth()-1] += " ----> ";
				}
				lastParent[node.getDepth()-1] = node.getParent().toString();
			}
			
			tree[node.getDepth()-1] += node.toString();
			
			
		}
			
		// Affichage
		for(int i = 0; i < this.getDepthMax(); i++) {
			if (tree[i] != null)
				result += tree[i] + "\n";
		}
		
		return result;
	}
	
	/**
	 * Retourne la liste des noeuds de l'arbre.
	 * @return listNodes
	 */
	public List<BTreeNode> getListNodes() {
		List<BTreeNode> nodes = new ArrayList<BTreeNode>();
		BTreeNode current;
		int cpt = 0;
		
		nodes.add(this.root);
		
		while(cpt < nodes.size()) {
			current = nodes.get(cpt);
			if(current instanceof BTreeInnerNode) {
				for(BTreeNode child : ((BTreeInnerNode)current).getChildren()) {
					nodes.add(child);
				}
			}
			cpt++;
		}
		
		return nodes;
	}

	public BTreeNode getRoot() {
		return root;
	}

	public void setRoot(BTreeNode root) {
		this.root = root;
	}

	public int getOrder() {
		return order;
	}
	
	public int getDepthMax() {
		return depthMax;
	}

	public void setDepthMax(int depthMax) {
		this.depthMax = depthMax;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}