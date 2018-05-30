package kr.ac.jejunu.jnu_tong.vo;

/**
 * Created by seung-yeol on 2018. 5. 19..
 */

public class ShuttleVO {
    private Integer id;
    private Integer order;
    private String name;

    private Integer firstTime;
    private Integer secondTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Integer firstTime) {
        this.firstTime = firstTime;
    }

    public Integer getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(Integer secondTime) {
        this.secondTime = secondTime;
    }
}
