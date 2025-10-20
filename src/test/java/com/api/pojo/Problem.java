package com.api.pojo;

public class Problem {
    private int id;
    private String remark;

    // Constructor
    public Problem(int id, String remark) {
        this.id = id;
        this.remark = remark;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for remark
    public String getRemark() {
        return remark;
    }

    // Setter for remark
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Problem [id=" + id + ", remark=" + remark + "]";
    }
}
