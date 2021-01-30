package com.huade.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class FileController {

//    @RequestMapping(value="/uploadImage",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/uploadImage")
    @ResponseBody
    public Map<String,Object> uploadImage(MultipartFile upfile) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();
        String realName = null;
        String uuidName = null;
        String realPath = null;

        try {
            realName = getRealName(upfile.getOriginalFilename());
            uuidName = getUUIDFileName(upfile.getOriginalFilename());

            realPath = "/Users/yaoyuan/Desktop/online_exam/file/image";
            InputStream in = new BufferedInputStream(upfile.getInputStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(realPath,uuidName)));
            //读写
            int len = 0;
            byte[] buffer = new byte[102400];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer,0,len);
                out.flush();
            }
            out.close();
            in.close();
            //IOUtils.copy(in,out);
            map.put("state", "SUCCESS");
            map.put("url","/images/"+uuidName);
            map.put("title",realName);
            map.put("original",realName);
        } catch (IOException e) {
            map.put("state", "文件上传失败,请联系管理员");
            map.put("url","");
            map.put("title","");
            map.put("original","");
        }
        //存入数据库方法
        return map;
    }

    private String getExtName(String s, char split) {
        int i = s.lastIndexOf(split);
        int leg = s.length();
        return i > 0 ? (i + 1) == leg ? " " : s.substring(i+1, s.length()) : " ";
    }

    private String getUUIDFileName(String fileName){
        UUID uuid = UUID.randomUUID();
        StringBuilder sb = new StringBuilder(100);
        sb.append(uuid.toString().replace("-","")).append(".").append(this.getExtName(fileName, '.'));
        return sb.toString();
    }

    private String getRealName(String originalName){
        //System.out.println(originalName.contains("."));

        if(originalName.contains(".")){
            String [] as = originalName.split("\\.");
            //System.out.println(as[0]);
            return as[0];
        }else {
            return originalName;
        }

    }

//    @RequestMapping("/upload")
//    public String upload (@RequestParam("file") CommonsMultipartFile file) throws IOException{
//        System.out.println(file);
//        String uploadFileName = file.getOriginalFilename();
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        JSONObject object = new JSONObject();
//        ObjectMapper mapper = new ObjectMapper();
//        if ("".equals(uploadFileName)) {
//            object.put("code",0);
//            object.put("message","上传失败！请重试！");
//            return mapper.writeValueAsString(object);
//        }
//        String path = "/Users/yaoyuan/Desktop/Exam/file";
//        File realPath = new File(path);
//        if (!realPath.exists()){
//            realPath.mkdir();
//        }
//        InputStream inputStream = file.getInputStream();//文件输入流
//        OutputStream outputStream = new FileOutputStream(new File(realPath,uuid+uploadFileName));//文件输出流
//
//        //读写
//        int len = 0;
//        byte[] buffer = new byte[10240];
//        while ((len=inputStream.read(buffer)) != -1){
//            outputStream.write(buffer,0,len);
//            outputStream.flush();
//        }
//        outputStream.close();
//        inputStream.close();
//        String file_path = realPath+"/"+uuid+uploadFileName;
//        System.out.println("File_Path:" + file_path);
//        return file_path;
//    }

    /**
     * 上传配置：即不走config.json，模拟config.json里的内容，解决后端配置项不正确，无法上传的问题
     * @return
     */
    @RequestMapping("/ueditor/config")
    @ResponseBody
    public String uploadConfig() {
        String s = "{\n" +
                "            \"imageActionName\": \"uploadimage\",\n" +
                "                \"imageFieldName\": \"upfile\", \n" +
                "                \"imageMaxSize\": 2048000, \n" +
                "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "                \"imageCompressEnable\": true, \n" +
                "                \"imageCompressBorder\": 1600, \n" +
                "                \"imageInsertAlign\": \"none\", \n" +
                "                \"imageUrlPrefix\": \"\",\n" +
                "                \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\" }";
        return s;
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/Users/yaoyuan/Desktop/Exam/file";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);

            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }

    @RequestMapping("/download")
    public String downloads(HttpServletResponse response,
                            @Param("fileName")String fileName) throws Exception{
        String path = "/Users/yaoyuan/Desktop/online_exam/file";

        //设置response响应头
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-date");//二进制传输数据

        //设置响应头
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        File file = new File(path,fileName);

        //读取文件--输入流
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[10240];
        int index = 0;
        while ((index = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer,0,index);
            outputStream.flush();
        }
        outputStream.close();
        inputStream.close();
        return null;
    }

}
