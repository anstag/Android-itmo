package ru.anstag.app.notes;

public class Notes {

    private  long id;
    private String name, description;

    public Notes(){

    }

    public Notes(String name, String description){
        this.name = name;
        this.description = description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return "Дата";
    }


}
