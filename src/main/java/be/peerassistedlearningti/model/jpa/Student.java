package be.peerassistedlearningti.model.jpa;

import be.peerassistedlearningti.common.model.jpa.JPAEntity;

import javax.persistence.Entity;

/**
 * Class used to specify a Student
 *
 * @see JPAEntity
 */
@Entity
public class Student extends JPAEntity<Integer>{

    private String name,
            password,
            email;
    private boolean admin;

    /**
     * Default empty constructor for JPA Entities
     */
    public Student(){}

    /**
     * Constructor for a student entity
     * @param name The name of the student
     * @param password The password of the student
     * @param email The email of the student
     * @param admin The admin value of the student
     */
    public Student(String name, String password, String email, boolean admin){
        setEmail(email);
        setName(name);
        setPassword(password);
        setAdmin(admin);
    }

    /**
     * Gets the name of the Student
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Student
     *
     * @param name The name of the Student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the password of the Student
     *
     * @return The password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the Student
     *
     * @param  password The password of the student
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email of the student
     *
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the student
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the admin value of the student
     *
     * @return The value, 1 = admin; 0 = non-admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets the admin value of the student
     * @param admin The value, 1 = admin; 0 = non-admi
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
