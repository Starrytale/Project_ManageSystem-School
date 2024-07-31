import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManage {

    static String filePath = "D://IDEA/Code/ParticipateTeam/team.txt";     //文件路径
    static String imagePath = "D://IDEA/Code/ParticipateTeam/map.png";     //图片路径
    ArrayList<Team> teams;  //用于执行存储，修改的操作
    String content;

    FileManage(){
        teams = new ArrayList<>();
        dataStorage();
    }

    public void getContent(){
        System.out.println(content);
    }

    public ArrayList<Team> getTeams(){
        return teams;
    }

    public String getFileRead(String filePath) {       //读取文本
        StringBuilder lineTXT = new StringBuilder();
        try{
            File file = new File(filePath);
            if (file.isFile() && file.exists()){
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String lineTXT_1;
                while (((lineTXT_1 = bufferedReader.readLine()) != null)){
                    lineTXT.append(lineTXT_1).append("\n");
                }
                bufferedReader.close();
            }
            else {
                System.out.println("TXT文档不存在");
            }
        }
        catch (Exception e) {
            System.out.println("ERROR，文件读取出现错误");
        }
        return lineTXT.toString();
    }

    public void setFileWrite(String filePath){
        String str;
        int num;
        int teamID = 0;
        String workName = null;
        String university = null;
        String eventCate = null;
        String participant = null;
        String teacher = null;
        Scanner sc = new Scanner(System.in);
        try {
            FileWriter fileWriter = new FileWriter(filePath,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("输入需添加的参赛队编号");
            num = sc.nextInt();
            teamID = num;
            bufferedWriter.write(num +"\t#\t");

            System.out.println("输入需添加的参赛作品名称");
            str = sc.next();
            workName = str;
            bufferedWriter.write(str+"\t#\t");

            System.out.println("输入需添加的参赛学校");
            str = sc.next();
            university = str;
            bufferedWriter.write(str+"\t#\t");

            System.out.println("输入需添加的赛事类别");
            str = sc.next();
            eventCate = str;
            bufferedWriter.write(str+"\t#\t");

            System.out.println("输入需添加的参赛者");
            str = sc.next();
            participant = str;
            bufferedWriter.write(str+"\t#\t");

            System.out.println("输入需添加的指导教师");
            str = sc.next();
            teacher = str;
            bufferedWriter.write(str+"\n");

            bufferedWriter.close();
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("ERROR");
        }
        Team team = new Team(teamID,workName,university,eventCate,participant,teacher);
        teams.add(team);
        System.out.println("添加成功");
    }

    public void dataStorage() {

        String txtReading = getFileRead(filePath);     //获取文档信息  未删除空格
        String txt = txtReading.replaceAll("\t",""); //去除空格
        this.content = txt;
        String[] resultLine = txt.split("\n");
        for (int n=1;n<resultLine.length;n++) {
            Team team = new Team();
            String[] str = resultLine[n].split("#");
            int i=0;
            team.dataManaged(Integer.parseInt(str[i++]),str[i++],str[i++],str[i++],str[i++],str[i++]);
            this.teams.add(team);
        }
    }

    public void fileDelete(String filePath,int ID){

        String str;
        for (int i=0;i<teams.size();i++) {
            if (teams.get(i).getTeamID() == ID){
                teams.remove(i);
            }
        }

        //将ArrayList 中的元素删除后重新添加到文件中
        try {
            FileWriter fileWriter = new FileWriter(filePath,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("参赛队编号\t#\t参赛作品名称\t#\t参赛学校\t#\t赛事类别\t#\t参赛者\t#\t指导教师\n");
            for (Team team : teams) {
                str = String.valueOf(team.getTeamID()) + "\t#\t" + team.getWorkName() + "\t#\t" +
                        team.getUniversity() + "\t#\t" + team.getEventCate() + "\t#\t" +
                        team.getParticipant() + "\t#\t" + team.getTeacher() +"\n";
                bufferedWriter.write(str);
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("ERROR");
        }
        System.out.println("删除成功");
    }

    public void getImage(String imagePath){

    }


}
