package edu.vsu.cs.sakovea.service;

import edu.vsu.cs.sakovea.model.Direction;
import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.model.dto.DirectionDTO;
import edu.vsu.cs.sakovea.repository.DirectionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DirectionService {

    private final DirectionRepository directionRepository;

    public DirectionService(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    public List<Direction> getDirectionsByFaculty(Long facultyId) {
        return directionRepository.findByFacultyId(facultyId);
    }

    public void addDirection(DirectionDTO directionDTO, Long facultyId) {
        Direction direction = new Direction();
        direction.setName(directionDTO.getName());

// Устанавливаем связь с факультетом
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        direction.setFaculty(faculty);

        directionRepository.save(direction);
    }
}