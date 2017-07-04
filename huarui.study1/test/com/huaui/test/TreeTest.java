package com.huaui.test;

import org.junit.Test;

import com.huarui.demo.Tree;

import junit.framework.TestCase;

/**
 * 测试遍历二叉树的指定层数的节点
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月3日
 */
public class TreeTest extends TestCase {

	/**
	 * 该类定义二叉树的结构
	 * @param value,节点值
	 * @param left,左子树
	 * @param right,右子树
	 */
	public class TNode {
		public int value;

		public TNode left;

		public TNode right;
	}

	/**
	 * 功能：初始化树的结构
	 * @param tn,传入的空树
	 */
	public void InitTree(TNode tn) {
		if (tn.value < 20) {
			TNode t1 = new TNode();
			TNode t2 = new TNode();

			t1.value = tn.value * 2;
			tn.left = t1;
			InitTree(t1);

			t2.value = tn.value * 2 + 1;
			tn.right = t2;
			InitTree(t2);
		}
		else {
			tn.left = null;
			tn.right = null;
		}
	}
	
	/**
	 * 测试树的遍历
	 * @param tree,测试用生成的树,结构为
	 * <pre>
	 * 						 			1
	 * 				      		2					  3
	 * 			     4	              5 		6   	    7
	 * 	 	    8          9      10    11   12    13    14    15
	 * 		16    17   18    19 20 21 22 23 24 25 26 27 28 29 30 31
	 *    32 33 34 35 36 37 38 39
	 * </pre>
	 */
	@Test
	public void testTreeLevel() {
		TNode tree = new TNode();
		tree.value = 1;
		InitTree(tree);
		TNode tree1 = null;
		//树不为空且层数大于0小于树的高度
		assertEquals("23", Tree.treeLevel(tree, 2));
		
		//树不为空且层数大于0小于树的高度
		assertEquals("1", Tree.treeLevel(tree, 1));
		
		//树不为空且层数等于树的高度
		assertEquals("3233343536373839", Tree.treeLevel(tree, 6));
		
		//树不为空，层数为0
		assertEquals(null, Tree.treeLevel(tree, 0));
		
		//树不为空且层数大于树的高度
		assertEquals("", Tree.treeLevel(tree, 8));
		
		//树为空
		assertEquals(null, Tree.treeLevel(tree1, 3));
	}

}
