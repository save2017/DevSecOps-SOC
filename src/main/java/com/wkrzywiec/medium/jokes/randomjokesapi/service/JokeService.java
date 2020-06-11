package com.wkrzywiec.medium.jokes.randomjokesapi.service;

import com.wkrzywiec.medium.jokes.randomjokesapi.model.Joke;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class JokeService {

    private RestTemplate restTemplate;

    public Joke getRandomJoke() {
        String str = restTemplate.getForObject("http://api.icndb.com/jokes/random", String.class);
        JSONObject jsonObject = new JSONObject(str);
        String jokeText = jsonObject.getJSONObject("value").getString("joke");
        Joke joke = new Joke();
        joke.setText(jokeText);
        return joke;
    }
}
