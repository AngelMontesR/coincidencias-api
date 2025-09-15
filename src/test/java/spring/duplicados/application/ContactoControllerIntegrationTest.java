package spring.duplicados.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class ContactoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSubirArchivoExcel() throws Exception {

        // Cargar el archivo Excel desde resources para la prueba
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "listado.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                getClass().getResourceAsStream("/listado.xlsx")
        );

        System.out.println("file = " + file.getOriginalFilename());

        // Realizamos la petición al endpoint
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/duplicacion/procesar")
                        .file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> System.out.println("Response: " + result.getResponse().getContentAsString()));
    }
}
