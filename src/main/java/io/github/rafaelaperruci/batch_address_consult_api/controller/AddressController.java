package io.github.rafaelaperruci.batch_address_consult_api.controller;

import io.github.rafaelaperruci.batch_address_consult_api.service.AddressService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> processExcelFile(
            //captura a planilha do form-data com a chave file
            @RequestParam("file") MultipartFile file) throws IOException {

        //Objeto criado para representar a planilha de excel
        Workbook workbook = new XSSFWorkbook(file.getInputStream());

        //Pega a primeira aba da planilha e joha no objeto sheet
        Sheet sheet =  workbook.getSheetAt(0);
        System.out.println(sheet.getPhysicalNumberOfRows());

        return  ResponseEntity.ok(sheet.getPhysicalNumberOfRows());

    }
}
