package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.PhotoService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 30/10/15.
 */
@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

    @Resource
    Environment env;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadFileHandler(@RequestParam("name") String name,
                             @RequestParam("file") MultipartFile file) {
        Map<String, String> result = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String pathFull = env.getRequiredProperty(PhotoService.PROPERTY_STATIC_FILE_PHOTO);
                File dir = new File(pathFull);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                result.put("result", "success");
            } catch (Exception e) {
                result.put("result", "fail");
            }
        } else {
            result.put("result", "empty");
        }

        return result;
    }
}
