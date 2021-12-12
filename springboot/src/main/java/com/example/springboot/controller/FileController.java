package com.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {



    /**
     *上传接口
     * @param file
     * @return
     * @throws IOException
    **/
//   上传接口
    @PostMapping("/upload")//如果不行就改成RequestMapping
    public Result<?> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename() ; //获取源文件的名称
        
        //防止重名，定义文件的唯一标识，加个前缀
        String flag = IdUtil.fastSimpleUUID();

        String rootFilePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/" + flag + "_" + originalFilename; //获取上传的目的路径,localhost
        FileUtil.writeBytes(file.getBytes(),rootFilePath); //把文件写入到目的路径
        return Result.success("http://101.35.255.239:9090/files/" + flag); //返回结果 url ,文件的下载接口 ip + 端口 + files + flag
    }

    /**
     *
     * @param flag
     * @param response
     */
//    下载接口
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os; //新建一个输出流对象
        String basePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/"; //定义文件上传的根路径，localhost
        List<String> fileNames = FileUtil.listFileNames(basePath); //获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse(""); //然后找到和参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName); //通过文件的路径读取文件的字节流
                os = response.getOutputStream(); //通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }
}
