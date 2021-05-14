package Hobe.Restaurant.Service;

import Hobe.Restaurant.Domain.Booking;
import Hobe.Restaurant.Domain.Table;
import Hobe.Restaurant.Repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    private final TableRepository tableDB;

    @Autowired
    public TableService(TableRepository tableDB) {
        this.tableDB = tableDB;
    }
    public List<Table> showTables(){ //현재 가게에 등록된 테이블 전체 다 보여주는 기능
       return tableDB.findAll();
    }
    public boolean checkTable(long id){ //테이블 번호를 줘서 테이블 데이터베이스에 있는지 확인.
       System.out.println(tableDB.findByID(id).get()) ;
       return true;
    }
//    public boolean checkAvailable(long id){
//        System.out.println("checkAvailable : "+tableDB.findByID(id).get().isAvailable());
//        return tableDB.findByID(id).get().isAvailable();
//    }
//    public void changeAvailable(long id){ //테이블 예약 가능한지 검증한 뒤 또는 예약취소,먹고 갔을 때 해당 메소드 실행
//        Table tempTable = tableDB.findByID(id).get();
//        System.out.println("몇번 테이블 예약?"+tempTable.getTableNumber()+tempTable.isAvailable());
//        if(tempTable.isAvailable()) {
//            tempTable.setAvailable(false);
//            System.out.println(tempTable.isAvailable());
//        }//예약을 했으니 이용가능하지 않다고 설정함.
//        else{
//            tempTable.setAvailable(true); //예약을 취소하거나 시간이 경과한 후.
//        }
//
//        System.out.println(tempTable.isAvailable());
//    }
    public void testInputTable(){ //임시용 ...아마도 메인화면 보여질 때 테이블 넣어야될듯.
        Table table1 = new Table();
        table1.setAvailable(true);
        table1.setTableNumber(1);
        table1.setMaxNumber(15);
        tableDB.save(table1);

        Table table2 = new Table();
        table2.setAvailable(true);
        table2.setTableNumber(2);
        table2.setMaxNumber(4);
        tableDB.save(table2);


        Table table3 = new Table();
        table3.setAvailable(true);
        table3.setTableNumber(3);
        table3.setMaxNumber(7);
        tableDB.save(table3);

        Table table4 = new Table();
        table4.setAvailable(true);
        table4.setTableNumber(4);
        table4.setMaxNumber(7);
        tableDB.save(table4);

        Table table5 = new Table();
        table5.setAvailable(true);
        table5.setTableNumber(5);
        table5.setMaxNumber(7);
        tableDB.save(table5);

        Table table6 = new Table();
        table6.setAvailable(true);
        table6.setTableNumber(6);
        table6.setMaxNumber(7);
        tableDB.save(table6);

        System.out.println("테이블 등록이 완료되었습니다.");
    }
    public boolean checkMaxNumber(Booking booking,long TableNumber){
        if(booking.getHowMany() > tableDB.findByID(TableNumber).get().getMaxNumber())
            return false;
        return true;
    }

}
