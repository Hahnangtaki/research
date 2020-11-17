package id.co.research.relation.web.rest;

import id.co.research.relation.RelationInOneTableApp;
import id.co.research.relation.domain.Karyawan;
import id.co.research.relation.repository.KaryawanRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link KaryawanResource} REST controller.
 */
@SpringBootTest(classes = RelationInOneTableApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KaryawanResourceIT {

    private static final String DEFAULT_NIK = "AAAAAAAAAA";
    private static final String UPDATED_NIK = "BBBBBBBBBB";

    private static final String DEFAULT_NAMA_KARYAWAN = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_KARYAWAN = "BBBBBBBBBB";

    private static final String DEFAULT_JABATAN = "AAAAAAAAAA";
    private static final String UPDATED_JABATAN = "BBBBBBBBBB";

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKaryawanMockMvc;

    private Karyawan karyawan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Karyawan createEntity(EntityManager em) {
        Karyawan karyawan = new Karyawan()
            .nik(DEFAULT_NIK)
            .namaKaryawan(DEFAULT_NAMA_KARYAWAN)
            .jabatan(DEFAULT_JABATAN);
        return karyawan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Karyawan createUpdatedEntity(EntityManager em) {
        Karyawan karyawan = new Karyawan()
            .nik(UPDATED_NIK)
            .namaKaryawan(UPDATED_NAMA_KARYAWAN)
            .jabatan(UPDATED_JABATAN);
        return karyawan;
    }

    @BeforeEach
    public void initTest() {
        karyawan = createEntity(em);
    }

    @Test
    @Transactional
    public void createKaryawan() throws Exception {
        int databaseSizeBeforeCreate = karyawanRepository.findAll().size();
        // Create the Karyawan
        restKaryawanMockMvc.perform(post("/api/karyawans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(karyawan)))
            .andExpect(status().isCreated());

        // Validate the Karyawan in the database
        List<Karyawan> karyawanList = karyawanRepository.findAll();
        assertThat(karyawanList).hasSize(databaseSizeBeforeCreate + 1);
        Karyawan testKaryawan = karyawanList.get(karyawanList.size() - 1);
        assertThat(testKaryawan.getNik()).isEqualTo(DEFAULT_NIK);
        assertThat(testKaryawan.getNamaKaryawan()).isEqualTo(DEFAULT_NAMA_KARYAWAN);
        assertThat(testKaryawan.getJabatan()).isEqualTo(DEFAULT_JABATAN);
    }

    @Test
    @Transactional
    public void createKaryawanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = karyawanRepository.findAll().size();

        // Create the Karyawan with an existing ID
        karyawan.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKaryawanMockMvc.perform(post("/api/karyawans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(karyawan)))
            .andExpect(status().isBadRequest());

        // Validate the Karyawan in the database
        List<Karyawan> karyawanList = karyawanRepository.findAll();
        assertThat(karyawanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNikIsRequired() throws Exception {
        int databaseSizeBeforeTest = karyawanRepository.findAll().size();
        // set the field null
        karyawan.setNik(null);

        // Create the Karyawan, which fails.


        restKaryawanMockMvc.perform(post("/api/karyawans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(karyawan)))
            .andExpect(status().isBadRequest());

        List<Karyawan> karyawanList = karyawanRepository.findAll();
        assertThat(karyawanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKaryawans() throws Exception {
        // Initialize the database
        karyawanRepository.saveAndFlush(karyawan);

        // Get all the karyawanList
        restKaryawanMockMvc.perform(get("/api/karyawans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(karyawan.getId().intValue())))
            .andExpect(jsonPath("$.[*].nik").value(hasItem(DEFAULT_NIK)))
            .andExpect(jsonPath("$.[*].namaKaryawan").value(hasItem(DEFAULT_NAMA_KARYAWAN)))
            .andExpect(jsonPath("$.[*].jabatan").value(hasItem(DEFAULT_JABATAN)));
    }
    
    @Test
    @Transactional
    public void getKaryawan() throws Exception {
        // Initialize the database
        karyawanRepository.saveAndFlush(karyawan);

        // Get the karyawan
        restKaryawanMockMvc.perform(get("/api/karyawans/{id}", karyawan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(karyawan.getId().intValue()))
            .andExpect(jsonPath("$.nik").value(DEFAULT_NIK))
            .andExpect(jsonPath("$.namaKaryawan").value(DEFAULT_NAMA_KARYAWAN))
            .andExpect(jsonPath("$.jabatan").value(DEFAULT_JABATAN));
    }
    @Test
    @Transactional
    public void getNonExistingKaryawan() throws Exception {
        // Get the karyawan
        restKaryawanMockMvc.perform(get("/api/karyawans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKaryawan() throws Exception {
        // Initialize the database
        karyawanRepository.saveAndFlush(karyawan);

        int databaseSizeBeforeUpdate = karyawanRepository.findAll().size();

        // Update the karyawan
        Karyawan updatedKaryawan = karyawanRepository.findById(karyawan.getId()).get();
        // Disconnect from session so that the updates on updatedKaryawan are not directly saved in db
        em.detach(updatedKaryawan);
        updatedKaryawan
            .nik(UPDATED_NIK)
            .namaKaryawan(UPDATED_NAMA_KARYAWAN)
            .jabatan(UPDATED_JABATAN);

        restKaryawanMockMvc.perform(put("/api/karyawans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedKaryawan)))
            .andExpect(status().isOk());

        // Validate the Karyawan in the database
        List<Karyawan> karyawanList = karyawanRepository.findAll();
        assertThat(karyawanList).hasSize(databaseSizeBeforeUpdate);
        Karyawan testKaryawan = karyawanList.get(karyawanList.size() - 1);
        assertThat(testKaryawan.getNik()).isEqualTo(UPDATED_NIK);
        assertThat(testKaryawan.getNamaKaryawan()).isEqualTo(UPDATED_NAMA_KARYAWAN);
        assertThat(testKaryawan.getJabatan()).isEqualTo(UPDATED_JABATAN);
    }

    @Test
    @Transactional
    public void updateNonExistingKaryawan() throws Exception {
        int databaseSizeBeforeUpdate = karyawanRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKaryawanMockMvc.perform(put("/api/karyawans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(karyawan)))
            .andExpect(status().isBadRequest());

        // Validate the Karyawan in the database
        List<Karyawan> karyawanList = karyawanRepository.findAll();
        assertThat(karyawanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKaryawan() throws Exception {
        // Initialize the database
        karyawanRepository.saveAndFlush(karyawan);

        int databaseSizeBeforeDelete = karyawanRepository.findAll().size();

        // Delete the karyawan
        restKaryawanMockMvc.perform(delete("/api/karyawans/{id}", karyawan.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Karyawan> karyawanList = karyawanRepository.findAll();
        assertThat(karyawanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
