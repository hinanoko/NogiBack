package com.example.nogiback.entity;

import java.util.List;

public class BlogDetail {
    private String idolId;
    private Integer blogId;
    private String content;
    private List<String> imageUrls;

    public String getIdolId() {
        return idolId;
    }

    public void setIdolId(String idolId) {
        this.idolId = idolId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
