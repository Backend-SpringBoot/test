package user.dataaccess.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import user.dataaccess.entities.PersonEntity;
import user.dataaccess.mappers.core.GenericMapper;
import user.test.record.request.PersonRequestRecord;
import user.test.record.response.PersonResponseRecord;

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
