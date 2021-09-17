package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
       // printDeadlines(tasksData);
        printDeadlinesWithStreams(tasksData);
        //printDataWithStreams(tasksData);
      //  System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println(countDeadlinesWithStream(tasksData));
    }


    private static int countDeadlinesWithStream(ArrayList<Task> tasksData) {
        return (int) tasksData.stream().filter((t) -> t instanceof Deadline).count();
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }

    }

    public static void printDataWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing using streams");
        tasks.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlinesWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing using streams");
        tasks.stream()
                .filter(task ->
                    task instanceof Deadline
                ).forEach(System.out::println);
    }

    public static ArrayList<Task> filterTasksUsingString(ArrayList<Task> tasks, String filter) {
        ArrayList<Task> filteredTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(filter)).collect(Collectors.toList());
        return filteredTasks;
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasks) {
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
}
