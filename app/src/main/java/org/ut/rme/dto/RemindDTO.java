package org.ut.rme.dto;

public class RemindDTO {
    private  String title;

    public RemindDTO(String s) {
        this.title = s;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
