package model;

public class Relationship {
    private long userOneId;
    private long userTwoId;
    private byte status;
    private long actionUserId;

    public Relationship(long userOneId, long userTwoId, byte status, long actionUserId) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
        this.status = status;
        this.actionUserId = actionUserId;
    }

    public long getUserOneId() {
        return userOneId;
    }

    public long getUserTwoId() {
        return userTwoId;
    }

    public byte getStatus() {
        return status;
    }

    public long getActionUserId() {
        return actionUserId;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void setActionUserId(long actionUserId) {
        this.actionUserId = actionUserId;
    }
}
