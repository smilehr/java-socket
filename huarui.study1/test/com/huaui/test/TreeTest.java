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
	 * @param r,随机判断是否生成子树
	 */
	public void InitTree(TNode tn) {
		if (tn.value < 50) {
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

	@Test
	public void testTreeLevel() {
		TNode tree = new TNode();
		tree.value = 1;
		InitTree(tree);
		StringBuilder str = new StringBuilder();
		assertEquals("23", Tree.treeLevel(tree, 2, str).toString());
		assertEquals("4567", Tree.treeLevel(tree, 3, str).toString());
	}

}
