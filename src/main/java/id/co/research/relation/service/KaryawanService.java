package id.co.research.relation.service;

import id.co.research.relation.domain.Karyawan;
import id.co.research.relation.repository.KaryawanRepository;
import id.co.research.relation.service.dto.KaryawanDTO;
import id.co.research.relation.service.mapper.KaryawanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Karyawan}.
 */
@Service
@Transactional
public class KaryawanService {

    private final Logger log = LoggerFactory.getLogger(KaryawanService.class);

    private final KaryawanRepository karyawanRepository;

    private final KaryawanMapper karyawanMapper;

    public KaryawanService(KaryawanRepository karyawanRepository, KaryawanMapper karyawanMapper) {
        this.karyawanRepository = karyawanRepository;
        this.karyawanMapper = karyawanMapper;
    }

    /**
     * Save a karyawan.
     *
     * @param karyawanDTO the entity to save.
     * @return the persisted entity.
     */
    public KaryawanDTO save(KaryawanDTO karyawanDTO) {
        log.debug("Request to save Karyawan : {}", karyawanDTO);
        Karyawan karyawan = karyawanMapper.toEntity(karyawanDTO);
        karyawan = karyawanRepository.save(karyawan);
        return karyawanMapper.toDto(karyawan);
    }

    /**
     * Get all the karyawans.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<KaryawanDTO> findAll() {
        log.debug("Request to get all Karyawans");
        return karyawanRepository.findAll().stream()
            .map(karyawanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one karyawan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<KaryawanDTO> findOne(Long id) {
        log.debug("Request to get Karyawan : {}", id);
        return karyawanRepository.findById(id)
            .map(karyawanMapper::toDto);
    }

    /**
     * Delete the karyawan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Karyawan : {}", id);
        karyawanRepository.deleteById(id);
    }
}
