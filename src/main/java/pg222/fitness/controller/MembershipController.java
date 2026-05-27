package pg222.fitness.controller;

import pg222.fitness.model.Membership;
import pg222.fitness.model.User;
import pg222.fitness.service.MembershipService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/member")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @GetMapping("/dashboard")
    public String memberDashboard(Model model, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("member")) return "redirect:/api/users/login";
        Membership membership = membershipService.getMembership(user.getUsername());
        model.addAttribute("membership", membership);
        return "member-dashboard";
    }
}