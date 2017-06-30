
/**
 * 完成培训第二题
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author HuaRui
 * @createdate 2017年6月29日
 */
import java.util.Random;

/**
 * 该类定义二叉树的结构
 */
class TNode {
	int value;

	TNode left;

	TNode right;
}

/**
 * 该类实现二叉树从左至右遍历指定层数的节点
 */
public class Tree {

	/**
	 * 功能：初始化树的结构
	 * @param tn,传入的空树
	 * @param r,随机判断是否生成子树
	 */
	public void InitTree(TNode tn) {
		if (tn.value < 50) {
			TNode t1 = new TNode();
			TNode t2 = new TNode();
			Random r = new Random();
			if (r.nextInt(2) == 1) {
				t1.value = tn.value * 2;
				tn.left = t1;
				InitTree(t1);
			}
			else {
				tn.left = null;
			}
			if (r.nextInt(2) == 1) {
				t2.value = tn.value * 2 + 1;
				tn.right = t2;
				InitTree(t2);
			}
			else {
				tn.right = null;
			}
		}
		else {
			tn.left = null;
			tn.right = null;
		}
	}

	/**
	 * 获取二叉树的深度
	 * @param left
	 * @param right
	 * @return
	 */
	public static int getDgree(TNode tree) {
		if (tree == null) {
			return 0;
		}
		int left = getDgree(tree.left);
		int right = getDgree(tree.right);
		return 1 + Math.max(left, right);
	}

	/**
	 * 对传入的参数进行判断处理
	 * @param tree
	 * @param n
	 */
	public static void treeLevel(TNode tree, int n) {
		if (tree == null) {
			System.out.println("输入的树为空！");
			return;
		}
		int degree = getDgree(tree);
		if (n <= 0 || n > degree) {
			System.out.println("输入的层数不符合要求！");
			return;
		}
		treeLevel1(tree, n);
	}

	/**
	 * 采用递归的方法，以层序遍历的方式，从左到右依次读取出第n层的所有节点
	 * @param tree 传入的二叉树
	 * @param n，遍历二叉树的层数
	 */
	public static void treeLevel1(TNode tree, int n) {
		if (tree == null) {
			return;
		}
		if (n == 1) {
			System.out.print(tree.value + " ");
		}
		else {
			treeLevel1(tree.left, n - 1); //遍历左子树
			treeLevel1(tree.right, n - 1); //遍历右子树
		}
	}

}
