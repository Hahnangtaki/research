package id.co.research.relation.service.mapper;


import id.co.research.relation.domain.*;
import id.co.research.relation.service.dto.KaryawanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Karyawan} and its DTO {@link KaryawanDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KaryawanMapper extends EntityMapper<KaryawanDTO, Karyawan> {

    @Mapping(source = "karyawan.id", target = "karyawanId")
    KaryawanDTO toDto(Karyawan karyawan);

    @Mapping(target = "karyawans", ignore = true)
    @Mapping(target = "removeKaryawan", ignore = true)
    @Mapping(source = "karyawanId", target = "karyawan")
    Karyawan toEntity(KaryawanDTO karyawanDTO);

    default Karyawan fromId(Long id) {
        if (id == null) {
            return null;
        }
        Karyawan karyawan = new Karyawan();
        karyawan.setId(id);
        return karyawan;
    }
}
