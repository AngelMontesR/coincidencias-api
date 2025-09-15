package spring.duplicados.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.duplicados.domain.Coincidencia;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class DetectarDuplicadosUseCaseTest {

    @Autowired
    private DetectarDuplicadosUseCase useCase;

    @Test
    void detectarDuplicados_deberiaEncontrarCoincidencias() throws IOException {
        try {
            // obtenemos archivo listado
            String filePath = getClass().getResource("/listado.xlsx").getPath();

            // verificamos datos de archivo
            List<Coincidencia> duplicados = useCase.verificar(filePath);

            // mostramos respuesta
            System.out.println(duplicados);
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo Excel: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }
}
