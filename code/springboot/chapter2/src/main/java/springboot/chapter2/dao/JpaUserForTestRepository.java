package springboot.chapter2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.chapter2.pojo.UserForJpa;

import java.util.List;

public interface JpaUserForTestRepository extends JpaRepository<UserForJpa, Long> {
    List<UserForJpa> findByNameLike(String name);

    List<UserForJpa> getUserForTestsByIdAfter(Long id);

    void deleteUserForTestByIdEndsWith(Long id);
}
