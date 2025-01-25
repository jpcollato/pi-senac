package com.jpcycles.senacpi.controller;

import com.jpcycles.senacpi.model.Bicicleta;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BicicletaController {
   private List<Bicicleta> bicicletas = new ArrayList();
   
  @RequestMapping("/")
  public String home(){
      return "index";
  }
}
