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
		}
	}

	public void removeValue(int value) {
		BTreeNode brotherL;
		BTreeNode brotherR;
		
		//Si pas de soucis on supprime et c'est fini
		this.keys.remove(this.keys.indexOf(value));
		Collections.sort(this.keys);
	
		//Si on a plus assez de valeur il va falloir fusionner
		if(this.keys.size() < (this.order)) {
			System.out.println("fusion");
			int currentLeafIndex = this.getParent().getChildren().indexOf(this);
			
			//Récuperation du frère droit si possible
			if(currentLeafIndex < this.getParent().getChildren().size()-1)
				brotherR = this.getParent().getChildren().get(currentLeafIndex+1);
			else
				brotherR = null;
			
			//Récuperation du frère gauche si possible
			if(currentLeafIndex > 0)
				brotherL = this.getParent().getChildren().get(currentLeafIndex-1);
			else
				brotherL = null;
				
			//fusion selon les cas
			if(brotherR != null && brotherR.keys.size() > this.order) {
				this.fusionR(brotherR);
			}else if(brotherR != null && brotherR.keys.size() <= 2*this.order){
				this.fusionR2(brotherR);
			}else if(brotherL != null && brotherL.keys.size() > this.order){
				this.fusionL(brotherL);
			}else{
				this.fusionL2(brotherL);
			}
		}
	}
	
	public void fusionR(BTreeNode brother){
		int index = 0;
		int oldBrotherFirst = brother.getKeys().get(0);
		while(this.keys.size() < this.order){
			
			this.keys.add(brother.getKeys().get(index));
			brother.getKeys().remove(index);
			
			int newIndex = this.parent.getKeys().indexOf(oldBrotherFirst);
			this.parent.getKeys().set(newIndex,brother.getKeys().get(0));
			index++;
		}
		Collections.sort(this.keys);
	}
	
	public void fusionR2(BTreeNode brother){

		int oldBrotherFirst = brother.getKeys().get(0);
		int removeIndex = this.parent.getKeys().indexOf(oldBrotherFirst);
		
		this.keys.addAll(brother.getKeys());
		brother.getKeys().clear();
		
		this.parent.getChildren().remove(brother);
		this.parent.getKeys().remove(removeIndex);
		
		Collections.sort(this.keys);
	}
	
	public void fusionL(BTreeNode brother){

		int oldBrotherFirst = brother.getKeys().get(brother.getKeys().size()-1);
		int index = brother.getKeys().size()-1;
		while(brother.getKeys().size() > this.order){
			this.keys.add(brother.getKeys().get(index));
			brother.getKeys().remove(index);
			Collections.sort(this.keys);
			
			int newIndex = this.getKeys().indexOf(oldBrotherFirst);
			this.parent.getKeys().set(newIndex,this.getKeys().get(0));
			index--;
		}
	}
	
	public void fusionL2(BTreeNode brother){
	
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