package com.jpcycles.senacpi.controller;

import com.jpcycles.senacpi.model.Bicicleta;
import com.jpcycles.senacpi.service.BicicletaService;
import jakarta.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BicicletaController {

    @Autowired
    BicicletaService biciService;

    private List<Bicicleta> bicicletas = new ArrayList();

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/estoque")
    public String estoqueBicicleta(Model model) {
        model.addAttribute("bicicleta", new Bicicleta());
        model.addAttribute("bicicletas", biciService.listarBicicletas());
        return "estoque";
    }

    @PostMapping("/cadastrarBici")
    public String cadastrarBicicleta(@Valid @ModelAttribute Bicicleta bicicleta, Model model) {
        bicicleta.setSaldo(0.0);
        if (bicicleta.getPrecoCusto() == null) {
            bicicleta.setPrecoCusto(0.0);
        }
        if (bicicleta.getPrecoVenda() == null) {
            bicicleta.setPrecoVenda(0.0);
        }
        biciService.cadastrarBicicleta(bicicleta);
        return "redirect:/estoque";
    }

    @GetMapping("/compras")
    public String comprasBicicletas(Model model) {
        model.addAttribute("bicicleta", new Bicicleta());

        return "compras";
    }

    @PostMapping("/comprarBici")
    public String comprarBicicleta(@ModelAttribute Bicicleta bicicleta, Model model) {

        biciService.comprarBicicleta(bicicleta.getId(), bicicleta);
        return "redirect:/estoque";
    }

    @GetMapping("/vendas")
    public String vendasBicicletas(Model model) {
        model.addAttribute("bicicleta", new Bicicleta());

        return "vendas";
    }

    @PostMapping("/venderBici")
    public String venderBicicleta(@ModelAttribute Bicicleta bicicleta, Model model) {

        if (biciService.venderBicicleta(bicicleta.getId(), bicicleta)) {
            return "redirect:/estoque";
        } else {
            return "redirect:/erro";
        }

    }

    @GetMapping("/erro")
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {

        return "databaseError";
    }
}
