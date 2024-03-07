package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @Test
    void testFindById() {
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality found = specialitySDJpaService.findById(1L);
        assertThat(found).isNotNull();
        verify(specialtyRepository).findById(1L);
    }

    @Test
    void findByIdTestBDD() {
        // given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality)); // BDD

        // when
        Speciality foundSpeciality = specialitySDJpaService.findById(1L);

        // then
        assertThat(foundSpeciality).isNotNull();
        then(specialtyRepository).should().findById(anyLong()); // BDD
        then(specialtyRepository).should(times(1)).findById(anyLong()); // BDD
        then(specialtyRepository).should(atLeastOnce()).findById(anyLong()); // BDD
    }


    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();
        specialitySDJpaService.delete(speciality);
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void testDeleteByObjectBDD() {
        // given
        Speciality speciality = new Speciality();

        // when
        specialitySDJpaService.delete(speciality);

        // then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void deleteById() {
        specialitySDJpaService.deleteById(1L);
        verify(specialtyRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteByIdBDD() {
        // given - none

        // when
        specialitySDJpaService.deleteById(1L);

        // then
        then(specialtyRepository).should().deleteById(1L);
    }

    @Test
    void deleteByIdAtLeastOnce() {
        specialitySDJpaService.deleteById(1L);
        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        specialitySDJpaService.deleteById(1L);
        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void delete() {
        specialitySDJpaService.delete(new Speciality());
    }
}