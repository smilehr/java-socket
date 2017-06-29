import java.io.File;
import java.util.Scanner;

/**
 * 功能：对三个题目进行测试
 * @author Huarui
 *@createtime 20170627 
 * */

	//树结构类
	class TNode{
		int value;
		TNode left;
		TNode right;
	}
public class Test {
	
	public static void main(String[] args){
		
		//测试第一题：文件读取，并转换成数组
		File_input fi = new File_input();
		File fobj = new File("D:\\text.txt");
		if(fobj.exists()){
			if(fobj.length()> Integer.MAX_VALUE){
				System.out.println("该文件太大，读取失败，请确定读取文件大小！");
			}else{
				byte[] bt = fi.file2buf(fobj);
				System.out.println(bt.length);	
			}
		}else{
			System.out.println("文件不存在！");
		}
		
		
		//测试第二题：十进制转换成十六进制并以字符串的形式输出
		InttoOther intto = new InttoOther();
		System.out.print("请输入要转换的十进制数：");
		Scanner sc = new Scanner(System.in);
		int obj = sc.nextInt();
		StringBuilder hex = new StringBuilder();
		hex = intto.IntToHex(obj);
		System.out.println(hex);
		
		//测试第三题：遍历二叉树指定层的节点
		Tree tr = new Tree();
		TNode tn = new TNode();
		int value;
		System.out.print("请输入树根节点数值：");
		Scanner sc1 =new Scanner(System.in);
		value = sc1.nextInt();
		tn.value = value;
		tr.InitTree(tn);
		System.out.print("请输入要查询节点的所在层数：");
		Scanner sc2 =new Scanner(System.in);
		int n = sc2.nextInt();
		tr.TreeLevel(tn, n);
	}
}
