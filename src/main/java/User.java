import java.time.LocalDate;
import java.util.Date;

public class User implements DTO{
    private Integer id;
    private String lastname;
    private String firstname;
    private Integer age;
    private String deckname;

    public User(){

    }

    public User(String lastname, String firstname, Integer age, String deckname){
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.deckname = deckname;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }
}