package com.wkrzywiec.medium.jokes.randomjokesapi.controller;

import com.wkrzywiec.medium.jokes.randomjokesapi.model.Joke;
import com.wkrzywiec.medium.jokes.randomjokesapi.service.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class JokesController {

    private JokeService jokeService;

    @GetMapping("")
    public Joke getRandomJoke() {
        return jokeService.getRandomJoke();
    }
}
