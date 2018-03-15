package MUN.Factory;



public class UserForm {

private String email;
private String firstName;
private String lastName;
private String pwd;

    public UserForm(String email, String firstName, String lastName, String pwd) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pwd = pwd;
    }
    public UserForm(){

    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPwd() {
        return pwd;
    }
}
