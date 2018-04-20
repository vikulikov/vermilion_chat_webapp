package model;

public class Chat {
    private long id;
    private long userOneId;
    private long userTwoId;

    public Chat(long userOneId, long userTwoId) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
    }

    public long getId() {
        return id;
    }

    public long getUserOneId() {
        return userOneId;
    }

    public long getUserTwoId() {
        return userTwoId;
    }

    public void setId(long id) {
        this.id = id;
    }
}
