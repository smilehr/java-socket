import java.util.LinkedList;
import java.util.Queue;

import javax.xml.soap.Node;

/**
 * 功能：实现二叉树从左至友遍历指定层数的节点
 * 
 * @author Huarui
 * @createdate 20170627
 */
public class Tree {
	
	
	
	/**
	 * 
	 * @param tn,传入的空树
	 */
	public void InitTree(TNode tn){
		if(tn.value<50){
			TNode t1 = new TNode();
			TNode t2 = new TNode();
			t1.value = tn.value*2;
			t2.value = tn.value*2+1;
			tn.left = t1;
			tn.right = t2;
			InitTree(t1);
			InitTree(t2);
		}else{
			tn.left = null;
			tn.right = null;
		}
	}
	
	/**
	 * 功能：以层序遍历的方式，从左到右依次读取出第n层的所有节点
	 * @param tree
	 * @param n
	 */
	public static void TreeLevel(TNode tree, int n){
		
		if(tree == null || n <= 0) return;
		if(n == 1){
			System.out.print(tree.value + " ");
		}else{
			TreeLevel(tree.left,n-1);	//遍历左子树
			TreeLevel(tree.right,n-1);	//遍历右子树
		}
	}
}
