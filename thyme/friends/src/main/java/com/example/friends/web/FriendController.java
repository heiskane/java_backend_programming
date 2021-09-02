package com.example.friends.web;

import com.example.friends.domain.Friend;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class FriendController {

    ArrayList<Friend> friendsList = new ArrayList<Friend>();

    @RequestMapping("/index")
    public String friends(Model model) {

        friendsList.add(new Friend("John West"));
        friendsList.add(new Friend("Kate Bower"));

        model.addAttribute("friends", friendsList);

        return "index";
    }

    @PostMapping("/index")
    public String add_friends(@RequestParam String name, Model model) {

        friendsList.add(new Friend(name));

        model.addAttribute("friends", friendsList);

        return "index";
    }
}
