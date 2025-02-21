package view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Task;

public class TaskView {

    private Scanner scanner = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public int getChoice() {
        System.out.println("======Task Manager======");
        System.out.println("1.Add Task");
        System.out.println("2.Show Task by priority");
        System.out.println("3.Simulate Task");
        System.out.println("4.Mark as Completed");
        System.out.println("5.Calculate Total Time");
        System.out.println("6.exit");
        System.out.print("Enter the choice : ");

        return scanner.nextInt();
    }

    public Task getTask() {
        scanner.nextLine();
        Task task = new Task();
        System.out.print("Enter the name of the task: ");
        task.setName(scanner.nextLine());
        System.out.print("Enter the priority: ");
        task.setPriority(scanner.nextInt());
        System.out.print("Enter time taken: ");
        task.setTime(scanner.nextInt());
        return task;
    }

    public void displayTasks(Map<Integer, List<Task>> map) {
        if (map == null || map.isEmpty())
            System.out.println("All tasks were executed completely or no task exists");
        else
            map.forEach((priority, tasks) -> {
                System.out.println("Priority " + priority + ":");
                tasks.forEach(System.out::println);
            });
        System.out.println();
    }

}
