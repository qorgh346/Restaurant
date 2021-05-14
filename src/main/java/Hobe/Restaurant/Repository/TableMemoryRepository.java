package Hobe.Restaurant.Repository;

import Hobe.Restaurant.Domain.Table;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TableMemoryRepository implements TableRepository{
    Map<Long,Table> tableDB = new HashMap<>();

    @Override
    public Table save (Table table) {
        tableDB.put(table.getTableNumber(),table);
        System.out.println("Table을 Save 하는 코드:"+table.getTableNumber());
        return table;
    }

    @Override
    public Optional<Table> findByID(long id) {
        return Optional.ofNullable(tableDB.get(id));
    }

    @Override
    public List<Table> findAll() {
        return new ArrayList<>(tableDB.values());
    }


}
