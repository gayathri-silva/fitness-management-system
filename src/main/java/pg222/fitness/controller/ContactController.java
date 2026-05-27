package pg222.fitness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class ContactController {

    @PostMapping("/contact-us")
    public String handleContactForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message) {
        // Save the form content to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contact-requests.txt", true))) {
            writer.write("Name: " + name + ", Email: " + email + ", Message: " + message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Redirect back to the contact-us page with a success message
        return "redirect:/contact-us?success";
    }
}