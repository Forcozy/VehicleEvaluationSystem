package com.fffff.vehicleevaluationsystem.Server.Impl;

import com.fffff.vehicleevaluationsystem.Mapper.CockpitMapper;
import com.fffff.vehicleevaluationsystem.Mapper.DrivernametoencodedurlMapper;
import com.fffff.vehicleevaluationsystem.Mapper.EvaluationrecordsMapper;
import com.fffff.vehicleevaluationsystem.Pojo.*;
import com.fffff.vehicleevaluationsystem.Server.EvaluationrecordsServer;
import com.fffff.vehicleevaluationsystem.Utils.FileUtil;
import com.fffff.vehicleevaluationsystem.Utils.QRCodeGeneratorUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EvaluationrecordsServerImpl implements EvaluationrecordsServer {

    @Autowired
    private EvaluationrecordsMapper evaluationrecordsMapper;


    @Value("${QRCode.save-path}")
    private String savePath;

    @Value("${QRCode.PublicNetworkAddress}")
    private String QRCodePublicNetworkAddress;


    @Autowired
    private DrivernametoencodedurlMapper drivernametoencodedurlMapper;


  private void newtext(){
      // 指定文件路径和名称
      String filePath = savePath+"\\当前无数据.txt";
      // 要写入文件的文本内容
      String content = "当前无数据";

      // 创建文件对象
      File file = new File(filePath);

      // 如果文件不存在，则创建它
      if (!file.exists()) {
          try {
              file.createNewFile();
          } catch (IOException e) {
              System.out.println("文件创建失败：" + e.getMessage());
              return;
          }
      }

      // 使用BufferedWriter写入文件
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
          writer.write(content);
          System.out.println("文件写入成功：" + filePath);
      } catch (IOException e) {
          System.out.println("文件写入失败：" + e.getMessage());
      }

  }

    @Override
    public Integer EvaluationRecordQRCodeGeneration() {
        String PublicNetworkAddress1 = QRCodePublicNetworkAddress; //公网地址

        String ewmURL = "QRCode/"; //公网地址和UUID之间的路由路径
        String pngPath = savePath;//生成的图片存储路径

        FileUtil.ClearDirectory(pngPath);
        FileUtil.CreateDirectoryExample(pngPath);


        List<Drivernametoencodedurl> drivernametoencodedurls = drivernametoencodedurlMapper.GetAllData();
        if (drivernametoencodedurls.isEmpty()){
            newtext();
            FileUtil.ZipFolder(pngPath,pngPath+"\\全部司机对应的二维码(压缩版).zip");
        }else {
            for (int i = 0; i < drivernametoencodedurls.size(); i++) { //遍历全部
                String drivernameURL = PublicNetworkAddress1 + ewmURL + drivernametoencodedurls.get(i).getUUID();
                QRCodeGeneratorUtil.code(drivernameURL,
                        pngPath+"\\"+drivernametoencodedurls.get(i).getDRIVERNAME()+"_"+drivernametoencodedurls.get(i).getUUID()+".png");
            }
            FileUtil.ZipFolder(pngPath,pngPath+"\\全部司机对应的二维码(压缩版).zip");
        }

        return 1;
        /*
        //String PublicNetworkAddress = "http://8.134.182.46:8081/"; //公网地址
        String PublicNetworkAddress = QRCodePublicNetworkAddress;
        String ewmURL = "QRCode/"; //公网地址和UUID之间的路由路径
        //String pngPath = "C:\\Users\\QQ1839913610\\Desktop\\ewm\\ewmlist\\";//生成的图片存储路径
        String pngPath = savepath;


        System.out.println("savepath ==>");
        System.out.println(savepath);
        System.out.println(savepath.getClass());


        FileUtil.ClearDirectory(pngPath);
        FileUtil.CreateDirectoryExample(pngPath);


        List<Drivernametoencodedurl> drivernametoencodedurls = drivernametoencodedurlMapper.GetAllData();
        System.out.println(drivernametoencodedurls.size());
        System.out.println(drivernametoencodedurls);
        System.out.println(drivernametoencodedurls.get(1).getUUID());
        System.out.println(drivernametoencodedurls.get(1).getUUID().getClass());

        for (int i = 0; i < drivernametoencodedurls.size(); i++) { //遍历全部
            String drivernameURL = PublicNetworkAddress + ewmURL + drivernametoencodedurls.get(i).getUUID();
         *//*   System.out.print(drivernameURL);
            System.out.print("   ");
            System.out.print(drivernametoencodedurls.get(i).getDRIVERNAME());
            System.out.println();*//*


            QRCodeGeneratorUtil.code(drivernameURL,
                    pngPath + drivernametoencodedurls.get(i).getDRIVERNAME() + drivernametoencodedurls.get(i).getUUID() + ".png");

        }

        FileUtil.ZipFolder(pngPath, pngPath + "全部司机对应的二维码.zip");

        return 1;
        */


    }

    @Override
    public PageBean<Evaluationrecords> TimePeriodQuery(String startDate, String endDate, int pageNum, int pageSize) {// 按照某一个时间段查询数据
        //1.创建PageBean对象
        PageBean<Evaluationrecords> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<Evaluationrecords> as = evaluationrecordsMapper.TimePeriodQuery(startDate, endDate); // testfycxMapper.list();
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Evaluationrecords> p = (Page<Evaluationrecords>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }

    @Override
    public Integer NumberOfDriversEvaluatedToday() {  //今日被评价司机数:
        return evaluationrecordsMapper.NumberOfDriversEvaluatedToday();
    }

    @Override
    public Integer QueryAllEvaluationDataForTodayAllNum() {// 查询今日所有评价数据的总数
        return evaluationrecordsMapper.QueryAllEvaluationDataForTodayAllNum();
    }

    @Override
    public List<User> QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth() {

        List<User> users = evaluationrecordsMapper.QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth();// 查询上月每天的总评价数
        System.out.println(users.size());
        for (int i = 0; i < users.size(); i++) {
            //System.out.println(users.get(i).toString());
            users.get(i).setUsername(users.get(i).getUsername().substring(0, 10)); // 取前10个字符 (字符串格式2024-03-01 00:00:00.0改成2024-03-01)
            //System.out.println(users.get(i).toString());
        }

        return users;

    }

    //插入新评价记录
    @Override
    public Integer CreateNewCord(Evaluationrecords evaluationrecords) {
        return evaluationrecordsMapper.CreateNewCord(evaluationrecords);
    }

    @Override
    public List<Evaluationrecords> GetAllData() {
        return evaluationrecordsMapper.GetAllData();
    }

    @Override
    public Integer GetAllDataAllNum() {//打印全部数据的数量
        return evaluationrecordsMapper.GetAllDataAllNum();
    }


    public PageBean<Evaluationrecords> findAllByPage(int pageNum, int pageSize) {  //分页成功


        //1.创建PageBean对象
        PageBean<Evaluationrecords> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<Evaluationrecords> as = evaluationrecordsMapper.GetAllData(); // testfycxMapper.list();
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Evaluationrecords> p = (Page<Evaluationrecords>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }


    @Override
    public List<Evaluationrecords> GetPagingEVALUATIONRECORDS(int pageNum, int pageSize) {
        // 这里的page是从0开始的，而MyBatis PageHelper是从1开始的，所以需要做转换
        PageHelper.startPage(pageNum + 1, pageSize);
        return evaluationrecordsMapper.GetAllData();


//
//// 设置第几条记录开始，多少页记录为一页
//        PageHelper.startPage(1,5);
//        // 获取 User，sql 语句为 select * from tb_user
//        // 因为已经注册了 PageHelper插件，所以 PageHelper会在原 sql 语句上增加 limit，从而实现分页
//        List<Evaluationrecords> userList = evaluationrecordsMapper.GetAllData(); // 获得的分好页的结果集
//        System.out.println("GetPagingEVALUATIONRECORDS   userList.size()=>");
//        System.out.println(userList.size());
//        return userList;

    /*
        PageHelper.startPage(pageNum,pageSize);
        List<Evaluationrecords> users = evaluationrecordsMapper.GetAllData();
        PageInfo<Evaluationrecords> pageInfo = new PageInfo<>(users);
        return pageInfo.getList();*/
    }





/*
    //根据起止ID值返回评价记录的分页查找结果
    @Override
    public List<Evaluationrecords> PaginationOfEvaluationRecords(Integer begin, Integer end) {
        return evaluationrecordsMapper.PaginationOfEvaluationRecords(begin,end);
    }
*/


/*
    @Override
    public List<Evaluationrecords> QueryAllEvaluationDataForToday() {// 查询今日所有评价数据(未加分页)
        return evaluationrecordsMapper.QueryAllEvaluationDataForToday();
    }
*/

    public PageBean<Evaluationrecords> QueryAllEvaluationDataForToday(int pageNum, int pageSize) {  // 查询今日所有评价数据(加分页)

        //1.创建PageBean对象
        PageBean<Evaluationrecords> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<Evaluationrecords> as = evaluationrecordsMapper.QueryAllEvaluationDataForToday();
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Evaluationrecords> p = (Page<Evaluationrecords>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }


    public PageBean<Evaluationrecords> QueryAllEvaluationDataForThisMonth(int pageNum, int pageSize) {  // 查询今日所有评价数据(加分页)

        //1.创建PageBean对象
        PageBean<Evaluationrecords> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<Evaluationrecords> as = evaluationrecordsMapper.QueryAllEvaluationDataForThisMonth();
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Evaluationrecords> p = (Page<Evaluationrecords>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }

    @Autowired
    private CockpitMapper cockpitMapper;


    @Override
    public PageBean<DriverRanking> GetAllDriverRankingPaging(int pageNum, int pageSize) {
        //获取评价表的全部值   从高到低排序   显示排名

        //1.创建PageBean对象
        PageBean<DriverRanking> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<DriverRanking> as = cockpitMapper.GetAllDriverRanking();
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<DriverRanking> p = (Page<DriverRanking>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }

    @Override
    public PageBean<DriverRanking> GetAllDriverRankingthismonth(int pageNum, int pageSize) {
        //获取评价表的本月全部值   从高到低排序   显示排名

        //1.创建PageBean对象
        PageBean<DriverRanking> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<DriverRanking> as = cockpitMapper.GetAllDriverRankingthismonth();
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<DriverRanking> p = (Page<DriverRanking>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }

    @Override
    public PageBean<DriverRanking> GetAllDriverRankingToday(int pageNum, int pageSize) {
        //获取评价表的今日全部值   从高到低排序   显示排名

        //1.创建PageBean对象
        PageBean<DriverRanking> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<DriverRanking> as = cockpitMapper.GetAllDriverRankingToday();
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<DriverRanking> p = (Page<DriverRanking>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }


    @Override
    public PageBean<Evaluationrecords> QueryAllDirvernameData(String Dirvername, int pageNum, int pageSize) {
        // 查询某一个司机的全部评价数据

        //1.创建PageBean对象
        PageBean<Evaluationrecords> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<Evaluationrecords> as = evaluationrecordsMapper.QueryAllDirvernameData(Dirvername);
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Evaluationrecords> p = (Page<Evaluationrecords>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }

    @Override
    public PageBean<Evaluationrecords> QueryTodayDirvernameData(String Dirvername, int pageNum, int pageSize) {
        // 查询某一个司机的今天全部评价数据

        // 查询某一个司机的全部评价数据

        //1.创建PageBean对象
        PageBean<Evaluationrecords> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<Evaluationrecords> as = evaluationrecordsMapper.QueryTodayDirvernameData(Dirvername);
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Evaluationrecords> p = (Page<Evaluationrecords>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }

    @Override
    public PageBean<Evaluationrecords> QueryThisMonthDirvernameData(String Dirvername, int pageNum, int pageSize) {
// 查询某一个司机的本月全部评价数据

        //1.创建PageBean对象
        PageBean<Evaluationrecords> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<Evaluationrecords> as = evaluationrecordsMapper.QueryThisMonthDirvernameData(Dirvername);
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Evaluationrecords> p = (Page<Evaluationrecords>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }

    @Override
    public List<User> QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth() {
        return evaluationrecordsMapper.QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth();
    }

    @Override
    public List<User> QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth() {
        return evaluationrecordsMapper.QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth();
    }


}
