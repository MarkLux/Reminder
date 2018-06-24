package com.marklux.reminder.domain;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lumin on 18/6/23.
 */
public class Remind {
    private Long id;
    private Integer userId;
    @NotBlank
    private String content;
    private Long createdAt;
    private Short isPinned;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Short getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(Short isPinned) {
        this.isPinned = isPinned;
    }
}
