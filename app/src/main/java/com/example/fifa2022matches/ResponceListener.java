package com.example.fifa2022matches;

import com.example.fifa2022matches.model.FixtureResponce;

public interface ResponceListener {
    void didfatch(FixtureResponce responce,String message);
    void didError(String message);
}
