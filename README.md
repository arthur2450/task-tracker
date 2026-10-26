
```markdown
# Java Task Tracker

A terminal-based task manager built with Java.

## Features

- Create tasks with a title and description.
- List tasks with their IDs and completion status.
- Mark tasks as completed.
- Edit task titles and descriptions.
- Delete tasks by ID.
- Save tasks to a file and load them on startup.
- Handle invalid input and file errors.

## Technologies

- Java 27
- Maven

## How to Run

1. Open the project in IntelliJ IDEA.
2. Set JDK 27 as the project SDK.
3. Open src/main/java/com/arthur/tasktracker/main/Main.java.
4. Run the main method.
5. Follow the menu instructions in the terminal.

The application currently displays its menu and messages in Portuguese.

## Menu Options

| Option | Action |
|--------|--------|
| 1 | Create a task |
| 2 | List tasks |
| 3 | Mark a task as completed |
| 4 | Delete a task |
| 5 | Edit a task |
| 0 | Save and exit |

## Data Storage

Tasks are loaded from tarefas.properties when the application starts.
If the file does not exist, the application starts with an empty task list.

Choose option 0 to save your changes and exit.
Closing the application directly may cause unsaved changes to be lost.

The file is stored in the application's working directory.
Task IDs, titles, descriptions, completion status, and the next available
ID are preserved between sessions.

If loading fails, the application reports the error and exits.
If saving fails, it reports the error and keeps the menu available.

## Code Structure

- Task: represents a task and validates its title.
- TaskManager: manages tasks and handles file storage.
- Main: displays the menu and processes user input.

## Concepts Practiced

- Object-oriented programming
- Encapsulation
- Collections with ArrayList
- Loops and switch statements
- Exception handling
- File reading and writing
```
