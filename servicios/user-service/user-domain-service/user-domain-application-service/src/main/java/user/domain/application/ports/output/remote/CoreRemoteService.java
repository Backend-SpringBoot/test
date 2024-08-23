package user.domain.application.ports.output.remote;

import java.util.LinkedHashMap;
import java.util.List;

public interface CoreRemoteService {

  List<LinkedHashMap<String, Object>> findByIdNumber(String id);

}
