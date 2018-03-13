package kr.co.william.yeahsir.data;

import java.io.Serializable;

/**
 * Created by sheo on 2018-03-13.
 */

@SuppressWarnings("serial")
public class CompetitionVo implements Serializable {

    private String name = "";            // 대회명
    private String where = "";           // 장소
    private String time = "";            // 시간
    private String type = "";            // 경기방식
    private String hpNum = "";           // 대표번호
    private String rule = "";            // 규칙
    private String teamName = "";        // 팀명

    public CompetitionVo(String name, String where, String time, String type, String hpNum, String rule, String teamName) {
        this.name = name;
        this.where = where;
        this.time = time;
        this.type = type;
        this.hpNum = hpNum;
        this.rule = rule;
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHpNum() {
        return hpNum;
    }

    public void setHpNum(String hpNum) {
        this.hpNum = hpNum;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public static class Builder {

        private String name = "";
        private String where = "";
        private String time = "";
        private String type = "";
        private String hpNum = "";
        private String rule = "";
        private String teamName = "";

        public CompetitionVo.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public CompetitionVo.Builder setWhere(String where) {
            this.where = where;
            return this;
        }

        public CompetitionVo.Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public CompetitionVo.Builder setRule(String rule) {
            this.rule = rule;
            return this;
        }

        public CompetitionVo.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public CompetitionVo.Builder setHpNum(String hpNum) {
            this.hpNum = hpNum;
            return this;
        }

        public CompetitionVo.Builder setTeamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public CompetitionVo build() {
            return new CompetitionVo(name, where, time, rule, type, hpNum, teamName);
        }
    }
}
