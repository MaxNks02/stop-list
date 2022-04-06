package uz.davrbank.stoplist.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.davrbank.stoplist.dao.model.StopListEntity;

@Repository
public interface StopListRepo extends BaseRepo<StopListEntity> {
}
