package model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String birthday;
    private int gender;

    public User(long id,
                String name,
                String login,
                String password,
                String lastName,
                String email,
                int gender,
                String birthday) {

        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }

    public boolean isPasswordValid(String pass) {
        return password.equals(pass);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    private String toGender() {
        if (gender == 0) return "Male";
        return "Female";
    }

    public Map<String, Object> getUserPublicData() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("last_name", lastName);
        userData.put("login", login);
        userData.put("email", email);
        userData.put("gender", toGender());
        userData.put("birthday", birthday);
        return userData;
    }

    @Override
    public String toString() {

        return "ID: " + id
                + ", Name: " + name
                + ", Last name: " + lastName
                + ", Login: " + login
                + ", E-mail: " + email
                + ", Gender: " + toGender()
                + ", Birthday: " + birthday;
    }
}
