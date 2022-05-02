package com.example.dao.impl;

import com.example.dao.Dao;
import com.example.entity.Branch;
import com.example.exception.ApplicationException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 */
public class BranchDao implements Dao<Branch, String> {

    private static final Map<String, Branch> branchMap = new HashMap<>();
    private static final BranchDao instance = new BranchDao();

    private BranchDao() {
    }

    public static BranchDao getInstance() {
        return instance;
    }


    @Override
    public Optional<Branch> findById(String id) {
        return branchMap.containsKey(id) ? Optional.of(branchMap.get(id)) : Optional.empty();
    }

    @Override
    public Branch save(Branch entity) {
        if (branchMap.containsKey(entity.getName())) {
            throw new ApplicationException(ApplicationException.Code.CONFLICT, "This branch already exist in the database.");
        }
        return branchMap.put(entity.getName(), entity);
    }

    @Override
    public Branch update(Branch entity) {
        return branchMap.put(entity.getName(), entity);
    }

    @Override
    public Collection<Branch> getAll() {
        return branchMap.values();
    }
}
