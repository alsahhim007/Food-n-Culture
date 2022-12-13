package com.dalhousie.foodnculture.models;

public class Friends {

    public String FriendName;
    public String Uname;
    public int uimage;

    public Friends(String friendName, String uname, int uimage) {
        this.FriendName = friendName;
        this.Uname = uname;
        this.uimage = uimage;
    }

    private int id;
    private int userId;
    private int targetUserId;
    private String updatedAt;
    private String createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updateAt) {
        this.updatedAt = updateAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
