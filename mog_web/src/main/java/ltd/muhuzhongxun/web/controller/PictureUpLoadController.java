package ltd.muhuzhongxun.web.controller;

import com.power.common.util.UUIDUtil;
import ltd.muhuzhongxun.utils.ResultUtils;
import ltd.muhuzhongxun.utils.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


//@CrossOrigin//解决跨域问题
@RestController
@RequestMapping("/util")
public class PictureUpLoadController {

    @Value("F:/IdeaJProjects/manual_og_system/mog_web/src/main/resources/images/")
    private String path;

    @RequestMapping("/upload")
    public ResultVo FileUpdate (HttpServletRequest request, MultipartFile file) {
        //表示获得服务器的绝对路径
//        String path=this.getClass().getClassLoader().getResource("").getPath()+"/images/";
        //得到上传时的文件名字
        String originalname=file.getOriginalFilename();
        //substring(originalname.lastIndexOf(".")截取图片名后缀
        String suffix= originalname.substring(originalname.lastIndexOf("."));


        //获取当前系统时间时分秒
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String updatetimeString= sdf.format(date);
//        System.out.println(updatetimeString);

        //利用UUidUtil工具类生成新的文件名字后缀，newfile（时间+uuid+后缀）
        String newName = updatetimeString + UUIDUtil.getUuid32() + suffix;
//        System.out.println(newName);
        File newFile=new File(path,newName);

        //获得文件目录，判断是否存在
        if(!newFile.getParentFile().exists()) {
            //如果path路径不存在，创建一个文件夹，存在则使用当前文件夹
            newFile.getParentFile().mkdirs();
        }
        try {
            //保存文件到指定路径之中
            file.transferTo(newFile);
            return ResultUtils.success("上传成功！",newName);
        } catch (IllegalStateException | IOException e) {

            //e.printStackTrace();
            return ResultUtils.error("文件上传失败！");
        }
//        System.out.println("path = "+path+",newName = "+newName);


    }
}
