package com.project.zone.bean;

import java.util.List;

/**
 * @ClassName UserBasic
 * @Description 用户基本信息
 * @Author wk
 * @Date 2022/3/21 17:22
 * @Version 1.0
 */
public class UserBasic {
    private Integer id;
    private String loginId;
    private String nickName;
    private String password;
    private String headImage;

    private UserDetail userDetail;  // 1 ： 1 (用户基本信息和用户详情信息是1对1)
    private List<Topic> topicList; //  1 ： N (用户 和 日志 是 1 对 多)
    private List<UserBasic> friendList; // M : N (用户 和 好友 是 多 对 多)
    private boolean addFriend; // 添加好友标识 false：不能添加，true：可以添加

    public UserBasic() {

    }

    public UserBasic(Integer id){
        this.id = id;
    }

    public UserBasic(String loginId, String nickName, String password, String headImage) {
        this.loginId = loginId;
        this.nickName = nickName;
        this.password = password;
        this.headImage = headImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<UserBasic> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserBasic> friendList) {
        this.friendList = friendList;
    }

    public boolean getAddFriend() {
        return addFriend;
    }

    public void setAddFriend(boolean addFriend) {
        this.addFriend = addFriend;
    }

    @Override
    public String toString() {
        return "UserBasic{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", headImage='" + headImage + '\'' +
                ", userDetail=" + userDetail +
                ", topicList=" + topicList +
                ", friendList=" + friendList +
                '}';
    }
}
