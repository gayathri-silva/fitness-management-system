package pg222.fitness.controller;

import pg222.fitness.model.Product;
import pg222.fitness.model.User;
import pg222.fitness.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/store")
    public String showStore(Model model) throws IOException {
        model.addAttribute("products", productService.getAllProducts());
        return "store";
    }

    @GetMapping("/manage")
    public String manageProducts(Model model, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("admin")) {
            return "redirect:/api/users/login";
        }

        model.addAttribute("products", productService.getAllProducts());
        return "manage-products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProduct(@PathVariable String id, Model model, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("admin")) {
            return "redirect:/api/users/login";
        }

        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("admin")) {
            return "redirect:/api/users/login";
        }

        productService.saveProduct(product);
        return "redirect:/products/manage";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("admin")) {
            return "redirect:/api/users/login";
        }

        productService.deleteProduct(id);
        return "redirect:/products/manage";
    }
}