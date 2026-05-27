package pg222.fitness.service;

import pg222.fitness.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private FileService fileService;

    @Autowired
    private MembershipService membershipService; // Inject MembershipService

    public boolean registerUser(User user) throws IOException {
        // Check for duplicate username or email in users.txt
        List<String> lines = fileService.readFile("users.txt");
        for (String line : lines) {
            // Skip empty or malformed lines
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            // Ensure the line has at least 4 parts (username, password, role, email)
            if (parts.length < 4) {
                continue;
            }
            if (parts[0].equals(user.getUsername()) || parts[3].equals(user.getEmail())) {
                return false; // Duplicate username or email
            }
        }
        // Add the user to users.txt
        fileService.appendToFile("users.txt", user.toString());

        // Add the user to memberships.txt with inactive status and tier 1 (if a member)
        if ("member".equals(user.getRole())) {
            String usernameToMembership = user.getUsername();
            Membership memberOnRegister = new Membership(usernameToMembership, "inactive", "2024-01-01", 1);
            membershipService.updateMembership(memberOnRegister); // Use MembershipService
        }
        return true;
    }

    public User login(String username, String password) throws IOException {
        List<String> lines = fileService.readFile("users.txt");
        for (String line : lines) {
            // Skip empty or malformed lines
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            // Ensure the line has at least 4 parts (username, password, role, email)
            if (parts.length < 4) {
                continue;
            }
            if (parts[0].equals(username) && parts[1].equals(password)) {
                return parts[2].equals("member") ?
                        new GymMember(parts[0], parts[1], parts[3]) :
                        new Admin(parts[0], parts[1], parts[3]);
            }
        }
        return null;
    }

    public void updateUser(String username, String newEmail, String newPassword) throws IOException {
        List<String> lines = fileService.readFile("users.txt");
        List<String> updatedLines = new ArrayList<>();
        for (String line : lines) {
            // Skip empty or malformed lines
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            // Ensure the line has at least 4 parts (username, password, role, email)
            if (parts.length < 4) {
                continue;
            }
            if (parts[0].equals(username)) {
                updatedLines.add(parts[0] + "," + newPassword + "," + parts[2] + "," + newEmail);
            } else {
                updatedLines.add(line);
            }
        }
        fileService.writeFile("users.txt", updatedLines);
    }
}