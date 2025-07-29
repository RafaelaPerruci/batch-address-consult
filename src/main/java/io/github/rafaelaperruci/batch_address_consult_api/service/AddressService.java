package io.github.rafaelaperruci.batch_address_consult_api.service;

import io.github.rafaelaperruci.batch_address_consult_api.dto.AddressDTO;
import io.github.rafaelaperruci.batch_address_consult_api.model.Address;
import io.github.rafaelaperruci.batch_address_consult_api.repository.AddressRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.replaceAll;

@Service
public class AddressService {

    private ExternalApiConsumer consumer;
    private AddressRepository addressRepository;


    public AddressService(ExternalApiConsumer consumer, AddressRepository addressRepository) {
        this.consumer = consumer;
        this.addressRepository = addressRepository;
    }


    public List<Address> fetchAddresses(Sheet sheet, Workbook workbook) {
        List<AddressDTO> addresses = new ArrayList<>();

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            System.out.println(sheet.getLastRowNum());
            Row row = sheet.getRow(i);
            if (row == null) break;

            Cell cepCellObj = row.getCell(0);
            if (cepCellObj.toString().trim().isEmpty()) break;

            String cepValue = cepCellObj.toString().trim();

            // pula cabeçalho
            if (i == 0 && cepValue.equalsIgnoreCase("Cep")) continue;

            String cep = cepValue.replaceAll("[^0-9]", "");
            if (cep.length() != 8) {
                Cell erroCell = row.getCell(1);
                if (erroCell == null) erroCell = row.createCell(1);
                erroCell.setCellValue("Cep incorreto.");
                continue;
            }

            try {
                AddressDTO dto = consumer.getAddress(cep);
                Cell addressCell = row.getCell(1);
                String address = String.format(
                        "%s, %s - %s/%s",
                        dto.street(),
                        dto.suburb(),
                        dto.city(),
                        dto.state()
                );
                addressCell.setCellValue(address);

                addresses.add(dto);
            } catch (Exception e) {
                Cell errorCell = row.getCell(1);
                if (errorCell == null) errorCell = row.createCell(1);
                errorCell.setCellValue("Erro na consulta.");
            }


        }
        try {
            Path outputDir = Paths.get("src/main/resources/output");
            Files.createDirectories(outputDir);
            File outputFile = outputDir.resolve("planilha-completa.xlsx").toFile();

            try (FileOutputStream output = new FileOutputStream(outputFile)) {
                workbook.write(output);
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a planilha preenchida", e);
        }

        return addresses.stream().map(address -> new Address(address)).collect(Collectors.toList());
    }

    public List<AddressDTO> saveAll(List<Address> addresses) {
        List<AddressDTO> dtos = new ArrayList<>();
        for (Address address : addresses) {
            addressRepository.save(address);
            dtos.add(new AddressDTO(address));
        }
        return dtos;
    }
}
