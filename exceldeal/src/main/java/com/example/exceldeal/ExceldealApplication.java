package com.example.exceldeal;

import com.example.exceldeal.service.ExcelExport2003;
import com.example.exceldeal.service.ExcelExport2007;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ExceldealApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExceldealApplication.class, args);
    }

    @PostConstruct
    public void test(){
        //ExcelExport2003.getExcelExport();
        ExcelExport2007.getExcelExport();
    }

}
