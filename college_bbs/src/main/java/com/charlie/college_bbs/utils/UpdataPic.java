package com.charlie.college_bbs.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/article")
public class UpdataPic {
    public static final String pathname = "F:\\articlePic\\";

    /**
     * 前端上传图片提交到后台，
     * 后台保存图片然后返回图片在的后台保存地址
     * @param picbase64url  图片的二进制编码
     * @param picName       图片的名称
     * @return
     */
    @PostMapping("updataPic")
    public Message updataPic(String picbase64url, String picName){
        log.info("com.charlie.college_bbs.utils.UpdataPic.updataPic调用，正在上传图片：" + picName);
        String result = Base64Utils.Base64StrToImage(picbase64url, picName);
        if (result == null)
            return MessageUtil.error("图片格式错误，上传失败");
        else
            return MessageUtil.success("图片上传成功！", result);
    }
}
