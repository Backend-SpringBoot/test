package test.dataaccess.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import test.dataaccess.entities.PersonEntity;
import test.dataaccess.mappers.core.GenericMapper;
import test.test.record.request.PersonRequestRecord;
import test.test.record.response.PersonResponseRecord;

import java.util.List;

@Mapper
public interface PersonMapper extends GenericMapper<PersonEntity, PersonResponseRecord> {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonEntity requestRecordToEntity(PersonRequestRecord personRequestRecord);

    @Override
    PersonResponseRecord entityToResponseRecord(PersonEntity personEntity);

    PersonRequestRecord entityToRequestRecord(PersonEntity personEntity);

    List<PersonResponseRecord> entitiesToResponseRecords(List<PersonEntity> personaEntities);
}
