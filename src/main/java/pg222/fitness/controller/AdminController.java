package pg222.fitness.controller;

import pg222.fitness.model.RenewalRequest;
import pg222.fitness.model.User;
import pg222.fitness.service.MembershipService;
import pg222.fitness.service.RequestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private MembershipService membershipService;

    @GetMapping("/memberships")
    public String viewCurrentMemberships(Model model, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("admin")) return "redirect:/api/users/login";

        model.addAttribute("memberships", membershipService.getAllMembershipsSortedByExpiryDate());
        return "current-memberships";
    }

    @GetMapping("/pending-requests")
    public String viewPendingRequests(Model model, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("admin")) return "redirect:/api/users/login";

        // Get all requests as an array and convert to list for the view
        RenewalRequest[] requestArray = requestService.getPendingRequests().getAll();
        List<RenewalRequest> requestList = Arrays.asList(requestArray);
        model.addAttribute("requests", requestList);

        return "pending-requests";
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("admin")) return "redirect:/api/users/login";

        RenewalRequest[] requestArray = requestService.getPendingRequests().getAll();
        List<RenewalRequest> requestList = Arrays.asList(requestArray);
        model.addAttribute("requests", requestList);
        model.addAttribute("members", membershipService.getAllMembershipsSortedByExpiryDate());

        return "admin-dashboard";
    }

    @PostMapping("/approve")
    public String approveRequest(@RequestParam int requestId) throws IOException {
        requestService.approveRequest(requestId);
        return "redirect:/admin/dashboard";
    }
}
