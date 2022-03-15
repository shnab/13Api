package com.techproed.pojos;

public class JsonPlaceHolderTodosPojo {

    /*
        Request body  {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
     */
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    //sadece Boolean da isCompleted oluyor
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public JsonPlaceHolderTodosPojo() {
    }

    public JsonPlaceHolderTodosPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    //toString olmazsa bunlari yazdiramayiz
    @Override
    public String toString() {
        return "JsonPlaceHolderTodosPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
