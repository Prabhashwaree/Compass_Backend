package lk.me.compass.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lk.me.compass.util.ExcelConverter;
import lk.me.compass.util.ResponseUtil;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/backOffice")
public class BackOfficeController {
    

    //get excel file as multipart file
    @PostMapping("/upload")
    public ResponseUtil validateAuthentication(@RequestParam("file") MultipartFile file){
        System.out.println(file.toString());
        try {
            ExcelConverter.readExcelFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        // try{
        //     return authService.authenticationLogin(authReqDTO);
        // }catch(Exception e){
        //     return new ResponseUtil(500,e.getMessage(),null);
        // }
            return new ResponseUtil(500,"e.getMessage()",null);

    }

}
