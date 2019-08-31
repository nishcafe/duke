package tool;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;


public class Parser {
    protected TaskList commands;
    protected Storage storage;
    protected Ui ui;


    public Parser(TaskList commands, Storage storage, Ui ui) {
        this.commands = commands;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses the user's input to make sense of all the commands, and hands it over
     * to tool.TaskList
     * @param command
     */
    public void parse(String command) {
            String[] inputArr = command.split(" ");
            String userCommand = inputArr[0];
            try {
                if (userCommand.equals("bye")) {
                    this.ui.bye();
                    this.storage.close(this.commands);
                } else if (userCommand.equals("list")) {
                    this.ui.list();
                    this.commands.list();
                } else if (userCommand.equals("done")) {
                    try {
                        int index = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task doneTask = this.commands.done(index);
                            this.ui.done(doneTask);
                            storage.done(doneTask, index + 1);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for done does not exist in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! Index for done cannot be empty.");
                    }
                } else if (userCommand.equals("deadline")) {
                    try {
                        String dL = command.split(" ", 2)[1];
                        try {
                            String[] taskDeadLine = dL.split(" /by ");
                            String taskD = taskDeadLine[0];
                            String by = taskDeadLine[1];
                            Task tt = new Deadline(taskD, new DateTime(by));
                            this.commands.add(tt);
                            this.ui.addTask(tt, this.commands.size());
                            storage.save(tt);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The format for deadline is wrong. Please follow: <description> /by <time>");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
                    }
                } else if (userCommand.equals("event")) {
                    try {
                        String eEvent = command.split(" ", 2)[1];
                        try {
                            String[] taskEvent = eEvent.split(" /at ");
                            String taskE = taskEvent[0];
                            String at = taskEvent[1];
                            Task ee = new Event(taskE, new DateTime(at));
                            this.commands.add(ee);
                            this.ui.addTask(ee, this.commands.size());
                            storage.save(ee);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The format for event is wrong. Please follow: <description> /at <time>");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of event cannot be empty.");
                    }
                } else if (userCommand.equals("todo")) {
                    try {
                        String todoT = command.split(" ", 2)[1];
                        Task t = new Todo(todoT);
                        this.commands.add(t);
                        this.ui.addTask(t, this.commands.size());
                        storage.save(t);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (userCommand.equals("delete")) {
                    try {
                        int i = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task tt = this.commands.delete(i);
                            this.ui.delete(tt, this.commands.size());
                            storage.delete(i + 1);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for delete does not exist in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! Index for delete cannot be empty.");
                    }
                } else if (userCommand.equals("find")) {
                    String word = inputArr[1];
                    this.ui.find();
                    this.commands.find(word);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

    }
}