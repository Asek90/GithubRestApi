package com.example.demo.controllers;

import com.example.demo.dtos.RepositoryDto;
import com.example.demo.githubmodel.Branch;
import com.example.demo.githubmodel.Repository;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class GitHubRestController {


    @GetMapping(value = "/repositories/{username}", produces = "application/json")
    @ResponseBody
    public Repository[] listUserRepositories(@PathVariable String username) {
        String apiUrl = "https://api.github.com/users/" + username + "/repos";

        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        ResponseEntity<Repository[]> response;

        try {
            response = restTemplate.getForEntity(apiUrl, Repository[].class);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Not found");
            } else if (e.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
                throw new ForbiddenException(HttpStatus.FORBIDDEN,"Forbidden access");
            }
            throw new IllegalStateException(e);
        }

        Repository[] repositories = response.getBody();

        for (Repository repositoryWoBranch : repositories) {
            String owner = repositoryWoBranch.getOwner().getLogin();
            String name = repositoryWoBranch.getName();
            repositoryWoBranch.setBranches(getRepositoryBranches(owner, name));
        }
        return repositories;
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex, WebRequest request) {

        return new ResponseEntity<Object>(
                ex, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({ ForbiddenException.class })
    public ResponseEntity<Object> handleForbidden(ResourceNotFoundException ex, WebRequest request) {

        return new ResponseEntity<Object>(

                ex, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }


    @GetMapping("/repositories/{owner}/{repo}/branches")
    @ResponseBody
    public List<Branch> getRepositoryBranches(@PathVariable String owner, @PathVariable String repo) {
        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/branches";

        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        ResponseEntity<Branch[]> response = restTemplate.getForEntity(apiUrl, Branch[].class);

        Branch[] branches = response.getBody();
        List<Branch> branchList = Arrays.asList(branches);

        return branchList;
    }

}


