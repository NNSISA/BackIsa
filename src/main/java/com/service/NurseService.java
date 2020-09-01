package com.service;

import com.model.Nurse;
import com.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseService {

    @Autowired
    private NurseRepository nr;

    public Nurse findByUsername(String username) {
        return (Nurse) nr.findByUsername(username);
    }

    public Nurse save(Nurse patient) {
        return nr.save(patient);
    }
}
