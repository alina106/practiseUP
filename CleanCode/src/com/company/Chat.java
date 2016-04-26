package com.company;

/**
 * Created by alinanamdag on 23.02.16.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.server.UID;
import java.sql.Timestamp;
import java.util.*;
import javax.json.*;

public class Chat {

    public Chat() throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        List<Message> messages = new ArrayList<>();
        FileWriter writer = new FileWriter("logfile.txt", true);
        writer.write("Usage history: ");
        String choice = new String();

        System.out.println("Please, select what you like to do:");
        //System.out.println("1) Download messages from file.");
        System.out.println("2) Save messeges to file.");
        System.out.println("3) Add new message.");
        System.out.println("4) Show chronological history.");
        System.out.println("5) Delete the message by ID.");
        System.out.println("6) Search for messages by author.");
        System.out.println("7) Search messages by keyword.");
        System.out.println("0) Exit.");

        switcher(consoleReader, messages, writer, choice);
        writer.close();
        consoleReader.close();
    }

    public void switcher(BufferedReader consoleReader, List<Message> messages, FileWriter writer, String choice) throws IOException {
        while (!choice.equals("0")) {
            System.out.println("Please, select what you like to do:");
            choice = consoleReader.readLine();
            switch (choice) {
//                case "1": {   //load from file
//                    //loadingFromFile(messages, writer);
//                    break;
//                }
                case "2": { //save to file
                    writingIntoFile(messages);
                    break;
                }
                case "3": {  // add message
                    addMessage(consoleReader, messages, writer);
                    break;
                }
                case "4": {  // chat history
                    if (!messages.isEmpty()) {
                        Collections.sort(messages);
                        for (Message it : messages)
                            System.out.println(it.toString());
                    } else {
                        System.out.println("Chat history is empty.");
                    }
                    break;
                }
                case "5": {  //deleting
                    deleting(consoleReader, messages, writer);
                    break;
                }
                case "6": {  //searching by author
                    searchingByAuthor(consoleReader, messages, writer);
                    break;
                }
                case "7": { //searching by keyword
                    searchingByKeyword(consoleReader, messages, writer);
                    break;
                }
                case "0": {  //exit
                    System.out.println("Good bye!");
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Error: wrong input! Try again.");
                    writer.write("Error: wrong input!" + "\t" + new Timestamp(System.currentTimeMillis()) + "\n");
                    writer.flush();
                    break;
                }
            }
        }
    }

    public void searchingByKeyword(BufferedReader consoleReader, List<Message> messages, FileWriter writer) throws IOException {
        if (!messages.isEmpty()) {
            System.out.println("Enter the keyword:");
            String word = consoleReader.readLine();
            int amount = 0;
            Boolean found = false;
            for (Message it : messages)
                if (it.getMessage().contains(word)) {
                    found = true;
                    amount++;
                    System.out.println(it.toString());
                }
            if (!found)
                System.out.println("No message contains \"" + word + "\"");

            writer.write(" Request: searching by keyword on " + new Timestamp(System.currentTimeMillis())
                    + "\n \t Amount of found messages  - " + amount + "\n");
            writer.flush();
        } else {
            System.out.println("Messages not found: chat history is empty.");
        }
    }

    public void searchingByAuthor(BufferedReader consoleReader, List<Message> messages, FileWriter writer) throws IOException {
        if (!messages.isEmpty()) {
            System.out.println("Enter the author's name:");
            String name = consoleReader.readLine();
            Boolean found = false;
            int amount = 0;
            for (Message message : messages)
                if (message.getAuthor().equals(name)) {
                    found = true;
                    amount++;
                    System.out.println(message.toString());
                }
            if (!found)
                System.out.println("No message from user " + name + " found.");
            writer.write(" Request: searching by author on " + new Timestamp(System.currentTimeMillis())
                    + "\n \t Amount of deleted messages  - " + amount + "\n");
            writer.flush();
        } else {
            System.out.println("Messages not found: chat history is empty.");
        }
    }

    public void deleting(BufferedReader consoleReader, List<Message> messages, FileWriter writer) throws IOException {
        if (!messages.isEmpty()) {
            System.out.println("Enter an id of message you want to delete:");
            String index = consoleReader.readLine();
            int size = messages.size();
            for (int i = 0; i < messages.size(); i++)
                if (messages.get(i).getId().equals(index)) {
                    messages.remove(i);
                    System.out.println("Successfully deleted!");
                    break;
                }
            if (size == messages.size())
                System.out.println("No message to delete: ID not found.");
            writer.write(" Request: deleting on " + new Timestamp(System.currentTimeMillis())
                    + "\n \t Amount of deleted messages  - " + (size - messages.size()) + "\n");
            writer.flush();
        } else {
            System.out.println("No message to delete: chat history is empty.");
        }
    }

    public void addMessage(BufferedReader consoleReader, List<Message> messages, FileWriter writer) throws IOException {

        System.out.println("Enter your name:");
        String userName = consoleReader.readLine();
        System.out.println("Enter your message:");
        String userMes = consoleReader.readLine();

        int maxSimbols = 280;

        if (userMes.length() > maxSimbols) {
            System.out.println("Message is too large (more than "+maxSimbols+" letters).");
            writer.write("Warning! Message is too large (more than "+maxSimbols+" letters) on " + new Timestamp(System.currentTimeMillis()).toString() + "\n");
            writer.flush();
            return;
        }
        Message tempMes = new Message(new UID().toString(), userName, userMes, new Timestamp(System.currentTimeMillis()));
        messages.add(tempMes);
        System.out.println(tempMes.toString());
    }

    public void writingIntoFile(List<Message> messages) throws IOException {
        if (!messages.isEmpty()) {
            File jfile = new File("chat.json");
            FileWriter fileWriter = new FileWriter(jfile);
            JsonWriter jsonWriter = Json.createWriter(fileWriter);
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Message message : messages) {
                arrayBuilder.add(
                        Json.createObjectBuilder()
                                .add("id", message.getId())
                                .add("author", message.getAuthor())
                                .add("timestamp", message.getTimestamp().getTime())
                                .add("message", message.getMessage())
                                .build());
            }
            JsonArray jsonArray = arrayBuilder.build();
            jsonWriter.writeArray(jsonArray);
            jsonWriter.close();
            System.out.println("Chat history has been recorded.");

        } else {
            System.out.println("No message to record: chat history is empty.");
        }
    }
}
