package pg222.fitness.model;

public class GymMember extends User {
    public GymMember(String username, String password, String email) {
        super(username, password, "member", email);
    }
}