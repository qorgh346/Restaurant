package Hobe.Restaurant.Repository;

import Hobe.Restaurant.Domain.Table;

import java.util.List;
import java.util.Optional;

public interface TableRepository {
    Table save(Table table);
    Optional<Table> findByID(long id);
    List<Table>findAll();

}
