package com.tkieffer.domo;

public class Switch {

    private String name;
    private Integer id;
    private boolean checked;

    public Switch(String name, Integer id, boolean status) {
        this.name = name;
        this.id = id;
        this.checked = status;
    }


    // Getters & setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean status) {
        this.checked = status;
    }
}
