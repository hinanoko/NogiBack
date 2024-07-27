package com.example.nogiback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("history")
@Data
public class History {
    @TableId(type = IdType.AUTO)
    private Integer blogId;
    private String blogIdolId;
    private String blogUserId;
    private String title;
    private String blogDate;
    private List<ContentBlock> contentBlocks;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getBlogIdolId() {
        return blogIdolId;
    }

    public void setBlogIdolId(String blogIdolId) {
        this.blogIdolId = blogIdolId;
    }

    public String getBlogUserId() {
        return blogUserId;
    }

    public void setBlogUserId(String blogUserId) {
        this.blogUserId = blogUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(String blogDate) {
        this.blogDate = blogDate;
    }

    public List<ContentBlock> getContentBlocks() {
        return contentBlocks;
    }

    public void setContentBlocks(List<ContentBlock> contentBlocks) {
        this.contentBlocks = contentBlocks;
    }

    // Nested static class for content blocks
    @Data
    public static class ContentBlock {
        private String type;
        private String value;
    }

    @Override
    public String toString() {
        return "History{" +
                "blogId=" + blogId +
                ", blogIdolId='" + blogIdolId + '\'' +
                ", blogUserId='" + blogUserId + '\'' +
                ", title='" + title + '\'' +
                ", blogDate='" + blogDate + '\'' +
                ", contentBlocks=" + contentBlocks +
                '}';
    }
}
