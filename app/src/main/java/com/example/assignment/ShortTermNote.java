package com.example.assignment;

public class ShortTermNote {
    private int id;
    private String title;
    private String deadline;
    private String content;
    private int isDeleted;
    private int longNoteId;

    public ShortTermNote() {

    }

    public ShortTermNote(String title, String deadline, String content, int isDeleted, int longNoteId) {
        this.title = title;
        this.deadline = deadline;
        this.content = content;
        this.isDeleted = isDeleted;
        this.longNoteId = longNoteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getLongNoteId() {
        return longNoteId;
    }

    public void setLongNoteId(int longNoteId) {
        this.longNoteId = longNoteId;
    }
}
