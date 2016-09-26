package com.huizetime.basketball.bean.tv;

/**
 * Created by water_fairy on 2016/9/22.
 */
public class TVScoreBean {
    //AB队
    private Entity teamA, teamB;
    private int time;//比赛剩余时间
    private int segment;//小节

    public Entity getTeamA() {
        return teamA;
    }

    public void setTeamA(Entity teamA) {
        this.teamA = teamA;
    }

    public Entity getTeamB() {
        return teamB;
    }

    public void setTeamB(Entity teamB) {
        this.teamB = teamB;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSegment() {
        return segment;
    }

    public void setSegment(int segment) {
        this.segment = segment;
    }

    public class Entity {
        private String teamName;//队伍名字
        private int score;//得分
        private int stopTime;//暂停时间
        private int foul;//犯规时间

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getStopTime() {
            return stopTime;
        }

        public void setStopTime(int stopTime) {
            this.stopTime = stopTime;
        }

        public int getFoul() {
            return foul;
        }

        public void setFoul(int foul) {
            this.foul = foul;
        }
    }
}
