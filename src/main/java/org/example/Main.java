package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            String choosenAction = args[0];
            if (choosenAction.equalsIgnoreCase("Add")) {
                String description = args[1];
                TaskManager.addTask(description);
            } else if (choosenAction.equalsIgnoreCase("Update")) {
                try {
                    int id = Integer.parseInt(args[1]);
                    String updatedDescription = args[2];
                    TaskManager.updateTask(id, updatedDescription);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID");
                }
            } else if (choosenAction.equalsIgnoreCase("mark-in-progress")) {
                try {
                    String status = "in-progresss";
                    int id = Integer.parseInt(args[1]);
                    TaskManager.updateStatus(id, status);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID");
                }

            } else if (choosenAction.equalsIgnoreCase("mark-done")) {
                try {
                    String status = "done";
                    int id = Integer.parseInt(args[1]);
                    TaskManager.updateStatus(id, status);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID");
                }
            } else if (choosenAction.equalsIgnoreCase("list")) {
                if(args.length==1) {
                    TaskManager.showAllTasks();
                }
                else{
                    String status=args[1];
                    TaskManager.showByStatus(status);
                }
            }

        }
    }
}