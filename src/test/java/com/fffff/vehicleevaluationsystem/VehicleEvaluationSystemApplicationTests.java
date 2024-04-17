package com.fffff.vehicleevaluationsystem;

import com.fffff.vehicleevaluationsystem.Mapper.DrivernametoencodedurlMapper;
import com.fffff.vehicleevaluationsystem.Mapper.EvaluationrecordsMapper;
import com.fffff.vehicleevaluationsystem.Mapper.UserMapper;
import com.fffff.vehicleevaluationsystem.Pojo.*;
import com.fffff.vehicleevaluationsystem.Server.Impl.CockpitServerImpl;
import com.fffff.vehicleevaluationsystem.Server.Impl.DriverManagementServerImpl;
import com.fffff.vehicleevaluationsystem.Server.Impl.DrivernametoencodedurlServerImpl;
import com.fffff.vehicleevaluationsystem.Server.Impl.EvaluationrecordsServerImpl;
//import com.fffff.vehicleevaluationsystem.Server.Impl.testYourEntityService;
//import com.fffff.vehicleevaluationsystem.Server.Impl.testfycxService;
import com.fffff.vehicleevaluationsystem.Utils.*;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@SpringBootTest
class TESTVehicleEvaluationSystemApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EvaluationrecordsMapper evaluationrecordsMapper;
    @Autowired
    private CockpitServerImpl cockpitServerImpl;
    @Autowired
    private DrivernametoencodedurlMapper drivernametoencodedurlMapper;
    @Autowired
    private DrivernametoencodedurlServerImpl drivernametoencodedurlServerImpl;
    @Autowired
    private EvaluationrecordsServerImpl evaluationrecordsServerimpl;
    @Autowired
    private EvaluationrecordsServerImpl evaluationrecordsServer;
    @Autowired
    private DriverManagementServerImpl driverManagementServer;


    @Test
    void GetAllData111() {   //测试日历图值的最大值的数为多少
        System.out.println(" GetAllData ==>");
        List<User> users = evaluationrecordsServerimpl.QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth();
        if (users.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(users.get(0).getPassword1());
        }
    }



    @Test
    void GetAllData11112() {   //token获取用户名
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsidXNlcm5hbWUiOiJhZG1pbiJ9LCJleHAiOjE3MTMxNTI0NjN9.xwKUiMzY9WhcUDAU0MBr-qLA6VGbudUzG1iVXwUho3E";
        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");
        System.out.println("username =");
        System.out.println(username);
    }




    @Test
    void GetAllData1111() {   //测试日历图值的最大值的数为多少
        System.out.println(" GetAllData ==>");
        List<User> users = evaluationrecordsServerimpl.QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth();
        if (users.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(users.get(0).getPassword1());
        }
    }

    @Test
    void GetAllData1111121() {   // 按照某一个时间段查询数据
        String startDate = "2024-04-01 00:00:00";
        String endDate = "2024-04-02 00:00:00";

        List<Evaluationrecords> evaluationrecords = evaluationrecordsMapper.TimePeriodQuery(startDate, endDate);
        System.out.println(evaluationrecords.size());
        for (int i = 0; i < evaluationrecords.size(); i++) {
            System.out.println(evaluationrecords.get(i));
        }

    }


    @Test
    void GetAllData11121() {   //查询今日评价数总数
        Integer integer = evaluationrecordsMapper.QueryAllEvaluationDataForTodayAllNum();
        System.out.println(integer);
    }

    @Test
    void GetAllData() {
        System.out.println(" GetAllData ==>");
        PageBean<Drivernametoencodedurl> l = driverManagementServer.GetAllDriverData(1, 10);
        // List<Drivernametoencodedurl> l2 = driverManagementServer.GetAllDriverData(1,10);
   /*     for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }*/
        System.out.println(l.toString());
        System.out.println(l.getItems().size());
        for (int i = 0; i < l.getItems().size(); i++) {
            System.out.println(l.getItems().get(i));
        }

    }//打印全部可用


    @Test
    void drivernametoencodedurlMapperFindDriverNameByDriverData() {  //按照司机名查司机（✔）
        List<Drivernametoencodedurl> p = drivernametoencodedurlMapper.FindDriverNameByDriverData("张三");
        System.out.println(p.size());
        System.out.println(p.toString());
    }

    @Test
    void UPDATEDrivernameFindByID() {
        Drivernametoencodedurl drivernametoencodedurl = new Drivernametoencodedurl();
        drivernametoencodedurl.setID(399);
        drivernametoencodedurl.setDRIVERNAME("65465");
        Integer integer = drivernametoencodedurlServerImpl.UPDATEDrivernameFindByID(drivernametoencodedurl);
        System.out.println("integer =>");
        System.out.println(integer);
    }







    @Value("${QRCode.save-path}")
    private String savepath;

    @Value("${QRCode.PublicNetworkAddress}")
    private String QRCodePublicNetworkAddress;
    @Test
    void testewmsc() {  //测试二维码生成
        //String PublicNetworkAddress = "http://8.134.182.46:8081/"; //公网地址
        String PublicNetworkAddress = QRCodePublicNetworkAddress;


        String ewmURL = "QRCode/"; //公网地址和UUID之间的路由路径
        //String pngPath = "C:\\Users\\QQ1839913610\\Desktop\\ewm\\ewmlist\\";//生成的图片存储路径
        String pngPath =savepath ;




        System.out.println("savepath ==>");
        System.out.println(savepath);
        System.out.println(savepath.getClass());


        FileUtil.ClearDirectory(pngPath);
        FileUtil.CreateDirectoryExample(pngPath);


        List<Drivernametoencodedurl> drivernametoencodedurls = drivernametoencodedurlServerImpl.GetAllData();
        System.out.println(drivernametoencodedurls.size());
        System.out.println(drivernametoencodedurls);
        System.out.println(drivernametoencodedurls.get(1).getUUID());
        System.out.println(drivernametoencodedurls.get(1).getUUID().getClass());

        for (int i = 0; i < drivernametoencodedurls.size(); i++) { //遍历全部
            String drivernameURL = PublicNetworkAddress + ewmURL + drivernametoencodedurls.get(i).getUUID();
         /*   System.out.print(drivernameURL);
            System.out.print("   ");
            System.out.print(drivernametoencodedurls.get(i).getDRIVERNAME());
            System.out.println();*/


            QRCodeGeneratorUtil.code(drivernameURL,
                    pngPath + drivernametoencodedurls.get(i).getDRIVERNAME()+drivernametoencodedurls.get(i).getUUID() + ".png");

        }

        FileUtil.ZipFolder(pngPath, pngPath + "全部司机对应的二维码.zip");




/*
        QRCodeGeneratorUtil.code("http://8.134.182.46:8081/QRCode/3565ce22bb874dc6ab005ab67553d36a",
                "C:\\Users\\QQ1839913610\\Desktop\\ewm\\ewm.png");*/
        //(路径必须存在)C:\Users\QQ1839913610\Desktop\ewm\ewm.png (系统找不到指定的路径。)
    }







/*

    @Test
    void GetAllTestDatad() {  //司机名生成
        List<String> chineseNames = Arrays.asList(
                "张三", "李四", "王五", "赵六", "孙七", "周八", "吴九", "郑十",
                "陈十一", "刘十二", "黄十三", "徐十四", "郭十五", "高十六", "林十七", "何十八",
                "梁十九", "罗二十", "宋二十一", "韩二十二", "唐二十三", "冯二十四", "蔡二十五", "邓二十六",
                "曹二十七", "彭二十八", "蒋二十九", "曾三十", "肖三十一", "田三十二", "董三十三", "潘三十四",
                "袁三十五", "于三十六", "程三十七", "傅三十八", "苏三十九", "杜四十", "叶四十一", "魏四十二",
                "吕四十三", "丁四十四", "沈四十五", "任四十六", "卢四十七", "钟四十八", "姜四十九", "崔五十",
                "谭五十一", "陆五十二", "郝五十三", "孔五十四", "白五十五", "崔五十六", "康五十七", "毛五十八",
                "邱五十九", "秦六十", "江六十一", "史六十二", "顾六十三", "侯六十四", "邵六十五", "孟六十六",
                "龙六十七", "万六十八", "段六十九", "雷七十", "钱七十一", "汤七十二", "尹七十三", "黎七十四",
                "易七十五", "常七十六", "武七十七", "乔七十八", "贺七十九", "赖八十", "龚八十一", "文八十二",
                "庞八十三", "樊八十四", "兰八十五", "殷八十六", "施八十七", "陶八十八", "洪八十九", "翟九十",
                "安九十一", "倪九十二", "严九十三", "牛九十四", "温九十五", "芦九十六", "季九十七", "俞九十八",
                "章九十九", "鲁一百"
        );
        for (int i = 0; i < chineseNames.size(); i++) {
            Drivernametoencodedurl D = new Drivernametoencodedurl();
            D.setDRIVERNAME(chineseNames.get(i));
            D.setUUID(UUIDUtils.getUUID());
            Integer i2 = drivernametoencodedurlMapper.InsertNameCode(D);
            System.out.println("i2 =>");
            System.out.println(i2);
        }
        // System.out.println(UUIDUtils.getUUID()); //拿到一个随机ID
    }//导入一大堆司机名

        @Test
    void gety12131() {  //给评价表填假数据，自定义数据个数（可以用）  （想要新增今天的记得把时间改到今天）
        System.out.println("gety12131 ==>");
        //1获取司机表信息的uuid，变成一个全部uuid列表
        //2随机选一个uuid，根据业务填充值
        //（读取全部司机表数据，单独取出uuid，变成list，插入评价记录）
        List<Drivernametoencodedurl> l = drivernametoencodedurlMapper.GetAllData(); //获取全部值
        System.out.println("l.size() =>");
        System.out.println(l.size());
        List<String> ppp = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {  //将uuid单独提出来
            ppp.add(l.get(i).getUUID());
        }
        System.out.println("ppp.size() =>");
        System.out.println(ppp.size());

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);

            Random r = new Random();
            int randomNum = r.nextInt(ppp.size());//随机一个uuid
            String Uuid = ppp.get(randomNum);
          //  System.out.println("Uuid =>");
          //  System.out.println(Uuid);
            List<Drivernametoencodedurl> kkk = drivernametoencodedurlMapper.FindDriverByUUID(Uuid);//根据用户UUID找到司机名

            String drivername = kkk.get(0).getDRIVERNAME();//测试内司机一定有就不判断了

            int e1 = r.nextInt(4) + 1;  //随机数1~4
            int e2 = r.nextInt(4) + 1;  //随机数1~4

            String phonenum = "14212345678";

            //随机时间
            // 定义时间段的起始和结束点
            LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0); // 2023年1月1日 00:00
            LocalDateTime endDate = LocalDateTime.of(2024, 4, 8, 23, 59); // 2023年12月31日 23:59

            Random rand = new Random();
            long secondsBetween = ChronoUnit.SECONDS.between(startDate, endDate);  // 计算两个日期之间的秒数
            long randomSeconds = rand.nextLong() % secondsBetween;// 生成一个随机秒数
            LocalDateTime randomDateTime = startDate.plusSeconds(randomSeconds);// 将随机秒数加到起始日期上，得到随机时间点
            ZoneId zoneId = ZoneId.systemDefault();// 获取系统默认时区
            ZonedDateTime zonedDateTime = randomDateTime.atZone(zoneId);// 将LocalDateTime转换为ZonedDateTime
            Instant instant = zonedDateTime.toInstant();// 将ZonedDateTime转换为Instant
            Date date = Date.from(instant);// 将Instant转换为Date

            Evaluationrecords e = new Evaluationrecords();
            e.setDRIVERNAME(drivername);
            e.setUUID(Uuid);
            e.setEVALUATIONINDICATOR1(e1);
            e.setEVALUATIONINDICATOR2(e2);
            e.setCONTACTNUMBEROFEVALUATOR(phonenum);
            e.setEvaluationTime(date);
          //  System.out.println(e.toString());
          //  System.out.println("Integer o = evaluationrecordsMapper.CreateNewCord2(e);//插入新评价记录 =>");
            Integer o = evaluationrecordsMapper.CreateNewCord2(e);//插入新评价记录

        }


    }


*/





    /*









    @Test
    void GetAllData() {
        System.out.println(" GetAllData ==>");
        List<Evaluationrecords> l = evaluationrecordsMapper.GetAllData();
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
    }//打印全部可用

    @Test
    void GetAllDatad() {
        System.out.println("drivernametoencodedurlMapper   ==>");
        List<Drivernametoencodedurl> l = drivernametoencodedurlMapper.GetAllData();
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }

    }//打印全部可用

    @Test
    void GetAllTestDatad() {
        List<String> chineseNames = Arrays.asList(
                "张三", "李四", "王五", "赵六", "孙七", "周八", "吴九", "郑十",
                "陈十一", "刘十二", "黄十三", "徐十四", "郭十五", "高十六", "林十七", "何十八",
                "梁十九", "罗二十", "宋二十一", "韩二十二", "唐二十三", "冯二十四", "蔡二十五", "邓二十六",
                "曹二十七", "彭二十八", "蒋二十九", "曾三十", "肖三十一", "田三十二", "董三十三", "潘三十四",
                "袁三十五", "于三十六", "程三十七", "傅三十八", "苏三十九", "杜四十", "叶四十一", "魏四十二",
                "吕四十三", "丁四十四", "沈四十五", "任四十六", "卢四十七", "钟四十八", "姜四十九", "崔五十",
                "谭五十一", "陆五十二", "郝五十三", "孔五十四", "白五十五", "崔五十六", "康五十七", "毛五十八",
                "邱五十九", "秦六十", "江六十一", "史六十二", "顾六十三", "侯六十四", "邵六十五", "孟六十六",
                "龙六十七", "万六十八", "段六十九", "雷七十", "钱七十一", "汤七十二", "尹七十三", "黎七十四",
                "易七十五", "常七十六", "武七十七", "乔七十八", "贺七十九", "赖八十", "龚八十一", "文八十二",
                "庞八十三", "樊八十四", "兰八十五", "殷八十六", "施八十七", "陶八十八", "洪八十九", "翟九十",
                "安九十一", "倪九十二", "严九十三", "牛九十四", "温九十五", "芦九十六", "季九十七", "俞九十八",
                "章九十九", "鲁一百"
        );
        for (int i = 0; i < chineseNames.size(); i++) {
            Drivernametoencodedurl D = new Drivernametoencodedurl();
            D.setDRIVERNAME(chineseNames.get(i));
            D.setUUID(UUIDUtils.getUUID());
            Integer i2 = drivernametoencodedurlMapper.InsertNameCode(D);
            System.out.println("i2 =>");
            System.out.println(i2);
        }
        // System.out.println(UUIDUtils.getUUID()); //拿到一个随机ID
    }//导入一大堆司机名

    @Test
    void gety() {
        List<Drivernametoencodedurl> drivernametoencodedurls = drivernametoencodedurlServerImpl.FindDriverByUUID("e8e5f092110940399001cc3d621a63fb");
        System.out.println("drivernametoencodedurls  =>");
        System.out.println(drivernametoencodedurls);
        System.out.println(drivernametoencodedurls.getClass());
        System.out.println(drivernametoencodedurls.size());

        List<Drivernametoencodedurl> drivernametoencodedurl = drivernametoencodedurlMapper.FindDriverByUUID("e8e5f092110940399001cc3d621a63fb");
        System.out.println("drivernametoencodedurl  =>");
        System.out.println(drivernametoencodedurl);
        System.out.println(drivernametoencodedurl.getClass());
        System.out.println(drivernametoencodedurl.size());
        // Drivernametoencodedurl FindDriverByUUID(Drivernametoencodedurl drivernametoencodedurl);//根据用户UUID找到司机名
    }//拿到UUID判断表内是否存储了司机


    @Test
    void gety023() {
        Evaluationrecords evaluationrecords = new Evaluationrecords();
        evaluationrecords.setUUID("sd");
        evaluationrecords.setDRIVERNAME("drnm");
        evaluationrecords.setEVALUATIONINDICATOR1(2);
        evaluationrecords.setEVALUATIONINDICATOR2(4);
        evaluationrecords.setCONTACTNUMBEROFEVALUATOR("15512345678");
        Integer i = evaluationrecordsServer.CreateNewCord(evaluationrecords);
        System.out.println("i = ");
        System.out.println(i);

    } //插入到记录内(可以用)




    @Test
    void gety0213() {
        User dl2124 = userMapper.findUserByUsername("dl2124");//根据用户名（手机号）查询用户信息
        System.out.println("dl2124 = ");
        System.out.println(dl2124.toString());
    }





    @Test
    void gety1213() {
        User user = new User();
        user.setUsername("admin");
        String s = "123456";
        user.setPassword1(Md5Util.getMD5String(s));


        Integer sd = null;
        try {
            sd = userMapper.CreateNewUser(user);
        } catch (Exception e) {

            //throw new RuntimeException(e);
        }
        System.out.println("sd = ");
        System.out.println(sd);
    }


    @Test
    void gety121311() {
        List<Evaluationrecords> evaluationrecords = evaluationrecordsMapper.GetAllData();//打印全部数据
        System.out.println(evaluationrecords.size());
    }

    @Test
    void gety121312() {
        Integer evaluationrecords = evaluationrecordsMapper.GetAllDataAllNum();//打印全部数据
        System.out.println(evaluationrecords);
    }



 @Test
    void selectpagelist() {  //插入可用
        List<Evaluationrecords> evaluationrecords1 = evaluationrecordsMapper.PaginationOfEvaluationRecords(1,3);

    }





    @Test
    void selectpagelist() {  //分页可用

        int beginNum = 11;
        int endNum = 20;

        List<Evaluationrecords> o = evaluationrecordsMapper.PagingAllData(beginNum, endNum);
        System.out.println(o.size());
        for (int i = 0; i < o.size(); i++) {
            System.out.println(o.get(i));
        }

    }




    @Test
    void sctoken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "fang");
        String s = JwtUtil.genToken(claims);
        System.out.println(s);
    }


    @Test
    void yztoken() {
        String s =

                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsidXNlcm5hbWUiOiJmYW5nIn0sImV4cCI6MTcxMTgyMDAyNX0.3xQEukKYgl_T1DePxBT_cnohmacQe68Lw8fJxnyeSPw";
        Map<String, Object> map = JwtUtil.parseToken(s);
        String username = (String) map.get("username");
        System.out.println("username =");
        System.out.println(username);


    }


    @Test
    void getalldrivernum() {  //获取司机表总数
        Integer integer = drivernametoencodedurlMapper.GetAlldrivernameNum();
        System.out.println("integer ==>");
        System.out.println(integer);
    }

    @Test
    void getallevalution() {  //获取评价表总数
        Integer integer = evaluationrecordsServer.GetAllDataAllNum();
        System.out.println("integer ==>");
        System.out.println(integer);
        Integer integer2 = evaluationrecordsServerimpl.GetAllDataAllNum();
        System.out.println("integer2 ==>");
        System.out.println(integer2);
    }


    @Test
    void getallevalution1() {  //获取评价表总数
        List<DriverRanking> driverRankings = cockpitServerImpl.GetAllDriverRanking();
        System.out.println("driverRankings =>");
        //System.out.println(driverRankings);
        System.out.println(driverRankings.size());
        for (int i = 0; i < driverRankings.size(); i++) {
            System.out.println(driverRankings.get(i));
        }

    }


    @Test
    void getallevalution11111() {  //获取评价表总数
        List<DriverRanking> driverRankings = cockpitServerImpl.GetAllNumberOfOccurrencesOfEvaluation1();
        System.out.println(driverRankings.size());

        for (int i = 0; i < driverRankings.size(); i++) {  //替换掉
            if (driverRankings.get(i).getName().equals("1")) {
                driverRankings.get(i).setName("不及格");
            } else if (driverRankings.get(i).getName().equals("2")) {
                driverRankings.get(i).setName("及格");
            } else if (driverRankings.get(i).getName().equals("3")) {
                driverRankings.get(i).setName("良好");
            } else if (driverRankings.get(i).getName().equals("4")) {
                driverRankings.get(i).setName("优秀");
            }
        }

        for (int i = 0; i < driverRankings.size(); i++) {  //替换掉
            System.out.println(driverRankings.get(i));
        }


    }

    @Test
    void getallevalution111112() {  //获取评价表总数
        List<DriverRanking> driverRankings = cockpitServerImpl.GetAllNumberOfOccurrencesOfEvaluation2();
        System.out.println(driverRankings.size());

        for (int i = 0; i < driverRankings.size(); i++) {  //替换掉
            if (driverRankings.get(i).getName().equals("1")) {
                driverRankings.get(i).setName("不及格");
            } else if (driverRankings.get(i).getName().equals("2")) {
                driverRankings.get(i).setName("及格");
            } else if (driverRankings.get(i).getName().equals("3")) {
                driverRankings.get(i).setName("良好");
            } else if (driverRankings.get(i).getName().equals("4")) {
                driverRankings.get(i).setName("优秀");
            }
        }

        for (int i = 0; i < driverRankings.size(); i++) {  //替换掉
            System.out.println(driverRankings.get(i));
        }
    }


    @Test
    void getallevalution1222() {  //获取评价表总数
        List<DriverRanking> driverRankings = cockpitServerImpl.MonthlySubmissionTimes();
        System.out.println("driverRankings =>");
        System.out.println(driverRankings.size());
        //System.out.println(driverRankings);
        System.out.println(driverRankings.size());
        for (int i = 0; i < driverRankings.size(); i++) {
            System.out.println(driverRankings.get(i));
        }
    }


    @Test
    void TotalDataInThePastWeek() {     // 统计近七天内每天提交的数据总数量
        List<DriverRanking> driverRankings = cockpitServerImpl.TotalDataInThePastWeek();
        System.out.println(driverRankings);
        System.out.println(driverRankings.size());
        for (int i = 0; i < driverRankings.size(); i++) {
            System.out.println(driverRankings.get(i));

        }
    }


    @Test
    void TotalDataInThePastWeek1() {     //测试基础的分页功能，获取评价表某一页

        List<Evaluationrecords> evaluationrecords = evaluationrecordsServerimpl.GetPagingEVALUATIONRECORDS(1, 3);
        //System.out.println(evaluationrecords);
        System.out.println(evaluationrecords.size());
        for (int i = 0; i <evaluationrecords.size() ; i++) {
            System.out.println(evaluationrecords.get(i));
        }


        //获取全部数据
        List<Evaluationrecords> evaluationrecords1 = evaluationrecordsServerimpl.GetAllData();
        //System.out.println(evaluationrecords1);
        System.out.println(evaluationrecords1.size());

    }


    @Autowired
    private testfycxService testfycxService1;




 @Test
    void TotalDataInThePastWeek11() {     //测试test的分页功能(未完成)
        PageBean<Evaluationrecords> allByPage = testfycxService1.findAllByPage(1, 3);
        System.out.println(allByPage.toString());
        //  List<Evaluationrecords> allByPage =  testYourEntityService.findAllByPage(1, 10);
       // System.out.println(allByPage.size());
    }






    @Test
    void TotalDataInThePastWeek121() {     //测试test的分页功能(可用)
        System.out.println("findAllByPage  ==>");
        PageBean<Evaluationrecords> allByPage = testfycxService1.findAllByPage(1, 3);
        System.out.println(allByPage.toString());
        System.out.println(allByPage.getTotal());
        System.out.println(allByPage.getItems().size());
        for (int i = 0; i < allByPage.getItems().size(); i++) {
            System.out.println(allByPage.getItems().get(i));
 if (allByPage.getItems().get(i).getEVALUATIONINDICATOR1().equals(1)) {

            }


        }

        //替换评价1、2、3、4为不及格、及格、良好、优秀


    }




    // 查询今日所有评价数据
    @Test
    void TotalDataInThePastWeek1211() {     //测试test的分页功能(可用)
        PageBean<Evaluationrecords> evaluationrecordsPageBean = evaluationrecordsServerimpl.QueryAllEvaluationDataForToday(2, 10);
        System.out.println(evaluationrecordsPageBean.getItems().size());
        System.out.println(evaluationrecordsPageBean.getTotal());

        for (int i = 0; i < evaluationrecordsPageBean.getItems().size(); i++) {
            System.out.println(evaluationrecordsPageBean.getItems().get(i));
        }
    }

    // 查询本月所有评价数据
    @Test
    void TotalDataInThePastWeek12111() {     //测试test的分页功能(可用)
        PageBean<Evaluationrecords> evaluationrecordsPageBean = evaluationrecordsServerimpl.QueryAllEvaluationDataForThisMonth(1, 10);
        System.out.println(evaluationrecordsPageBean.getItems().size());
        System.out.println(evaluationrecordsPageBean.getTotal());

        for (int i = 0; i < evaluationrecordsPageBean.getItems().size(); i++) {
            System.out.println(evaluationrecordsPageBean.getItems().get(i));
        }
    }


    @Test
    void TotalDataInThePastWeek22111() {
        //获取评价表的全部值   从高到低排序   显示排名


        PageBean<DriverRanking> driverRankingPageBean = evaluationrecordsServerimpl.GetAllDriverRankingPaging(1, 10);
        System.out.println(driverRankingPageBean.getItems().size());
        System.out.println(driverRankingPageBean.getTotal());

        for (int i = 0; i < driverRankingPageBean.getItems().size(); i++) {
            System.out.println(driverRankingPageBean.getItems().get(i));
        }
    }

    @Test
    void TotalDataInThePastWeek22211() {
        //获取评价表的全部值   从高到低排序   显示排名


        PageBean<Evaluationrecords> evaluationrecordsPageBean = evaluationrecordsServerimpl.QueryAllDirvernameData("黄十三", 1, 10);
        System.out.println(evaluationrecordsPageBean.getItems().size());
        System.out.println(evaluationrecordsPageBean.getTotal());

        for (int i = 0; i < evaluationrecordsPageBean.getItems().size(); i++) {
            System.out.println(evaluationrecordsPageBean.getItems().get(i));
        }
    }
*/
}
