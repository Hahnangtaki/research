package id.co.research.relation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class KaryawanMapperTest {

    private KaryawanMapper karyawanMapper;

    @BeforeEach
    public void setUp() {
        karyawanMapper = new KaryawanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(karyawanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(karyawanMapper.fromId(null)).isNull();
    }
}
