public class Graph {

    //根据图 得出的已知默认条件
    static int ver = 10;
    static int edg = 13;
    static String[] places = {"北门","北苑食堂","计算机学院","文理大楼","图书馆",
                              "住宿区","笃学楼","东苑食堂","体育中心","南门"};
    static int nullPoint = 1024;

    int vertexNum,edgings;		 //图的 点数 、 边数
    VertexType[] vertexTypes = new VertexType[10];		//定义10个顶点
    int [][] distance = new int[10][10];			//用来存取权值 (地图上两个地点之间的距离)

    public Graph()       //改进方式 将数据填写在文档中进行读取
    {

        vertexNum = ver;
        edgings = edg;

        for (int i=0;i<vertexNum;i++){        //地点名称存储
            vertexTypes[i] = new VertexType(i+1,places[i]);
        }

        //地点的地理位置距离 存储
        distance[0][1] = 100;   //北门
        distance[0][2] = 200;

        distance[1][0] = 100;   //北苑食堂
        distance[1][4] = 400;

        distance[2][0] = 200;   //计算机学院
        distance[2][3] = 100;

        distance[3][2] = 100;   //文理大楼
        distance[3][5] = 550;
        distance[3][6] = 300;

        distance[4][1] = 400;   //图书馆
        distance[4][6] = 150;
        distance[4][7] = 100;

        distance[5][3] = 550;   //住宿区
        distance[5][8] = 450;

        distance[6][3] = 300;   //笃学楼
        distance[6][4] = 150;
        distance[6][8] = 500;
        distance[6][9] = 200;

        distance[7][4] = 100;   //东苑食堂
        distance[7][9] = 600;

        distance[8][5] = 450;   //体育中心
        distance[8][6] = 200;
        distance[8][9] = 250;

        distance[9][6] = 200;   //南门
        distance[9][7] = 600;
        distance[9][8] = 250;
        //该二维矩阵为对称矩阵

        //无边相连的点 距离设为 nullPoint  1024
        for (int i=0;i<vertexNum;i++){
            for (int n=0;n< vertexNum;n++){
                if (distance[i][n] == 0) {
                    distance[i][n] = 1024;
                }
            }
        }
    }

    public void ShortestPath_DIJ(Graph graph,int v0,int v1)
    {
        //v0 为用户输入的起始地点序号（从1开始） 故使用时 需减1
        //v1 为用户输入的终点地点序号（从1开始） 故使用时 需减1
        int node = graph.vertexNum;			//先把地图的位置顶点数用n来表示
        boolean[] isShortest = new boolean[node];	//每执行一次循环找出最小路径，如果找出一个最小路径就把该点置为true
        int[] DIJ = new int[node];			//存权值(最小的),如果有更小的将会代替该位置上大的权值
        int[] Path = new int[node];		//记录该索引顶点的前驱顶点

        //初始化,先找出v0结点的邻居结点的各个权值
        for(int i=0; i<node; i++)
        {
            isShortest[i] = false; 				//S中先都置为false
            DIJ[i] = graph.distance[v0 -1][i]; 	//最开始先把v0与其他几个顶点的权值赋值给D[i]
            if(DIJ[i] >0 && DIJ[i] != 0)        //这一行是否有节点 相通
                Path[i] = v0 - 1;			//第i个顶点的前驱结点就是v0,表示i和v0相邻
            else
                Path[i] = -1;			//表示i顶点和v0顶点没有相邻,如果相邻必有权值不为无穷大
        }
        isShortest[v0 - 1] = true;					//v0与v0之间路径为0，我们不再讨论

        int v = - 1;
        //对剩下的n-1个顶点循环。
        for(int i = 1; i < node; i++)
        {
            int m = nullPoint;
            //找出D中最小的路径
            for(int w = 0; w < node; w++)
                if(!isShortest[w] && DIJ[w] < m)
                {
                    m = DIJ[w];			//依次比较D中的权值，找出最小的权值
                    v = w;			//把这个最小权值的位置赋值给v(记录下来)
                }
            if(v != -1)				//表示我们找到了D中的最小路径
            {
                isShortest[v] = true;		//我们找到了最小路径,我们把它置为true,以防下次还找到它
                for(int w = 0; w < node; w++)
                    if(!isShortest[w] && DIJ[v] + graph.distance[v][w] < DIJ[w])		//如果原来的路径D[w](0-n)依次和v0到v的权值再加上v到w的权值进行比较
                    {
                        DIJ[w] = DIJ[v] + graph.distance[v][w];				//如果小于了，等于说有最优路径,我们把最优路径赋值给D[w]
                        Path[w] = v;								//并把w的前驱置为v
                    }
            }
        }
        int [] a = new int[node];
        for(int i = 0; i < Path.length; i++)
        {
            a[i] = -1;
            if(Path[v1 - 1] == v0 - 1)
            {
                continue;
            }
            else
            {
                a[i] = Path[v1 - 1];
                if(Path[v1 - 1] != -1)
                    Path[v1 - 1] = Path[Path[v1 - 1]];
            }
        }
        if(DIJ[v1 - 1] != nullPoint)
        {
            System.out.print("路径是:" + graph.vertexTypes[v0 - 1].placeName);
            for(int i = a.length - 1; i >= 0; i --)
            {
                if(a[i] != -1)
                {
                    System.out.print("->" + graph.vertexTypes[a[i]].placeName);
                }
            }
            System.out.print("->" + graph.vertexTypes[v1 - 1].placeName + "\n");
            printf("最短距离为:" + DIJ[v1 - 1]);
        }
        else
            printf("对不起!两者不连通!");
    }

    public static void printf(Object o)
    {
        System.out.println(o);
    }


    public void placePrint(){
        int i=1;
        System.out.println("地点分别为");
        for (String place : places) {
            System.out.print(i+"." + place);
            System.out.println();
            i++;
        }

    }

}
