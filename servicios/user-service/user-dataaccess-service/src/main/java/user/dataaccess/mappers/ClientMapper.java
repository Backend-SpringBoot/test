package user.dataaccess.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import user.dataaccess.entities.ClientEntity;
import user.dataaccess.mappers.core.GenericMapper;
import user.test.record.request.ClientRequestRecord;
import user.test.record.response.ClientResponseRecord;

import java.util.List;

@Mapper
public interface ClientMapper extends GenericMapper<ClientEntity, ClientResponseRecord> {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "password", source = "password")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "idNumber", source = "idNumber")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "phone", source = "phone")
    ClientEntity requestRecordToEntity(ClientRequestRecord clientRequestRecord);

    @Override
    @Mapping(target = "name", source = "name")
    @Mapping(target = "idNumber", source = "idNumber")
    ClientResponseRecord entityToResponseRecord(ClientEntity clientEntity);

    List<ClientResponseRecord> entitiesToResponseRecords(List<ClientEntity> clientEntities);
}
