package ru.paalse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.paalse.persist.User;
import ru.paalse.service.UserRepr;
import ru.paalse.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listPage(Model model, @RequestParam("usernameFilter") Optional<String> usernameFilter) {
        logger.info("List page request");

        List<UserRepr> users;
        if(usernameFilter.isPresent() && !usernameFilter.get().isEmpty()) {
            users =userService.findWithFilter(usernameFilter.get());
        } else {
            users = userService.findAll();
        }
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("user", userService.findById(id)
                .orElseThrow(NotFoundException::new));
        return ("user_form");
    }

    @PostMapping("/update")
    public String update(@Valid UserRepr user, BindingResult result) {
        logger.info("Update end point requested");

        if (result.hasErrors()) {
            return "user_form";
        }

        if (!user.getPassword().equals(user.getMatchingPassword())) {
            result.rejectValue("password", "", "Password not matching");
            return "user_form";
        }

        logger.info("Update user with id {}", user.getId());
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new user requested");

        model.addAttribute("user", new UserRepr());
        return ("user_form");
    }

//    @GetMapping("/{id}/delete")
//    public String remove (@PathVariable("id") long id) {
//        logger.info("Delete user with id {}", id);
//
//        userService.delete(id);
//        return ("redirect:/user");
//    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") long id) {
        logger.info("Delete user with id {}", id);

        userService.delete(id);
        return ("redirect:/user");
    }
}
