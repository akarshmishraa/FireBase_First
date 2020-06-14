package application.example.firebasefirst;

public class Artist {
    String id;
    String email;
    String password;
    public Artist()
    {

    }

    public Artist(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
