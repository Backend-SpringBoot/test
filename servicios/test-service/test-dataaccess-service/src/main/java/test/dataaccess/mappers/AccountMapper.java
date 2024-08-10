package test.dataaccess.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import test.dataaccess.entities.AccountEntity;
import test.dataaccess.mappers.core.GenericMapper;
import test.test.record.request.AccountRequestRecord;
import test.test.record.response.AccountResponseRecord;

import java.util.List;

@Mapper
public interface AccountMapper extends GenericMapper<AccountEntity, AccountResponseRecord> {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountEntity requestRecordToEntity(AccountRequestRecord accountRequestRecord);

    @Override
    AccountResponseRecord entityToResponseRecord(AccountEntity accountEntity);

    List<AccountResponseRecord> entitiesToResponseRecords(List<AccountEntity> accountEntities);
}
