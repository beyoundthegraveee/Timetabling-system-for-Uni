package com.mp1.mas.services;

import com.mp1.mas.entities.Professor;
import com.mp1.mas.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Iterable<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }



    @Override
    public void addProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    @Override
    public Professor getProfessorById(int id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Override
    public void removeProfessor(int id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            System.out.println("Professor with ID " + id + " has been deleted.");
        } else {
            System.out.println("Professor not found.");
        }
    }


}
