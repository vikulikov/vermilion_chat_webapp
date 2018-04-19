package dao;

import model.Relationship;
import model.User;

import java.util.List;

public interface RelationshipsDAO {
    List<Relationship> getAllConnections(User user);
    List<Relationship> getAllConnections(User user, String status);
    byte getStatus(User userOne, User userTwo);
    void addConnection(User userOne, User userTwo, String status);
    void updateStatus(User userOne, User userTwo, String status);
    void deleteConnection(User userOne, User userTwo);
}
