package model;

import java.sql.Timestamp;

public class Message {
    private long id;
    private long chatId;
    private long authorId;
    private String content;
    private Timestamp creationTime;

    public Message(long chatId, long authorId, String content, Timestamp creationTime) {
        this.chatId = chatId;
        this.authorId = authorId;
        this.content = content;
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public long getChatId() {
        return chatId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setId(long id) {
        this.id = id;
    }
}
