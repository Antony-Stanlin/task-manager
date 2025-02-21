package model;

public class Task implements Comparable<Task> {

    private int id;
    private String name;
    private int time;
    private int priority;

    public Task(){}

    public Task(int id,String name,int priority,int time){
        this.id=id;
        this.name=name;
        this.priority=priority;
        this.time=time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString(){
        return """
        [ID: %d, Name: %s, Priority: %d, Time: %d mins]
                """.formatted(id,name,priority,time);
    }

    @Override
    public int compareTo(Task task){
        if(task.getPriority()!=this.getPriority())
            return Integer.compare(this.getPriority(),task.getPriority());
        
        return Integer.compare(this.getId(),task.getId());
    }


}
