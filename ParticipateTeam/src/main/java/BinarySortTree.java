import java.util.ArrayList;

public class BinarySortTree {
    Node root;
    public Node getRoot() {
        return root;
    }
    //查找给定节点是否在该二叉树排序上
    public boolean contains(int value) {
        if(root == null) {
            return false;
        } else {
            return root.contains(value);
        }
    }
    //查找结点
    public Node search(int value) {
        if(root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }
    //查找父节点
    public Node searchParent(int value) {
        if(root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }
    //删除节点
    public void delNode(int value) {
        if(root == null) {
            return;
        } else { //找到要删除的节点
            Node targetNode = search(value);
            //如果没有找到要删除的节点就返回
            if(targetNode == null) {
                return;
            }
            //如果这个二叉排序树只有一个节点
            if(root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去找要删除节点的父节点
            Node parent = searchParent(value);
            //判断删除的是叶子节点
            if(targetNode.left == null && targetNode.right == null) {
                //判断删除的节点是父节点的左子节点还是右子节点
                if(parent.left != null && parent.left.teamID == value) { //是左子节点
                    parent.left = null;
                } else if(parent.right != null && parent.right.teamID == value) {
                    parent.right = null;
                }
            } else if(targetNode.left != null && targetNode.right != null) { //删除有两颗子树的节点
                //从targetNode的右子树找到最小的节点，用临时变量保存该最小节点的值并删除，最后再吧临时变量的值赋给该节点的值
                int minValue = delRightMin(targetNode.right);
                targetNode.teamID = minValue;

            } else { //删除只有一颗子树的节点
                if(targetNode.left != null) { //若删除的节点有左子节点
                    //这里需要判断下父亲节点是否为空，如果不判断会出现空指针异常。因为如果二叉排序树只有根节点和它的一个左子节点，
                    // 刚好要删除这个根节点，由于它没有父亲节点，所以返回的是null
                    if(parent != null) {
                        if(parent.left.teamID == value) { //targetNode是parent的左子节点
                            parent.left = targetNode.left;
                        } else { ///targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { //删除的节点有右子节点
                    if(parent != null) { //同理也需要判段父亲节点是否为空
                        if(parent.left.teamID == value) { //targetNode是parent的左子节点
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }

    }

    public int delRightMin(Node node) {
        Node target = node;
        while(target.left != null) {
            target = target.left;
        }
        //退出while循环时，target就指向了最小节点;
        delNode(target.teamID);
        return target.teamID;
    }

    public int delLeftMax (Node node) {
        Node target = node;
        while(target.right != null) {
            target = target.right;
        }
        //退出while循环时，target就指向了最大节点
        delNode(target.teamID);
        return target.teamID;
    }
    //中序遍历BST
    public void infixOrder() {
        if(root != null) {
            root.infixOrder();
        } else {
            System.out.println("空树，无法遍历");
        }
    }
    //前序遍历该树
    public void preOrder() {
        if(root != null) {
            root.preOrder();
        } else {
            System.out.println("null");
        }
    }
    //添加节点
    public void add(Node node) {
        if(root == null) {
            root = node; //如果root为空，令其直接指向node
        } else {
            root.add(node);
        }
    }


}