package nyc.c4q.mustafizurmatin.fakebuttonchallenge.models;

/**
 * Created by c4q on 3/6/18.
 */

public class User {
    private String name;
    private String email;
    private String candidate;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.candidate = "bpn123";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
}
