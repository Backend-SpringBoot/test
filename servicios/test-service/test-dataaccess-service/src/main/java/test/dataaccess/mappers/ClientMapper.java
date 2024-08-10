package test.dataaccess.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import test.dataaccess.entities.ClientEntity;
import test.dataaccess.mappers.core.GenericMapper;
import test.test.record.request.ClientRequestRecord;
import test.test.record.response.ClientResponseRecord;

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
    @Mapping(target = "accounts", source = "accounts")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "idNumber", source = "idNumber")
    ClientResponseRecord entityToResponseRecord(ClientEntity clientEntity);

    List<ClientResponseRecord> entitiesToResponseRecords(List<ClientEntity> clientEntities);
}
