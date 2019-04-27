package com.qiaohx.qblog.qblogweb.controller.upload;

import com.google.gson.Gson;
import com.qiaohx.qblog.api.upload.vo.SingleUploadResponseVo;
import com.qiaohx.qblog.service.common.sequence.SequenceUtil;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
@RequestMapping("/upload")
@Api(description = "文件上传接口")
public class UploadController {

    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Value("${filepath}")
    private String fileBasePath;

    @Value("${qiniu.appkey}")
    private String qiniuAppkey;

    @Value("${qiniu.appsecret}")
    private String qiniuAppsecret;

    @Value("${qiniu.bucket}")
    private String qiniuBucket;

    @Value("${qiniu.baseurl}")
    private String qiniuBaseurl;

    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "单个文件上传", notes = "上传单个文件")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "文件", required = true),
            @ApiImplicitParam(name = "type", value = "文件类型", required = true, allowableValues = "image：图片")})
    public SingleUploadResponseVo upload(MultipartFile file, String type) {
        logger.info("getName：" + file.getName());
        String filename = file.getOriginalFilename();
        logger.info("getOriginalFilename：" + filename);
        logger.info("type：" + type);
        InputStream is = null;
        OutputStream os = null;
        logger.info("FILE_BASE_PATH ===== " + fileBasePath);
        String fileSaveFolder = fileBasePath.concat("/").concat(type).concat("/");
        String fileName = SequenceUtil.getSequence().concat(filename.substring(filename.lastIndexOf(".")));
        String fileSavePath = fileSaveFolder.concat(fileName);
        try {
            is = file.getInputStream();
            File fileSave = new File(fileSaveFolder);
            if (!fileSave.exists()){
                logger.debug("创建路径 ===== " + fileSaveFolder);
                boolean res = fileSave.mkdirs();
                if (!res) {
                    logger.error("创建文件目录失败了");
                    return ResponseUtil.result(ErrorCodeEnums.FILE_ERROR, SingleUploadResponseVo.class);
                }
            }
            File imageFile = new File(fileSavePath);
            os = new FileOutputStream(imageFile);
            byte[] b = new byte[1024];
            while (is.read(b) > 0){
                os.write(b);
            }

            os.flush();

            String url = uploadQiniu(fileSavePath);
            if (!"".equals(url)){
                boolean delFlag = imageFile.delete();// 删除
                SingleUploadResponseVo singleUploadResponseVo = ResponseUtil.success(SingleUploadResponseVo.class);
                singleUploadResponseVo.setUrl(url);
                return singleUploadResponseVo;
            }else {
                return ResponseUtil.result(ErrorCodeEnums.FILE_ERROR, SingleUploadResponseVo.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("上传文件IOException", e);
            return ResponseUtil.result(ErrorCodeEnums.FILE_ERROR, SingleUploadResponseVo.class);
        } finally {
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("关闭IS异常", e);
            }
        }
    }

    /**
     * 上传到七牛
     * @param filePath 本地路径
     * @return 七牛地址
     */
    private String uploadQiniu(String filePath){

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(qiniuAppkey, qiniuAppsecret);
        String upToken = auth.uploadToken(qiniuBucket);
        try {
            Response response = uploadManager.put(filePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            logger.info("putRet.key == " + putRet.key);
            logger.info("putRet.hash == " + putRet.hash);
            return qiniuBaseurl.concat(putRet.key);
        } catch (QiniuException ex) {
            Response r = ex.response;
            logger.error(r.toString());
            try {
                logger.error(r.bodyString());
            } catch (QiniuException ex2) {
            }
        }
        return "";
    }

}
