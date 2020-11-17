package id.co.research.relation.repository;

import id.co.research.relation.domain.Karyawan;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Karyawan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {
}
