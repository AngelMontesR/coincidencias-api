package spring.duplicados.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spring.duplicados.application.DetectarDuplicadosUseCase;
import spring.duplicados.domain.Coincidencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/duplicacion")
public class ContactoController {

    private final DetectarDuplicadosUseCase detectarDuplicadosUseCase;

    public ContactoController(DetectarDuplicadosUseCase detectarDuplicadosUseCase) {
        this.detectarDuplicadosUseCase = detectarDuplicadosUseCase;
    }

    @PostMapping("/procesar")
    public ResponseEntity<List<Coincidencia>> procesarArchivo(@RequestParam("file") MultipartFile file) {

        // Validamos si en la petición manda el campo file
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Guarda el archivo temporalmente.
            Path tempFile = Files.createTempFile("contactos-", ".xlsx");
            file.transferTo(tempFile);

            // Usa el caso de uso para verificar data.
            List<Coincidencia> resultados = detectarDuplicadosUseCase.verificar(tempFile.toString());

            // Elimina el archivo temporal.
            Files.delete(tempFile);

            return ResponseEntity.ok(resultados);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}