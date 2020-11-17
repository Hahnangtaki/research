package id.co.research.relation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Karyawan.
 */
@Entity
@Table(name = "karyawan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Karyawan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "nik", length = 10, nullable = false, unique = true)
    private String nik;

    @Column(name = "nama_karyawan")
    private String namaKaryawan;

    @Column(name = "jabatan")
    private String jabatan;

    @OneToMany(mappedBy = "karyawan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Karyawan> karyawans = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "karyawans", allowSetters = true)
    private Karyawan karyawan;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public Karyawan nik(String nik) {
        this.nik = nik;
        return this;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public Karyawan namaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
        return this;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public Karyawan jabatan(String jabatan) {
        this.jabatan = jabatan;
        return this;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public Set<Karyawan> getKaryawans() {
        return karyawans;
    }

    public Karyawan karyawans(Set<Karyawan> karyawans) {
        this.karyawans = karyawans;
        return this;
    }

    public Karyawan addKaryawan(Karyawan karyawan) {
        this.karyawans.add(karyawan);
        karyawan.setKaryawan(this);
        return this;
    }

    public Karyawan removeKaryawan(Karyawan karyawan) {
        this.karyawans.remove(karyawan);
        karyawan.setKaryawan(null);
        return this;
    }

    public void setKaryawans(Set<Karyawan> karyawans) {
        this.karyawans = karyawans;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public Karyawan karyawan(Karyawan karyawan) {
        this.karyawan = karyawan;
        return this;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Karyawan)) {
            return false;
        }
        return id != null && id.equals(((Karyawan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Karyawan{" +
            "id=" + getId() +
            ", nik='" + getNik() + "'" +
            ", namaKaryawan='" + getNamaKaryawan() + "'" +
            ", jabatan='" + getJabatan() + "'" +
            "}";
    }
}
