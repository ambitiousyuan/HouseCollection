package com.hnshengen.housecollection.house.core.util;

import cn.stylefeng.roses.core.util.ToolUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.UUID;

public class GetFileUtil {

	public static File getFile(HttpServletRequest request) throws Exception{
		//强转请求类型获取上传文件
        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
        //获取文件名进行遍历(实际只上传一个文件)
        Iterator<String> iterator = req.getFileNames();
        while (iterator.hasNext()) {
            MultipartFile file = req.getFile(iterator.next());
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String path = ToolUtil.class.getClassLoader().getResource("").toURI().getPath();
            File transFile = new File(path + uuid + fileName);
            file.transferTo(transFile);
            return transFile;
        }
        return null;
	}
	
	
	public static MultipartFile getMultipartFile(HttpServletRequest request) throws Exception{
		//强转请求类型获取上传文件
        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
        //获取文件名进行遍历(实际只上传一个文件)
        Iterator<String> iterator = req.getFileNames();
        while (iterator.hasNext()) {
            MultipartFile file = req.getFile(iterator.next());
            return file;
        }
        return null;
	}
	
	
	/**
	 * 判断上传文件后缀
	 * @param path 文件名
	 * @return
	 */
	public static Boolean matchExcel(String path){
        return path.matches("^.+\\.(?i)(xls)$");
    }
	
}
