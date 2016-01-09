package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.EquipoService;
import com.codigoartesanal.entuliga.services.PathPhoto;
import com.codigoartesanal.entuliga.services.PhotoService;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 30/10/15.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    PhotoService photoService;

    @Autowired
    EquipoService equipoService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("equipoId") Long idEquipo) throws IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {

            if (!(request instanceof StandardMultipartHttpServletRequest)){
                throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
            }
        }

        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request ;
        MultipartFile file = (MultipartFile) dmhsRequest.getFile("filelogo");

        Map<String, String> result = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                String nameLogo = photoService.getValidNameLogo(file.getOriginalFilename(), idEquipo);
                String serverPath = photoService.getValidPathAbsoluteLogo() + nameLogo;

                photoService.writeFile(bytes, serverPath);
                equipoService.updateLogoByEquipo(nameLogo, idEquipo);

                result.put("result", "success");
                result.put("pathfilename", photoService.getValidPathWebLogo(nameLogo, null));
                result.put("filename", nameLogo);
            } catch (Exception e) {
                result.put("result", "fail");
            }
        } else {
            result.put("result", "empty");
        }

        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteFile(@RequestParam("key") Long idEquipo, @RequestParam("logo") String logo)
            throws IOException {
        Map<String, String> result = new HashMap<>();

        photoService.deleteLogo(logo);
        equipoService.updateLogoByEquipo("", idEquipo);

        result.put("result", "success");
        result.put("defaultname", PathPhoto.EQUIPO_DEFAULT.getPath());
        return result;
    }
}
