package pg222.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;
import pg222.fitness.model.User;
import pg222.fitness.service.FileService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CustomerCareController {
    @Autowired
    private FileService fileService;

    @GetMapping("/customer-care")
    public String viewContactRequests(Model model, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("admin")) {
            return "redirect:/api/users/login";
        }

        List<String> contactRequests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("contact-requests.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contactRequests.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("contactRequests", contactRequests);
        return "customer-care";
    }
}