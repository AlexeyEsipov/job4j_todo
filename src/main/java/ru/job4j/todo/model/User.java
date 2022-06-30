package ru.job4j.todo.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users"/*,
        uniqueConstraints = @UniqueConstraint(columnNames = {"email"})*/)
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    @Column(unique = true)
    private String email;
    private String phone;

    public User(int id) {
        this.id = id;
    }

    public User() {
    }

    public User(int userId, String userName, String email, String phone) {
        this.id = userId;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
    }

    public User(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public int getUserId() {
        return id;
    }

    public void setUserId(int userId) {
        this.id = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone);
    }

    @Override
    public String toString() {
        return String.format("User{userId=%s, userName=%s email=%s phone=%s}",
                id, userName, email, phone);
    }
}
