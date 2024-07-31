public class Node {
    static int times = 0;

    int teamID;
    Team team;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int teamID) {
        this.teamID = teamID;
    }

    public Node(int id,Team team){
        this.teamID = id;
        this.team = team;
    }
    //查找要删除的结点

    public void setTeamName(int id){
        this.teamID = id;
    }

    public int getTeamID(){
        return this.teamID;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getTimes(){
        return times;
    }


    public Node search(int teamID) {
        if(teamID == this.teamID) { //找到直接返回
            return this;
        } else if(teamID < this.teamID) { //如果查找的结点的值小于当前结点，向左子树递归查询
            if(this.left == null) {
                //如果左子节点为空则返回null
                return null;
            }
            times++;
            return this.left.search(teamID);
        } else {
            if(this.right == null) {
                return null;
            }
            times++;
            return this.right.search(teamID);
        }
    }
    //查找要删除结点的父节点

    public Node searchParent(int teamID) {
        //如果当前结点就是要查找结点的父节点，就返回
        if((this.left != null && this.left.teamID == teamID) ||
                (this.right != null && this.right.teamID == teamID)) {
            return this;
        } else if(teamID < this.teamID && this.left != null) { //如果要查找的结点的值小于当前节点的值且当前结点的左子节点不为空
            return this.left.searchParent(teamID); //向左子树递归查找
        } else if(teamID > this.teamID && this.right != null) {
            return this.right.searchParent(teamID);
        } else {
            return null; //没有找到父节点
        }
    }

    //添加节点[递归的方式添加结点，要满足二叉排序树的规则]
    public void add(Node node) {
        if(node == null) {
            return;
        }
        //判断传入结点的值和当前子树的根节点的值的关系
        if(node.teamID < this.teamID) {
            //如果当前结点的左子节点为null，则将node添加到当前结点的左子节点上
            if(this.left == null) {
                this.left = node;
            } else { //递归向左子树添加
                this.left.add(node);
            }
        } else { //传入结点的值大于等于当前子树的根节点的值
            if(this.right == null) {
                this.right = node;
            } else { //递归向右子树添加
                this.right.add(node);
            }
        }
    }
    //中序遍历该BST树
    public void infixOrder() {
        if(this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null) {
            this.right.infixOrder();
        }
    }
    //前序遍历该树
    public void preOrder() {
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }

    public boolean contains(int teamID) {
        if(this.teamID == teamID) {
            return true;
        } else if(teamID < this.teamID) {
            if(this.left ==null) {
                return false;
            } else {
                return this.left.contains(teamID);
            }
        } else {
            if(this.right == null) {
                return false;
            } else {
                return this.right.contains(teamID);
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "teamID = " + teamID +
                '}';
    }

    public int BST_ASL(Node node, int weight)
    {
        weight++;
        int a = weight;
        if (node.left!=null)
            weight += BST_ASL(node.left, a);
        if (node.right!=null)
            weight += BST_ASL(node.right, a);
        return weight;
    }

}