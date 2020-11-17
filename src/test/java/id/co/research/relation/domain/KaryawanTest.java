package id.co.research.relation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.co.research.relation.web.rest.TestUtil;

public class KaryawanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Karyawan.class);
        Karyawan karyawan1 = new Karyawan();
        karyawan1.setId(1L);
        Karyawan karyawan2 = new Karyawan();
        karyawan2.setId(karyawan1.getId());
        assertThat(karyawan1).isEqualTo(karyawan2);
        karyawan2.setId(2L);
        assertThat(karyawan1).isNotEqualTo(karyawan2);
        karyawan1.setId(null);
        assertThat(karyawan1).isNotEqualTo(karyawan2);
    }
}
