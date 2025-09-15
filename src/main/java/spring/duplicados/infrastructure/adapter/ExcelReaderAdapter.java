package spring.duplicados.infrastructure.adapter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import spring.duplicados.domain.Contacto;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelReaderAdapter {

    /**
     * Lee los datos de contacto desde archivo Excel y los convierte en objetos Contacto.
     * @param filePath La ruta al archivo de entrada.
     * @return Una lista de objetos Contacto.
     * @throws IOException Si el archivo no se puede leer.
     */
    public List<Contacto> leerContactos(String filePath) throws IOException {
        List<Contacto> contactos = new ArrayList<>();
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        // leemos la primer hoja del archivo
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            // omitimos el encabezado del excel
            if (row.getRowNum() == 0) continue;

            Contacto contacto = new Contacto();

            // mapeamos las celdas del archivo
            contacto.setIdContacto((int) getCellValueAsDouble(row.getCell(0)));
            contacto.setNombre(getCellValueAsString(row.getCell(1)));
            contacto.setApellido(getCellValueAsString(row.getCell(2)));
            contacto.setCorreoElectronico(getCellValueAsString(row.getCell(3)));
            contacto.setCodigoPostal(String.valueOf((int) getCellValueAsDouble(row.getCell(4))));
            contacto.setDireccion(getCellValueAsString(row.getCell(5)));

            contactos.add(contacto);
        }

        workbook.close();
        file.close();
        return contactos;
    }

    // Métodos utilitarios para manejar diferentes tipos de celdas
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return cell.getStringCellValue();
    }

    private double getCellValueAsDouble(Cell cell) {
        if (cell == null) return 0.0;
        return cell.getNumericCellValue();
    }
}