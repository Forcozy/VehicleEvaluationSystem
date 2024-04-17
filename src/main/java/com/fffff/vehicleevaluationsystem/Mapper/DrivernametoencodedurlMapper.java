package com.fffff.vehicleevaluationsystem.Mapper;

import com.fffff.vehicleevaluationsystem.Pojo.Drivernametoencodedurl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DrivernametoencodedurlMapper {
    //

    @Update("UPDATE   DRRIVERNAMETOENCODEDURL t\n" +
            "SET t.drivername = #{DRIVERNAME}\n" +
            "where t.id = #{ID}")
    Integer UPDATEDrivernameFindByID(Drivernametoencodedurl drivernametoencodedurl); //更新一个司机记录


    @Select("select * from DRRIVERNAMETOENCODEDURL  order by ID ")
    List<Drivernametoencodedurl> GetAllData(); //获取全部值

    @Insert("insert into DRRIVERNAMETOENCODEDURL(DRIVERNAME, INDATA, UUID) values(#{DRIVERNAME},sysdate,#{UUID})")
    Integer InsertNameCode(Drivernametoencodedurl drivernametoencodedurl); //新增一个司机记录

    @Select("select *  from DRRIVERNAMETOENCODEDURL  where UUID = #{UUID}")
    List<Drivernametoencodedurl> FindDriverByUUID(String UUID);//根据用户UUID找到司机名

    @Select("select count(*) from DRRIVERNAMETOENCODEDURL  order by ID ")
    Integer GetAlldrivernameNum();//获取总数

    @Select("select *  from DRRIVERNAMETOENCODEDURL  where Drivername = #{Drivername}")
    List<Drivernametoencodedurl> FindDriverNameByDriverData(String Drivername);//根据司机名找到司机




}
