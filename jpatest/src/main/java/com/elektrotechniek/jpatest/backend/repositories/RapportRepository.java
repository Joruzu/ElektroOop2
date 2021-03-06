package com.elektrotechniek.jpatest.backend.repositories;

import com.elektrotechniek.jpatest.backend.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RapportRepository extends JpaRepository<Rapport, Integer> {

    @Query(value = "SELECT * FROM elektrotechniek.rapport WHERE student_studentennummer = :num", nativeQuery = true)
    List<Rapport> rapportByStudId(@Param("num") Integer studNummer);

    @Query(value = "SELECT * FROM elektrotechniek.rapport WHERE YEAR(datum) LIKE CONCAT('%', :jaar, '%') AND" +
            " student_studentennummer LIKE CONCAT('%', :num, '%') AND " +
            "vak_idvak LIKE CONCAT('%', :vak, '%')", nativeQuery = true)
    List<Rapport> rapportByStudentAndYear(@Param("num") String num, @Param("jaar") String jaar,
                                          @Param("vak") String vak);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO elektrotechniek.rapport (student_studentennummer,vak_idvak,datum,cijfer) " +
            "VALUES (:studid, :vakid, :datum, :cijfer)", nativeQuery = true)
    void saveRapport(@Param("studid") Integer studid, @Param("vakid") Integer vakid, @Param("datum") String datum, @Param("cijfer") Float cijfer);

    @Query(value = "SELECT * FROM elektrotechniek.rapport WHERE rapport_id = :id", nativeQuery = true)
    Rapport findOneRapportById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM elektrotechniek.rapport WHERE rapport_id = :id", nativeQuery = true)
    void deleteOneRapport(Integer id);
}



