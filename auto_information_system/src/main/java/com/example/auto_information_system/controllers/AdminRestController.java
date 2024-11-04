package com.example.auto_information_system.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.auto_information_system.model.Checks;
import com.example.auto_information_system.model.Stillages;
import com.example.auto_information_system.model.Users;
import com.example.auto_information_system.service.AdminService;
import com.example.auto_information_system.service.ChecksService;
import com.example.auto_information_system.service.StillagesService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class AdminRestController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StillagesService stillagesService;
    @Autowired
    private ChecksService checksService;
    @PostMapping("/admin/addNewUser")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody Users entity) {
        try {
            adminService.addNewUser(entity);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/admin/updateUser")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody Users entity) {
        try {
            adminService.updateUser(entity);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin/addStillage")
    public ResponseEntity<HttpStatus> addStillage(@RequestBody Stillages entity) {
        try {
            stillagesService.saveStillage(entity);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin/openChecks")
    public ResponseEntity<HttpStatus> openChecks() {
        try {
            checksService.openChecks();
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin/payChecks")
    public ResponseEntity<HttpStatus> closeChecks(@RequestParam int check_id) {
        try {
            checksService.closeChecks(check_id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/admin/getChecksByLibCardId")
    public List<Checks> getChecksByLibCardId(@RequestParam int libCardId) {
        return checksService.getChecksByLibCardId(libCardId);
    }
    @GetMapping("/admin/getAllUsers")
    public List<Users> getAllUsers() {
        return adminService.getAllUsers();
    }
}
