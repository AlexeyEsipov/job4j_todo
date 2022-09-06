package ru.job4j.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    @EqualsAndHashCode.Include
    @Column(unique = true)
    private String email;
    @EqualsAndHashCode.Include
    private String phone;

    public User(int id) {
        this.id = id;
    }

    public User(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("User{userId=%s, userName=%s email=%s phone=%s}",
                id, userName, email, phone);
    }
}
