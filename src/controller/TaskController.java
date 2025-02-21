package controller;

import java.sql.SQLException;
import model.Task;
import model.TaskDAO;
import view.TaskView;

public class TaskController {

    private TaskView view;
    private TaskDAO model;

    public TaskController(TaskView view, TaskDAO model) {
        this.view = view;
        this.model = model;
    }

    public void init() {

        while (true) {
            try {
                int choice = view.getChoice();

                switch (choice) {
                    case 1 -> {// 1.add task
                        Task task = view.getTask();
                        model.addTask(task);
                        view.showMessage("Task Added: " + task);
                    }

                    case 2 -> {// 2.show task by priority
                        view.displayTasks(model.getTaskList());
                    }

                    case 3 -> {// 3.simulate task
                        Thread.sleep(3000);
                        Task task = model.executeTask();
                        if (task == null) {
                            view.showMessage("No task exists");
                            break;
                        }
                        view.showMessage("""
                                    User simulates taSk execution:
                                    Executing Task: "%s", Remaining Time: %dmins
                                """.formatted(task.getName(), task.getTime()));
                    }

                    case 4 -> {// 4.mark as completed
                        Thread.sleep(3000);
                        Task task = model.setAsCompleted();
                        if (task == null) {
                            view.showMessage("No Task exists");
                            break;
                        }
                        view.showMessage("""
                                    Executing Task: "%s" completed
                                """.formatted(task.getName()));
                    }

                    case 5 -> {// 5.calculate time
                        Thread.sleep(3000);
                        view.showMessage("Total time taken: " + model.getTotalTime() + "mins");
                    }

                    case 6 -> {// 6.exit
                        view.showMessage("Thank you, visit again");
                        return;
                    }

                    default -> view.showMessage("Invalid choice, please try again");
                }
            } catch (SQLException e) {
                view.showMessage("DataBase error: " + e.getMessage());
            } catch (InterruptedException e) {
                view.showMessage("Simulation Interruped: " + e.getMessage());
            }
        }
    }

}
