public class games {
    private String gamename,gamekind,intro,subject,updatatime;
    private int liking;

    public games() {
        //无参构造
    }

    @Override
    public String toString() {
        return "games{" +
                "gamename='" + gamename + '\'' +
                ", gamekind='" + gamekind + '\'' +
                ", intro='" + intro + '\'' +
                ", subject='" + subject + '\'' +
                ", updatatime='" + updatatime + '\'' +
                ", liking=" + liking +
                '}';
    }

    // name,kinds,intro,liking,subject,updatetime
    public games(String gamename, String gamekind, String intro, int liking, String subject,String updatatime) {
        this.gamename = gamename;
        this.gamekind = gamekind;
        this.intro = intro;
        this.subject = subject;
        this.updatatime = updatatime;
        this.liking = liking;
    }
    public void setGamekind(String gamekind) {
        this.gamekind = gamekind;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setLiking(int liking) {
        this.liking = liking;
    }

    public void setUpdatatime(String updatatime) {
        this.updatatime = updatatime;
    }

    public String getGamekind() {
        return gamekind;
    }

    public String getIntro() {
        return intro;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUpdatatime() {
        return updatatime;
    }

    public int getLiking() {
        return liking;
    }

    public String getGamename() {
        return gamename;
    }
}
