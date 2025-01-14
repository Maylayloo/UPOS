package com.example.backend.service.adminService;

import com.example.backend.model.MajorGroup;
import com.example.backend.repository.MajorGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupManagementForAdminService {

    @Autowired
    private MajorGroupRepository majorGroupRepository;

    public void createGroup(MajorGroup group) {
        majorGroupRepository.save(group);
    }

    public void deleteGroup(Long groupId) {
        majorGroupRepository.deleteById(groupId);
    }
}

