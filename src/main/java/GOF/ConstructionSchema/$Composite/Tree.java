package GOF.ConstructionSchema.$Composite;

/**
 * @Auther: KAM1996
 * @Date: 17:10 2018-11-07
 * @Description: 树
 * @Version: 1.0
 */
public class Tree {

    TreeNode root = null;

    public Tree(String name){
        root = new TreeNode(name);
    }

    public static void main(String[] args) {
        Tree tree = new Tree("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");
        nodeB.add(nodeC);
        tree.root.add(nodeB);
        System.out.println("build the tree finish!");
        System.out.println(tree.root);
    }
}
