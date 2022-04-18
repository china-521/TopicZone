package com.project.zone.bean;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Reply
 * @Description 评论
 * @Author wk
 * @Date 2022/3/21 17:22
 * @Version 1.0
 */
public class Reply {
    private Integer id;
    private String content;
    private Date replyDate;
    private UserBasic author; // M : 1
    private Topic topic;   // M : 1

    private List<HostReply> hostReply; // 1 : N

    public Reply(){

    }

    public Reply(Integer id) {
        this.id = id;
    }

    public Reply(String content, Date replyDate, UserBasic author, Topic topic) {
        this.content = content;
        this.replyDate = replyDate;
        this.author = author;
        this.topic = topic;
    }

    public Reply(String content, Date replyDate, UserBasic author, Topic topic, List<HostReply> hostReply) {
        this.content = content;
        this.replyDate = replyDate;
        this.author = author;
        this.topic = topic;
        this.hostReply = hostReply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<HostReply> getHostReply() {
        return hostReply;
    }

    public void setHostReply(List<HostReply> hostReply) {
        this.hostReply = hostReply;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", replyDate=" + replyDate +
                ", author=" + author +
                ", topic=" + topic +
                ", hostReply=" + hostReply +
                '}';
    }
}
