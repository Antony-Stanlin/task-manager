## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


# Task Manager

A simple console-based Task Manager built using Java, JDBC, and MySQL. This project demonstrates the use of the MVC pattern to manage tasks, including features such as adding tasks, viewing tasks by priority, simulating task execution, marking tasks as completed, and calculating the total remaining time.

## Features

- **Add Task:** Create new tasks with a name, priority, and estimated time.
- **View Tasks by Priority:** Display tasks grouped by their priority levels.
- **Simulate Task Execution:** Simulate execution by decrementing the remaining time of a task.
- **Mark Task as Completed:** Set a task's time to zero to mark it as completed.
- **Calculate Total Time:** Calculate the total remaining time for tasks that have not yet been completed.

## Technologies Used

- **Java**
- **JDBC**
- **MySQL**
- **MVC Architecture**

## Database Setup

1. Install MySQL and create a database, e.g., `task_manager`.
2. Create the `task` table using the following SQL command:

   ```sql
   CREATE TABLE task (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       priority INT NOT NULL,
       time INT NOT NULL
   );

Project Structure:

TaskManager/
├── src/
│   ├── controller/
│   │   └── TaskController.java
│   ├── model/
│   │   ├── Task.java
│   │   └── TaskDAO.java
│   ├── view/
│   │   └── TaskView.java
│   └── Main.java
└── README.md

