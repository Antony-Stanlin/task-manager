package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class TaskDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/task_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "zoho123";
    private Queue<Task> taskList = new PriorityQueue();

    public TaskDAO() {
        fillTaskList();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void fillTaskList() {
        String query = "SELECT * FROM task where time > 0";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("priority"),
                        rs.getInt("time"));
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log errors properly
        }

    }

    // 1.Add Task
    public void addTask(Task task) throws SQLException {

        String query = "insert into task (name,priority,time) values(?,?,?)";
        String query1 = "SELECT max(id) id from task";

        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query);
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery(query1)) {

            stmt.setString(1, task.getName());
            stmt.setInt(2, task.getPriority());
            stmt.setInt(3, task.getTime());

            stmt.executeUpdate();

            if (rs.next()) {
                int id = rs.getInt("id");
                if (rs.wasNull()) {
                    task.setId(1);
                } else {
                    task.setId(id + 1);
                }
            }
        }

        taskList.add(task);
    }

    // 2.print priority
    public Map<Integer, List<Task>> getTaskList() throws SQLException {
        Map<Integer, List<Task>> map = new TreeMap<>();
        String query = "SELECT * FROM task WHERE time > 0";
        try (Connection con = getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("priority"),
                        rs.getInt("time"));

                map.computeIfAbsent(task.getPriority(), k -> new ArrayList()).add(task);
            }
        }

        return map;
    }

    // 3.Simulate Task
    public Task executeTask() throws SQLException {
        if (taskList.isEmpty())
            return null;
        Task task = taskList.remove();
        task.setTime(task.getTime() - 1);
        String query = "update task set time=? where id=?";
        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, task.getTime());
            stmt.setInt(2, task.getId());
            stmt.executeUpdate();
        }

        if (task.getTime() > 0)
            taskList.add(task);

        return task;
    }

    // 4.Mark as completed
    public Task setAsCompleted() throws SQLException {
        if (taskList.isEmpty())
            return null;
        Task task = taskList.remove();
        task.setTime(0);
        String query = "update task set time=0 where id=?";
        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, task.getId());
            stmt.executeUpdate();
        }
        return task;
    }

    // 5.Calculate total time
    public int getTotalTime() throws SQLException {
        String query = "select sum(time) time from task";
        int total = 0;
        try (Connection con = getConnection();
                Statement stmt = con.prepareCall(query);
                ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next())
                total = rs.getInt("time");
        }
        return total;
    }
}
