package com.project.zone.bean;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Topic
 * @Description 日志
 * @Author wk
 * @Date 2022/3/21 17:22
 * @Version 1.0
 */
public class Topic {
    private Integer id;
    private String title;
    private String content;
    private Date publishDate;
    private UserBasic author;   // M : 1

    private List<Reply> replyList;  // 1 : N

    public Topic(){

    }

    public Topic(Integer id){
        this.id = id;
    }

    public Topic(Integer id, String title, String content, Date publishDate, UserBasic author, List<Reply> replyList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.author = author;
        this.replyList = replyList;
    }

    public Topic(String title, String content, Date publishDate, UserBasic author) {
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                ", author=" + author +
                ", replyList=" + replyList +
                '}';
    }
}
