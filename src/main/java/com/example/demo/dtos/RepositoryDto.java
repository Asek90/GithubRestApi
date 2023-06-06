package com.example.demo.dtos;

import com.example.demo.githubmodel.Owner;

import java.util.List;

public class RepositoryDto {
    private String name;
    private String owner;
    private List<BranchesDto> branches;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<BranchesDto> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchesDto> branches) {
        this.branches = branches;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
