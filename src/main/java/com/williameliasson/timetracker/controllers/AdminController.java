package com.williameliasson.timetracker.controllers;

import com.williameliasson.timetracker.dto.UserSummaryDTO;
import com.williameliasson.timetracker.models.User;
import com.williameliasson.timetracker.services.AdminService;
import com.williameliasson.timetracker.services.SessionService;
import com.williameliasson.timetracker.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }
    @GetMapping("/summaries")
    public List<UserSummaryDTO> getAllUserSummaries() {
        return adminService.getAllUserSummaries();
    }
    
}
