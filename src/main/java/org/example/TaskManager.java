package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskManager {
    private static final String PATH="C:\\Users\\sandeep yadav\\IdeaProjects\\Task_tracker\\src\\main\\java\\org\\example\\tasks.json";
    private static final ObjectMapper objectMapper=new ObjectMapper();
    private static ArrayNode arrayNode;
    private static final File file=new File(PATH);
    public static void initialise() throws IOException{
        try{
            if(file.exists()&&file.length()>0){
                arrayNode=(ArrayNode)objectMapper.readTree(file);
            }
            else{
                arrayNode=objectMapper.createArrayNode();
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
    public static String getFormattedDateTime(){
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter formatPattern=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return now.format(formatPattern);
    }
    public static void addTask(String description) throws IOException {
        ObjectNode objectNode=objectMapper.createObjectNode();

        //initialising the arrayNode
         initialise();
        //calculating the id for the current task
        int id=1;
        if(!arrayNode.isEmpty()){
            ObjectNode lastNode=(ObjectNode) arrayNode.get(arrayNode.size()-1);
            id=lastNode.get("id").asInt() + 1;
        }
        //getting and formatting the current date and time
        String formattedNow=getFormattedDateTime();
        //putting all the values inside the current task
        objectNode.put("id",id);
        objectNode.put("description",description);
        objectNode.put("status","todo");
        objectNode.put("Created At",formattedNow);
        objectNode.put("Updated At",formattedNow);
        //adding in the json array(tasks.json)
        arrayNode.add(objectNode);
        //writing back to the tasks.json
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,arrayNode);
        System.out.println("Task added successfully! id:- "+id);
    }

    public static void updateTask(int id,String updatedDescription) throws IOException{
        initialise();
        if(id<=0||id>arrayNode.size()){
            System.out.println("Invalid ID, No task with this id");
        }
        else{
            for(var x:arrayNode){
                ObjectNode objectNode=(ObjectNode)x;
                if(objectNode.get("id").asInt()==id){
                    objectNode.put("description",updatedDescription);
                    String formattedNow=getFormattedDateTime();
                    objectNode.put("Updated At",formattedNow);
                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,arrayNode);
                    System.out.println("Update Successful for task with id:- "+id);
                    break;
                }
            }

        }
    }
    public static void updateStatus(int id,String updatedStatus) throws IOException{
        initialise();
        if(id<=0||id>arrayNode.size()){
            System.out.println("Invalid ID, No task with this id");
        }
        else{
            for(var x:arrayNode){
                ObjectNode objectNode=(ObjectNode)x;
                if(objectNode.get("id").asInt()==id) {
                    objectNode.put("status", updatedStatus);
                    String formattedNow = getFormattedDateTime();
                    objectNode.put("Updated At", formattedNow);
                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, arrayNode);
                    System.out.println("Update Successful for task with id:- " + id);
                    break;
                }
            }

        }
    }
    public static void showAllTasks() throws IOException{
        initialise();
        if(arrayNode.isEmpty()){
            System.out.println("No Tasks Created!!");
        }
        else{
            System.out.println(arrayNode.toPrettyString());
        }
    }
    public static void showByStatus(String status) throws IOException{
        initialise();
        if(arrayNode.isEmpty()){
            System.out.println("No Tasks Created!!");
        }
        else{
            System.out.println("herer");
            System.out.println(status);
           for(var nodes:arrayNode){
               ObjectNode objectNode=(ObjectNode) nodes;
               if(objectNode.get("status").asText().equals(status)){
                   System.out.println(objectNode.toPrettyString());
               }
           }
        }
    }
}
