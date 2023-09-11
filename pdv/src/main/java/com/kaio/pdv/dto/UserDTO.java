package com.kaio.pdv.dto;

import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private boolean isEnable;

    public UserDTO(Long id, String name, boolean isEnable) {
        this.id = id;
        this.name = name;
        this.isEnable = isEnable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
