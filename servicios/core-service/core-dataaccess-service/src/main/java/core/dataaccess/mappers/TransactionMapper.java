package core.dataaccess.mappers;

import core.dataaccess.mappers.core.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import core.dataaccess.entities.TransactionEntity;
import core.record.request.TransactionRequestRecord;
import core.record.response.TransactionResponseRecord;

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
