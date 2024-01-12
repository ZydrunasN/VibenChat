package lt.vibenchat.demo.dao;

import java.util.List;

public interface CommonDaoActions<T> {
    void save(T dao);
    void update(T dao);
    List<T> getAll();
    T getByID(Long id);
    void deleteByID(Long id);
}
