package com.cg.assetmanagementsystem.requestservice.dao;

import com.cg.assetmanagementsystem.requestservice.entity.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestDAO extends CrudRepository<Request,Integer> {
    Iterable<Request> findAllByStatusIgnoreCase(String requestStatus);
}
