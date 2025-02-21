import controller.TaskController;
import model.TaskDAO;
import view.TaskView;

public class TaskManager {
    public static void main(String[] args) throws Exception {
        TaskView view = new TaskView();
        TaskDAO taskDAO = new TaskDAO();

        new TaskController(view, taskDAO).init();

    }
}
