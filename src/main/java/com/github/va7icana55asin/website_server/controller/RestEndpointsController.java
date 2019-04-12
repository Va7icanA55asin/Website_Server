package com.github.va7icana55asin.website_server.controller;

import com.github.va7icana55asin.website_server.adapter.ExternalAPIAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/rest")
public class RestEndpointsController {

    private ExternalAPIAdapter apiAdapter;

    @Autowired
    public RestEndpointsController(ExternalAPIAdapter apiAdapter) {
        this.apiAdapter = apiAdapter;
    }

    @GetMapping("/apod")
    public ResponseEntity getNasaAPOD() {
        Map<String, String> response = Collections.singletonMap("url", apiAdapter.getNasaAPOD().block());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
