package id.co.research.relation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.co.research.relation.web.rest.TestUtil;

public class KaryawanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KaryawanDTO.class);
        KaryawanDTO karyawanDTO1 = new KaryawanDTO();
        karyawanDTO1.setId(1L);
        KaryawanDTO karyawanDTO2 = new KaryawanDTO();
        assertThat(karyawanDTO1).isNotEqualTo(karyawanDTO2);
        karyawanDTO2.setId(karyawanDTO1.getId());
        assertThat(karyawanDTO1).isEqualTo(karyawanDTO2);
        karyawanDTO2.setId(2L);
        assertThat(karyawanDTO1).isNotEqualTo(karyawanDTO2);
        karyawanDTO1.setId(null);
        assertThat(karyawanDTO1).isNotEqualTo(karyawanDTO2);
    }
}
