package com.thoughtworks.sid.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(name = "nodejs-service")
public interface NodejsFeignClient {
    @RequestMapping("/books")
    public List findByUid(@RequestParam("uid") Long id);
}
