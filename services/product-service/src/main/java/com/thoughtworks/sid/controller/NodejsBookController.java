package com.thoughtworks.sid.controller;

import com.thoughtworks.sid.client.NodejsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NodejsBookController {
    private final NodejsFeignClient client;

    @Autowired
    public NodejsBookController(NodejsFeignClient client) {
        this.client = client;
    }

    @RequestMapping("/books/{id}")
    public List book(@PathVariable Long id) {
        return client.findByUid(id);
    }
}
