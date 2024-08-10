package test.dataaccess.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import test.dataaccess.entities.TransactionEntity;
import test.dataaccess.mappers.core.GenericMapper;
import test.test.record.request.TransactionRequestRecord;
import test.test.record.response.TransactionResponseRecord;

import java.util.List;

@Mapper
public interface TransactionMapper extends
        GenericMapper<TransactionEntity, TransactionResponseRecord> {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionEntity requestRecordToEntity(TransactionRequestRecord transactionRequestRecord);

    @Override
    @Mapping(target = "client", source = "client")
    TransactionResponseRecord entityToResponseRecord(TransactionEntity transactionEntity);

    List<TransactionResponseRecord> entitiesToResponseRecords(
            List<TransactionEntity> movimientosEntities);
}
