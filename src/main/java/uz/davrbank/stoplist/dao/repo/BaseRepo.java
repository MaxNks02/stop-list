package uz.davrbank.stoplist.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.davrbank.stoplist.dao.model.BaseEntity;

public interface BaseRepo<E extends BaseEntity> extends JpaRepository<E, Long> {
}
