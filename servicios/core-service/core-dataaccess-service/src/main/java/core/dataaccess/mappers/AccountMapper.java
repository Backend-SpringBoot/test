package core.dataaccess.mappers;

import core.dataaccess.entities.AccountEntity;
import core.dataaccess.mappers.core.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import core.record.request.AccountRequestRecord;
import core.record.response.AccountResponseRecord;

import java.util.List;

@Mapper
public interface AccountMapper extends GenericMapper<AccountEntity, AccountResponseRecord> {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountEntity requestRecordToEntity(AccountRequestRecord accountRequestRecord);

    @Override
    AccountResponseRecord entityToResponseRecord(AccountEntity accountEntity);

    List<AccountResponseRecord> entitiesToResponseRecords(List<AccountEntity> accountEntities);
}
