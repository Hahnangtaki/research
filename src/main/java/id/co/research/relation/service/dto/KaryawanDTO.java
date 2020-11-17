package id.co.research.relation.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link id.co.research.relation.domain.Karyawan} entity.
 */
public class KaryawanDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 10)
    private String nik;

    private String namaKaryawan;

    private String jabatan;


    private Long karyawanId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public Long getKaryawanId() {
        return karyawanId;
    }

    public void setKaryawanId(Long karyawanId) {
        this.karyawanId = karyawanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KaryawanDTO)) {
            return false;
        }

        return id != null && id.equals(((KaryawanDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KaryawanDTO{" +
            "id=" + getId() +
            ", nik='" + getNik() + "'" +
            ", namaKaryawan='" + getNamaKaryawan() + "'" +
            ", jabatan='" + getJabatan() + "'" +
            ", karyawanId=" + getKaryawanId() +
            "}";
    }
}
