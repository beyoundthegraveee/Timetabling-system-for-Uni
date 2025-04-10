package com.mp1.mas.services;

import com.mp1.mas.entities.Professor;

import java.util.List;

public interface ProfessorService {


    Iterable<Professor> getAllProfessors();

    void addProfessor(Professor professor);

    void removeProfessor(int id);

    Professor getProfessorById(int id);


}
