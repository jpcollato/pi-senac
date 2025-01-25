package com.jpcycles.senacpi.controller;

import com.jpcycles.senacpi.model.Bicicleta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class BicicletaController {

    private List<Bicicleta> bicicletas = new ArrayList();

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/estoque")
    public String estoqueBicicleta(Model model) {
        model.addAttribute("bicicleta", new Bicicleta());
        model.addAttribute("bicicletas", bicicletas);
        return "estoque";
    }

    @PostMapping("/cadastrarBici")
    public String cadastrarBicicleta(@ModelAttribute Bicicleta bicicleta, Model model) {
        bicicleta.setId(bicicletas.size() + 1);
        bicicleta.setSaldo(0.0);
        if (bicicleta.getPrecoCusto() == null) {
            bicicleta.setPrecoCusto(0.0);
        }
        if (bicicleta.getPrecoVenda() == null) {
            bicicleta.setPrecoVenda(0.0);
        }
        bicicletas.add(bicicleta);
        return "redirect:/estoque";
    }

    @GetMapping("/compras")
    public String comprasBicicletas(Model model) {
        model.addAttribute("bicicleta", new Bicicleta());
        model.addAttribute("bicicletas", bicicletas);
        return "compras";
    }

    @PostMapping("/comprarBici")
    public String comprarBicicleta(@ModelAttribute Bicicleta bicicleta, Model model) {
        Bicicleta b = bicicletas.get(bicicleta.getId() - 1);
        b.setPrecoCusto(bicicleta.getPrecoCusto());
        b.setPrecoVenda(bicicleta.getPrecoVenda());
        b.setSaldo(b.getSaldo() + bicicleta.getSaldo());
        bicicletas.set(bicicleta.getId() - 1, b);
        return "redirect:/estoque";
    }

    @GetMapping("/vendas")
    public String vendasBicicletas(Model model) {
        model.addAttribute("bicicleta", new Bicicleta());
        model.addAttribute("bicicletas", bicicletas);
        return "vendas";
    }

    @PostMapping("/venderBici")
    public String venderBicicleta(@ModelAttribute Bicicleta bicicleta, Model model) {
        String message = "";
        Bicicleta b = bicicletas.get(bicicleta.getId() - 1);
        b.setPrecoVenda(bicicleta.getPrecoVenda());
        if (bicicleta.getSaldo() < b.getSaldo()) {
            b.setSaldo(b.getSaldo() - bicicleta.getSaldo());
            bicicletas.set(bicicleta.getId() - 1, b);
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
