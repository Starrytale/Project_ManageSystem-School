public class Team { //基本信息

    int teamID;  //参赛队编号
    String workName;    //参赛作品名称
    String university;  //参赛学校
    String eventCate;   //赛事类别      赛事类别共11项
    String participant;  //参赛者
    String teacher;  //指导老师

        public Team (){
            this.teamID=0;
            this.workName=null;
            this.university=null;
            this.eventCate=null;
            this.participant=null;
            this.teacher=null;
        }

        public Team(int teamID,String workName,String university,String eventCate,String participant,String teacher){
            this.teamID = teamID;
            this.workName = workName;
            this.university = university;
            this.eventCate = eventCate;
            this.participant = participant;
            this.teacher = teacher;
        }

        public int getTeamID() {
            return teamID;
        }

        public void setTeamID(int teamID) {
            this.teamID = teamID;
        }

        public String getWorkName() {
            return workName;
        }

        public void setWorkName(String workName) {
            this.workName = workName;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getEventCate() {
            return eventCate;
        }

        public void setEventCate(String eventCate) {
            this.eventCate = eventCate;
        }

        public String getParticipant() {
            return participant;
        }

        public void setParticipant(String participant) {
            this.participant = participant;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public void dataManaged(int teamID, String workName, String university,String eventCate,String student, String teacher) {
            this.teamID = teamID;
            this.workName = workName;
            this.university = university;
            this.participant=student;
            this.teacher=teacher;
            this.eventCate = eventCate;
        }

        public void printTeam(){
            System.out.println("参赛队编号："+teamID);
            System.out.println("参赛作品名称："+workName);
            System.out.println("参赛学校："+university);
            System.out.println("参赛类别："+eventCate);
            System.out.println("参赛者："+participant);
            System.out.println("指导老师："+teacher);
        }

}

