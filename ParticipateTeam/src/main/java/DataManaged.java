import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DataManaged {

    static int times = 0;
    static String filePath = "D://IDEA/Code/ParticipateTeam/team.txt";     //文件路径
    static String imagePath = "D://IDEA/Code/ParticipateTeam/map.jpg";     //图片路径
    static int eventNum = 31;   //赛事类别 目前一共有31个
    static String[] eventName = {"大数据实践", "信息图形设计", "动态信息影像（MG动画）", "交互信息设计",
                          "数据可视化","人工智能实践赛","Web应用与开发","管理信息系统","算法设计与应用",
                          "移动应用开发","移动应用开发（非游戏类）", "医药卫生","数字生活","运动健身",
                          "城市管理","行业应用","动画", "纪录片", "数字短片","微电影","新媒体漫画","环境设计",
                          "平面设计","产品设计", "交互媒体设计","游戏设计","虚拟现实VR与增强现实AR","汉语言文学",
                          "计算机基础与应用类课程微课", "虚拟实验平台","中、小学数学或自然科学课程微课"};

    static public void treeStorage(BinarySortTree bTree,ArrayList<Team> teams){     //将元素存入二叉树
        for (Team team : teams) {
            Node node = new Node(team.getTeamID(),team);
            bTree.add(node);
        }
    }

    static public ArrayList<Team> getTeamData(ArrayList<Team> teams,String content,int type){
        ArrayList<Team> teamList = new ArrayList<>();
        boolean isFind = false;
        for (Team team : teams) {
            switch (type){
                case 1:     //1 为学校名称
                    if (team.getUniversity().equals(content)){
                        teamList.add(team);
                        isFind = true;
                    }
                    break;
                case 2:    //2 为赛事类别
                    if (team.getEventCate().equals(content)){
                        teamList.add(team);
                        isFind = true;
                    }
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
        if (isFind == false){
            System.out.println("无信息匹配");
            return null;
        }
        return teamList;
    }

    static public void callSystem(ArrayList<Team> teams){  //叫号系统
        System.out.println("匹配到队伍信息");
        System.out.println("作品ID\t\t参赛作品\t\t参赛作者\t\t参赛类别");
        System.out.println("*******************************************");
        System.out.println("请各位选手，按以下顺序进入场第" + 1 + "个决赛室");     //31个赛事类别 9个决赛室
        System.out.println("*******************************************");
        System.out.println("---------------------------------------");
        int cir = 1;
        for (int i=0;i<eventName.length;i++) {
            if ((i/4+1)!=cir){
                cir = i/4+1;
                System.out.println("*******************************************");
                System.out.println("请各位选手，按以下顺序进入场第" + cir + "个决赛室");
                System.out.println("*******************************************");
            }
            String event = eventName[i];
            ArrayList<Team> teamList = getTeamData(teams,event,2);
            Queue<Team> q1 = new PriorityQueue<Team>(com);
            for (Team team : teamList) {
                q1.add(team);       //其中按照ID大小 从小到大 按顺序排列
            }
            System.out.println("");
            while (!q1.isEmpty()){
                Team team = q1.poll();
                System.out.println(team.getTeamID()+"--"+team.getWorkName() + "--"
                        + team.getParticipant() + "--" +team.getEventCate());
                try {
                    Thread.sleep(500);
                    System.out.println("(等待该小组完成)");
                } catch (Exception e) {
                    System.out.println("Error");;
                }
                System.out.println("");
            }
            System.out.println("--------------------------------------");
        }
    }

    static Comparator<Team> com = new Comparator<Team>() {
        @Override
        public int compare(Team o1, Team o2) {
            return o1.teamID-o2.teamID;         //按照 团队ID 进行排列 升序
        }
    };

    static public double log(double value,double base){
        return Math.log(value)/Math.log(base);
    }

    static public void printMenu_0(){
        System.out.println("********************************************************");
        System.out.println("*                                                      *");
        System.out.println("*                     赛事管理系统                        *");
        System.out.println("*                  输入序号进行相应操作                     *");
        System.out.println("*            1.管理队伍信息（添加、修改、删除）               *");
        System.out.println("*            2.查询队伍信息（根据队伍ID查找）                *");
        System.out.println("*            3.根据参赛学校查找队伍（有序输出）               *");
        System.out.println("*            4.决赛室叫号系统（9个决赛室）                   *");
        System.out.println("*            5.校园导航系统管理（两地距离最短路线）            *");
        System.out.println("*            0.结束系统管理系统（关闭程序）                  *");
        System.out.println("*                                                      *");
        System.out.println("********************************************************");
    }
    static public void printMenu_1(){
        System.out.println("********************************************************");
        System.out.println("*                                                      *");
        System.out.println("*                      管理队伍信息                      *");
        System.out.println("*                   输入序号进行相应操作                   *");
        System.out.println("*                     1.查看队伍信息                     *");
        System.out.println("*                     2.添加队伍信息                     *");
        System.out.println("*                     3.删除队伍信息                     *");
        System.out.println("*                     0.返回上个界面                     *");
        System.out.println("*                                                      *");
        System.out.println("********************************************************");
    }

    static public void printMenu_3(){
        System.out.println("********************************************************");
        System.out.println("*                                                      *");
        System.out.println("*                      查找队伍信息                      *");
        System.out.println("*                   输入序号进行相应操作                   *");
        System.out.println("*                1.按照参赛学校查找队伍信息                 *");
        System.out.println("*                2.按照赛事类别查找队伍信息                 *");
        System.out.println("*                     0.返回上个界面                     *");
        System.out.println("*                                                      *");
        System.out.println("********************************************************");
    }


    public static void main(String[] args) {

        ArrayList<Team> teams = new ArrayList<>();      //存储团队信息
        BinarySortTree bTree = new BinarySortTree();    //二叉树存储 进行团队的查找
        Graph graph = new Graph();
        Scanner sc = new Scanner(System.in);
        boolean program = true;
        FileManage fileManage = new FileManage();

        while (program){
            teams = fileManage.getTeams();
            treeStorage(bTree,teams);           //将ArrayList中的元素数据存入二叉树中
            printMenu_0();
            int order = sc.nextInt();
            switch (order){
                case 0:
                    program = false;
                    break;

                case 1:
                    printMenu_1();
                    int order_1 = sc.nextInt();
                    switch (order_1){
                        case 0:
                            break;

                        case 1:
                            for (Team team : teams) {
                                System.out.println("-----------------------------------------------");
                                team.printTeam();
                                System.out.println("-----------------------------------------------");
                            }
                            break;

                        case 2:
                            fileManage.setFileWrite(filePath);
                            break;

                        case 3:
                            System.out.println("======================================");
                            System.out.println("请输入需删除的队伍ID");
                            int id = sc.nextInt();
                            fileManage.fileDelete(filePath,id);
                            System.out.println("======================================");
                            break;

                        default:
                            break;
                        }
                    try {
                        System.out.println("等待中");
                        System.out.println("======================================");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Error");;
                    }
                    break;

                case 2:
                    System.out.println("=======================================");
                    System.out.println("                ID查找队伍               ");
                    System.out.println("请输入需查找队伍信息的队伍ID（格式:202100XXXX）");
                    int id = sc.nextInt();

                    Node node = bTree.search(id);
                    if (node == null){
                        System.out.println("查找失败，无相关队伍ID与输入匹配");
                    } else{
                        System.out.println("查找到队伍信息：");
                        node.getTeam().printTeam();
                        System.out.println("查找次数："+ node.getTimes());
                        int n = teams.size();
                        System.out.print("平均查找次数ASL：");
                        double asl = bTree.root.BST_ASL(bTree.root,0);
                        System.out.println(asl/teams.size());
                        System.out.println();
                        System.out.println(((n+1)/n) * (log(n,2)) - 1);         //查找次数有误
                    }
                    try {
                        System.out.println("等待中");
                        System.out.println("=======================================");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Error");;
                    }
                    break;

                case 3:
                    ArrayList<Team> getTeam = new ArrayList<>();
                    printMenu_3();
                    int order_2 = sc.nextInt();
                    String str;
                    System.out.println("=======================================");
                    System.out.println("              查找队伍信息                ");
                    switch (order_2){
                        case 0:
                            break;
                        case 1:
                            System.out.println("请输入学校名称：");
                            str = sc.next();
                            getTeam = getTeamData(teams,str,1);
                            if (getTeam.get(0) == null){
                                System.out.println("查找失败，该学校没有参加比赛");
                            }else {
                                System.out.println("以下为查找到的学校参加比赛的队伍信息：");
                                for (Team team : getTeam) {
                                    System.out.println("================================");
                                    team.printTeam();
                                    System.out.println("================================");
                                }
                            }
                            break;
                        case 2:
                            System.out.println("请输入赛事类别：");
                            str = sc.next();
                            getTeam = getTeamData(teams,str,2);
                            if (getTeam.get(0) == null){
                                System.out.println("查找失败，该学校没有参加比赛");
                            }else {
                                System.out.println("以下为查找到的学校参加比赛的队伍信息：");
                                for (Team team : getTeam) {
                                    System.out.println("---------------------------------");
                                    team.printTeam();
                                    System.out.println("---------------------------------");
                                }
                            }
                            break;
                    }
                    try {
                        System.out.println("等待中");
                        Thread.sleep(3000);
                        System.out.println("=======================================");
                    } catch (InterruptedException e) {
                        System.out.println("Error");;
                    }
                    break;

                case 4:
                    System.out.println("======================================");
                    System.out.println("               叫号系统                 ");
                    callSystem(teams);
                    try {
                        System.out.println("等待中");
                        System.out.println("======================================");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("Error");;
                    }
                    break;

                case 5:
                    //输出图片
                    //fileManage.getImage(imagePath);
                    int v1;
                    int v2;
                    System.out.println("=======================================");
                    System.out.println("地点如下：");
                    graph.placePrint();
                    System.out.println("按照地点序号输入，查找两点最短路径");
                    System.out.println("请输入起始点:");
                    v1 = sc.nextInt();
                    System.out.println("请输入终点:");
                    v2 = sc.nextInt();
                    graph.ShortestPath_DIJ(graph,v1,v2);
                    try {
                        System.out.println("等待中");
                        System.out.println("======================================");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Error");;
                    }
                    break;
                    }

        }

    }
}

