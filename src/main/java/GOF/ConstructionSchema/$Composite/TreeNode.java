package GOF.ConstructionSchema.$Composite;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: KAM1996
 * @Date: 16:56 2018-11-07
 * @Description: 树节点
 * @Version: 1.0
 */

/*
 组合模式，又叫 部分-整体 模式,在处理类似树形结构的问题时比较方便
 */
@Data
public class TreeNode {
    private String name;
    private TreeNode parent;
    private List<TreeNode> childrens = new ArrayList<>();

    public TreeNode(String name) {
        this.name = name;
    }

    //添加孩子的节点
    public void add(TreeNode node){
        childrens.add(node);
    }

    //删除孩子的节点
    public void remove(TreeNode node){
        childrens.remove(node);
    }

    //取得孩子的节点
    public Iterator<TreeNode> getChilds(){
        return childrens.iterator();
    }
}
