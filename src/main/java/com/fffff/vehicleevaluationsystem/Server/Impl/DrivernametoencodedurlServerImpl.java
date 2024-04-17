package com.fffff.vehicleevaluationsystem.Server.Impl;

import java.util.Date;

import com.fffff.vehicleevaluationsystem.Mapper.DrivernametoencodedurlMapper;
import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;
import com.fffff.vehicleevaluationsystem.Pojo.Drivernametoencodedurl;
import com.fffff.vehicleevaluationsystem.Pojo.Evaluationrecords;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;
import com.fffff.vehicleevaluationsystem.Server.DrivernametoencodedurlServer;
import com.fffff.vehicleevaluationsystem.Utils.FileUtil;
import com.fffff.vehicleevaluationsystem.Utils.QRCodeGeneratorUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DrivernametoencodedurlServerImpl implements DrivernametoencodedurlServer {
    @Autowired
    private DrivernametoencodedurlMapper drivernametoencodedurlMapper;

    @Override
    public Integer UPDATEDrivernameFindByID(Drivernametoencodedurl drivernametoencodedurl) {
        return drivernametoencodedurlMapper.UPDATEDrivernameFindByID(drivernametoencodedurl);
    }

    @Override
    public List<Drivernametoencodedurl> GetAllData() {
        return drivernametoencodedurlMapper.GetAllData();
    }

    @Override
    public Integer InsertNameCode(Drivernametoencodedurl drivernametoencodedurl) {
        return drivernametoencodedurlMapper.InsertNameCode(drivernametoencodedurl);
    }

    @Override
    public List<Drivernametoencodedurl> FindDriverByUUID(String UUID) {
        return drivernametoencodedurlMapper.FindDriverByUUID(UUID);
    }

    @Override
    public Integer GetAlldrivernameNum() {
        return drivernametoencodedurlMapper.GetAlldrivernameNum();
    }

    @Override
    public PageBean<Drivernametoencodedurl> FindDriverNameByDriverData(String Drivername, int pageNum, int pageSize) {//根据司机名找到司机
        //获取评价表的全部值   从高到低排序   显示排名

        //1.创建PageBean对象
        PageBean<Drivernametoencodedurl> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<Drivernametoencodedurl> as = drivernametoencodedurlMapper.FindDriverNameByDriverData(Drivername);
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Drivernametoencodedurl> p = (Page<Drivernametoencodedurl>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }

    @Value("${PublicNetworkAddress}")
    private String PublicNetworkAddress;

    @Value("${QRCode.save-path}")
    private String savePath;

    @Override
    public Integer GenerateQRCode() {
        String PublicNetworkAddress1 = PublicNetworkAddress; //公网地址

        String ewmURL = "QRCode/"; //公网地址和UUID之间的路由路径
        String pngPath = savePath;//生成的图片存储路径

        FileUtil.ClearDirectory(pngPath);
        FileUtil.CreateDirectoryExample(pngPath);


        List<Drivernametoencodedurl> drivernametoencodedurls = drivernametoencodedurlMapper.GetAllData();
        if (drivernametoencodedurls.isEmpty()) {

        } else {
            for (int i = 0; i < drivernametoencodedurls.size(); i++) { //遍历全部
                String drivernameURL = PublicNetworkAddress1 + ewmURL + drivernametoencodedurls.get(i).getUUID();
                QRCodeGeneratorUtil.code(drivernameURL,
                        pngPath + "\\" + drivernametoencodedurls.get(i).getDRIVERNAME() + "_" + drivernametoencodedurls.get(i).getUUID() + ".png");
            }
            FileUtil.ZipFolder(pngPath, pngPath + "\\全部司机对应的二维码(压缩版).zip");
        }

        return 1;
    }

}
