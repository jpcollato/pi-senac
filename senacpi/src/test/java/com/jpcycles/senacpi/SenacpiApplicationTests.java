package com.jpcycles.senacpi;

import com.jpcycles.senacpi.model.Bicicleta;
import com.jpcycles.senacpi.repository.BicicletaRepository;
import com.jpcycles.senacpi.service.BicicletaService;
import jakarta.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SenacpiApplicationTests {

    @Autowired
    private BicicletaService biciService;

    @Autowired
    private BicicletaRepository biciRepo;

    //Teste para cadastro de bicicleta com campos obrigatórios MODELO e MARCA.
    @Test
    void cadastrarBikeNormal() {
        Bicicleta bike = new Bicicleta("Sirrus", "Specialized", null, null, null, "2020", 'P');
        biciService.cadastrarBicicleta(bike);
        biciService.excluirBicicleta(bike.getId());

    }

    //Teste para cadastro de bicicleta com campo obrigatório MODELO=null.
    @Test
    void testarCadastroBike() {

        Bicicleta bike = new Bicicleta(null, "Specialized", null, null, null, "2014", 'M');
        assertThrows(ConstraintViolationException.class, () -> {
            biciService.cadastrarBicicleta(bike);
        });

    }
    
    //Testar venda de bicicleta com quantidade menor ou igual que a quantidade disponível no estoque
    @Test
    void testarVendaBike(){
        Bicicleta b = new Bicicleta("Sirrus", "Specialized", null, null, 6.0, "2020", 'P');
        biciService.cadastrarBicicleta(b);
        
        Bicicleta bike = new Bicicleta();
        bike.setSaldo(5.0);
        bike.setPrecoVenda(1000.0);
        
        assertTrue(biciService.venderBicicleta(b.getId(), bike));
        
        biciService.excluirBicicleta(b.getId());
    }
    
    //Testar venda de bicicleta com quantidade menor ou igual que a quantidade disponível no estoque
    @Test
    void testarVendaBikeSemEstoque(){
        Bicicleta b = new Bicicleta("Sirrus", "Specialized", null, null, 6.0, "2020", 'P');
        biciService.cadastrarBicicleta(b);
        
        Bicicleta bike = new Bicicleta();
        bike.setSaldo(10.0);
        bike.setPrecoVenda(1000.0);
        
        assertFalse(biciService.venderBicicleta(b.getId(), bike));
        
        biciService.excluirBicicleta(b.getId());
    }


}
