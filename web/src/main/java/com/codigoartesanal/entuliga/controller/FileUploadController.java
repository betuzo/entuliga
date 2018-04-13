package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.services.*;
import com.codigoartesanal.entuliga.util.ImageUtil;
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
    PathWebService pathWebService;

    @Autowired
    StorageImageService storageImageServices;

    @Autowired
    EquipoService equipoService;

    @Autowired
    JugadorService jugadorService;

    @Autowired
    ArbitroService arbitroService;

    @RequestMapping(value = "/upload/logo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addFileLogo(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam("id") Long id) throws IOException {
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
                String ext = getExtensionLogo(file.getOriginalFilename());
                byte[] bytes = ImageUtil.resizeImageDefault(file.getBytes(), ext.replace(".", ""));

                String nameLogo = id + ext;
                storageImageServices.writeImage(bytes, nameLogo, OriginPhoto.EQUIPO);
                equipoService.updateLogoByEquipo(nameLogo, id);

                result.put("result", "success");
                result.put("pathfilename", pathWebService.getValidPathWebLogo(nameLogo, null));
                result.put("filename", nameLogo);
            } catch (Exception e) {
                result.put("result", "fail");
            }
        } else {
            result.put("result", "empty");
        }

        return result;
    }

    @RequestMapping(value = "/upload/foto", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addFileFoto(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam("id") Long id, @RequestParam("origin") String origin) throws IOException {
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
                String ext = getExtensionLogo(file.getOriginalFilename());
                byte[] bytes = ImageUtil.resizeImageDefault(file.getBytes(), ext.replace(".", ""));

                String nameLogo = id + ext;
                OriginPhoto originPhoto = OriginPhoto.valueOf(origin);
                storageImageServices.writeImage(bytes, nameLogo, originPhoto);
                if (originPhoto == OriginPhoto.JUGADOR) {
                    jugadorService.updateFotoByJugador(nameLogo, id);
                } else {
                    arbitroService.updateFotoByArbitro(nameLogo, id);
                }

                result.put("result", "success");
                result.put("pathfilename", pathWebService.getValidPathWebFoto(nameLogo, OriginPhoto.valueOf(origin)));
                result.put("filename", nameLogo);
            } catch (Exception e) {
                result.put("result", "fail");
            }
        } else {
            result.put("result", "empty");
        }

        return result;
    }

    @RequestMapping(value = "/delete/logo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> removeFileLogo(@RequestParam("key") Long idEquipo, @RequestParam("logo") String logo)
            throws IOException {
        Map<String, String> result = new HashMap<>();

        storageImageServices.deleteImage(logo, OriginPhoto.EQUIPO);
        equipoService.updateLogoByEquipo("", idEquipo);

        result.put("result", "success");
        result.put("defaultname", PathPhoto.EQUIPO_DEFAULT.getPath());
        return result;
    }

    @RequestMapping(value = "/delete/foto", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> removeFileFoto(@RequestParam("key") Long idJugador,
                                              @RequestParam("logo") String foto, @RequestParam("origin") String origin)
            throws IOException {
        Map<String, String> result = new HashMap<>();

        OriginPhoto originPhoto = OriginPhoto.valueOf(origin);
        storageImageServices.deleteImage(foto, originPhoto);
        if (originPhoto == OriginPhoto.JUGADOR) {
            jugadorService.updateFotoByJugador("", idJugador);
        } else {
            arbitroService.updateFotoByArbitro("", idJugador);
        }

        result.put("result", "success");
        result.put("defaultname", PathPhoto.JUGADOR_DEFAULT.getPath());
        return result;
    }

    private String getExtensionLogo(String path) {
        return path.substring(path.lastIndexOf("."));
    }
}
