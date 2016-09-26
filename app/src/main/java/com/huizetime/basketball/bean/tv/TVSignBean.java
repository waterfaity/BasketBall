package com.huizetime.basketball.bean.tv;

import java.util.List;

/**
 * Created by water_fairy on 2016/9/22.
 */
public class TVSignBean {

    private String matchName;//赛事名字
    private Entity entityA;//A队
    private Entity entityB;//B队

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public Entity getEntityA() {
        return entityA;
    }

    public void setEntityA(Entity entityA) {
        this.entityA = entityA;
    }

    public Entity getEntityB() {
        return entityB;
    }

    public void setEntityB(Entity entityB) {
        this.entityB = entityB;
    }

    public class Entity {
        private String teamName;
        private List<Player> list;

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public List<Player> getList() {
            return list;
        }

        public void setList(List<Player> list) {
            this.list = list;
        }
    }

    public class Player {
        private String name;//名字
        private int num;//球衣号
        private String position;//位置
        private boolean sign;//签到状态

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public boolean isSign() {
            return sign;
        }

        public void setSign(boolean sign) {
            this.sign = sign;
        }
    }

}
