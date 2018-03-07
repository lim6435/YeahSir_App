package kr.co.william.yeahsir.data;

/**
 * Created by sheo on 2018-03-01.
 */

public class MemberInfo {

    private String id;
    private String password;
    private String name;
    private String height;
    private String weight;

    private String attendList; // sheotest TODO 참석리스트

    public MemberInfo(String id, String password, String name, String height, String weight) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public static class Builder {

        private String id;
        private String password;
        private String name;
        private String height;
        private String weight;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setHeight(String height) {
            this.height = height;
            return this;
        }

        public Builder setWeight(String weight) {
            this.weight = weight;
            return this;
        }

        public MemberInfo build() {
            return new MemberInfo(id, password, name, height, weight);
        }
    }
}
