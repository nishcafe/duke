package tool;

import task.Task;

public class Ui {

    /**
     * Greeting message for Duke
     */
    public String hi() {

        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Farewell message for Duke
     */
    public String bye() {

        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles the UI for "list" command
     */
    public String list() {

        return "Here are the tasks in your list:";
    }

    /**
     * Handles the UI for "done" command
     * @param t
     */
    public String done(Task t) {

        return "Nice! I've marked this task as done:\n  " + t;
    }

    /**
     * Handles the UI for "todo", "deadline" and "event" tasks
     * @param t
     * @param size
     */

    public String addTask(Task t, int size) {
        return "Got it. I've added this task:\n  " + t
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Handles the UI for "delete" command
     * @param t
     * @param size
     */
    public String delete(Task t, int size) {
        return "Noted. I've removed this task:\n  " + t +
                "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Handles the UI for "find" command
     */
    public String find() {

        return "Here are the matching tasks in your list:";
    }

    public String help() {
        return "Here are a list of possible commands:\n" +
                "1. 'list' - List all the current tasks\n" +
                "2. 'done <(int) i>' - Mark the i-th task on the list as done\n" +
                "3. 'delete <(int) i>' - Delete the i-th task on the list\n" +
                "4. 'find <word>' - Return a list of tasks that contain <word>\n" +
                "5. To add a task:\n" +
                "  (There are three types of tasks you can add: Todo, Event, Deadline)\n" +
                "  6. 'todo <description>' - Todo: tasks without any date/time attached\n" +
                "  7. 'event <description> /at <date (in a dd/mm/yyyy format)> <time (in a 24-hour format)>'" +
                " - Event: tasks that start at a specific time\n" +
                "  8. 'deadline <description> /by <date (in a dd/mm/yyyy format)> <time (in a 24-hour format)>'" +
                " - Deadline: tasks that need to be done before a specific date/time\n";
    }

}
