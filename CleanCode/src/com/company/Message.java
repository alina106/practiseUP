package com.company;

import java.sql.Timestamp;

/**
 * Created by alinanamdag on 18.02.16.
 */
public class Message implements Comparable<Message> {

    private String id;
    private String message;
    private String author;
    private Timestamp timestamp;

    public Message(){

    }

    public Message(String id, String author, String message, Timestamp timestamp) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "id:\"" + id + '\"' +
                ",author:\"" + author + '\"' +
                ",message:\"" + message + '\"' +
                ",timestamp:\"" + timestamp + '\"'+
                '}';
    }
    @Override
    public int compareTo(Message o) {
        return this.timestamp.compareTo(o.timestamp);
    }


}
