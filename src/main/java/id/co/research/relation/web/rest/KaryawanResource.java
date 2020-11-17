package id.co.research.relation.web.rest;

import id.co.research.relation.domain.Karyawan;
import id.co.research.relation.repository.KaryawanRepository;
import id.co.research.relation.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.co.research.relation.domain.Karyawan}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KaryawanResource {

    private final Logger log = LoggerFactory.getLogger(KaryawanResource.class);

    private static final String ENTITY_NAME = "relationInOneTableKaryawan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KaryawanRepository karyawanRepository;

    public KaryawanResource(KaryawanRepository karyawanRepository) {
        this.karyawanRepository = karyawanRepository;
    }

    /**
     * {@code POST  /karyawans} : Create a new karyawan.
     *
     * @param karyawan the karyawan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new karyawan, or with status {@code 400 (Bad Request)} if the karyawan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/karyawans")
    public ResponseEntity<Karyawan> createKaryawan(@Valid @RequestBody Karyawan karyawan) throws URISyntaxException {
        log.debug("REST request to save Karyawan : {}", karyawan);
        if (karyawan.getId() != null) {
            throw new BadRequestAlertException("A new karyawan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Karyawan result = karyawanRepository.save(karyawan);
        return ResponseEntity.created(new URI("/api/karyawans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /karyawans} : Updates an existing karyawan.
     *
     * @param karyawan the karyawan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated karyawan,
     * or with status {@code 400 (Bad Request)} if the karyawan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the karyawan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/karyawans")
    public ResponseEntity<Karyawan> updateKaryawan(@Valid @RequestBody Karyawan karyawan) throws URISyntaxException {
        log.debug("REST request to update Karyawan : {}", karyawan);
        if (karyawan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Karyawan result = karyawanRepository.save(karyawan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, karyawan.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /karyawans} : get all the karyawans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of karyawans in body.
     */
    @GetMapping("/karyawans")
    public List<Karyawan> getAllKaryawans() {
        log.debug("REST request to get all Karyawans");
        return karyawanRepository.findAll();
    }

    /**
     * {@code GET  /karyawans/:id} : get the "id" karyawan.
     *
     * @param id the id of the karyawan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the karyawan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/karyawans/{id}")
    public ResponseEntity<Karyawan> getKaryawan(@PathVariable Long id) {
        log.debug("REST request to get Karyawan : {}", id);
        Optional<Karyawan> karyawan = karyawanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(karyawan);
    }

    /**
     * {@code DELETE  /karyawans/:id} : delete the "id" karyawan.
     *
     * @param id the id of the karyawan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/karyawans/{id}")
    public ResponseEntity<Void> deleteKaryawan(@PathVariable Long id) {
        log.debug("REST request to delete Karyawan : {}", id);
        karyawanRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
