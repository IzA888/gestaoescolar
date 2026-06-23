package com.example.gestaoescolar.academico.service;

import com.example.gestaoescolar.academico.dto.AlunoDTO;
import com.example.gestaoescolar.academico.entity.Aluno;
import com.example.gestaoescolar.academico.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private AlunoRepository studentRepository;

    @InjectMocks
    private AlunoService studentService;

    private AlunoDTO studentDTO;
    private Aluno student;

    @BeforeEach
    void setUp() {
        studentDTO = new AlunoDTO();
        studentDTO.setName("John Doe");
        studentDTO.setRegistrationNumber("STU001");
        studentDTO.setDateOfBirth(LocalDate.of(2000, 1, 1));
        studentDTO.setEmail("john@example.com");
        studentDTO.setPhone("123456789");
        studentDTO.setAddress("123 Main St");
        studentDTO.setStatus("ACTIVE");

        student = new Aluno();
        student.setId(1L);
        student.setName("John Doe");
        student.setRegistrationNumber("STU001");
        student.setDateOfBirth(LocalDate.of(2000, 1, 1));
        student.setEmail("john@example.com");
        student.setPhone("123456789");
        student.setAddress("123 Main St");
        student.setStatus(Aluno.StatusAluno.ACTIVE);
    }

    @Test
    void testCreateStudent() {
        when(studentRepository.save(any(Aluno.class))).thenReturn(student);

        AlunoDTO result = studentService.create(studentDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("STU001", result.getRegistrationNumber());
        verify(studentRepository, times(1)).save(any(Aluno.class));
    }

    @Test
    void testFindStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<AlunoDTO> result = studentService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testFindStudentByIdNotFound() {
        when(studentRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<AlunoDTO> result = studentService.findById(999L);

        assertFalse(result.isPresent());
        verify(studentRepository, times(1)).findById(999L);
    }

    @Test
    void testFindByRegistrationNumber() {
        when(studentRepository.findByRegistrationNumber("STU001")).thenReturn(Optional.of(student));

        Optional<AlunoDTO> result = studentService.findByRegistrationNumber("STU001");

        assertTrue(result.isPresent());
        assertEquals("STU001", result.get().getRegistrationNumber());
        verify(studentRepository, times(1)).findByRegistrationNumber("STU001");
    }

    @Test
    void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById(1L);

        studentService.delete(1L);

        verify(studentRepository, times(1)).deleteById(1L);
    }
}
