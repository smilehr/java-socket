package com.huarui.demo;

import com.huaui.test.TreeTest.TNode;

/**
 * 实现遍历树指定层的节点
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月3日
 */
public class Tree {

	/**
	 * 对传入的参数进行判断处理
	 * @param tree，传入的二叉树
	 * @param n，遍历二叉树的层数
	 */
	public static String treeLevel(TNode tree, int n) {
		if (tree == null) {
			System.out.println("输入的树为空！");
			return null;
		}
		if (n <= 0) {
			System.out.println("输入的层数不符合要求！");
			return null;
		}
		StringBuilder str = new StringBuilder();
		return treeLevel(tree, n, str).toString(); //递归遍历
	}

	/**
	 * 采用递归的方法，以层序遍历的方式，从左到右依次读取出第n层的所有节点;
	 * 如果n大于树的深度，则输出为空
	 * @param tree 传入的二叉树
	 * @param n，遍历二叉树的层数
	 * @param str,存储遍历的节点value
	 */
	private static StringBuilder treeLevel(TNode tree, int n, StringBuilder str) {
		if (tree == null) {
			return str;
		}
		if (n == 1) {
			str.append(tree.value);
		}
		else {
			str = treeLevel(tree.left, n - 1, str); //遍历左子树
			str = treeLevel(tree.right, n - 1, str); //遍历右子树
		}
		return str;
	}

}
