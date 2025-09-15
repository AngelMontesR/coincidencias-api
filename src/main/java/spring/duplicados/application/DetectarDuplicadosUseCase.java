package spring.duplicados.application;

import org.springframework.stereotype.Service;
import spring.duplicados.domain.Coincidencia;
import spring.duplicados.domain.Contacto;
import spring.duplicados.infrastructure.adapter.ExcelReaderAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DetectarDuplicadosUseCase {

    private final ExcelReaderAdapter excelReader;

    public DetectarDuplicadosUseCase(ExcelReaderAdapter excelReader) {
        this.excelReader = excelReader;
    }

    /**
     * Identifica duplicados potenciales
     * @param filePath La ruta al archivo de entrada.
     * @return Una lista de objetos Coincidencia que representan los duplicados encontrados.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public List<Coincidencia> verificar(String filePath) throws IOException {

        // Usa el adaptador para leer los datos.
        List<Contacto> contactos = excelReader.leerContactos(filePath);
        List<Coincidencia> duplicados = new ArrayList<>();

        // Agrupa los contactos por correo electrónico para una búsqueda rápida.
        Map<String, List<Contacto>> contactosPorCorreo = contactos.stream()
                .collect(Collectors.groupingBy(Contacto::getCorreoElectronico));

        for (List<Contacto> grupo : contactosPorCorreo.values()) {
            // Si el grupo tiene más de un contacto, hay duplicados potenciales.
            if (grupo.size() > 1) {
                // Compara cada contacto del grupo con los demás.
                for (int i = 0; i < grupo.size(); i++) {
                    for (int j = i + 1; j < grupo.size(); j++) {
                        Contacto c1 = grupo.get(i);
                        Contacto c2 = grupo.get(j);

                        // Lógica de precisión de la coincidencia
                        if (c1.getApellido().equalsIgnoreCase(c2.getApellido())) {
                            duplicados.add(new Coincidencia(c1.getIdContacto(), c2.getIdContacto(), "Alta"));
                        } else {
                            duplicados.add(new Coincidencia(c1.getIdContacto(), c2.getIdContacto(), "Baja"));
                        }
                    }
                }
            }
        }
        return duplicados;
    }
}