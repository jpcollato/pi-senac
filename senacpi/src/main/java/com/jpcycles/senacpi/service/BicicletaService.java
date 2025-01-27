package com.jpcycles.senacpi.service;

import com.jpcycles.senacpi.model.Bicicleta;
import com.jpcycles.senacpi.repository.BicicletaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BicicletaService {

    @Autowired
    BicicletaRepository biciRepo;

    public Bicicleta buscarPorId(Integer id) {
        return biciRepo.findById(id).orElseThrow();
    }

    public void cadastrarBicicleta(Bicicleta bici) {
        bici.setId(null);
        biciRepo.save(bici);

    }

    public List<Bicicleta> listarBicicletas() {
        return biciRepo.findAll();
    }

    public void excluirBicicleta(Integer id) {
        Bicicleta b = buscarPorId(id);
        biciRepo.delete(b);
    }

    public Bicicleta editarBicicleta(Integer id, Bicicleta bicicleta) {
        Bicicleta b = buscarPorId(id);
        b.setMarca(bicicleta.getMarca());
        b.setModelo(bicicleta.getModelo());
        b.setAno(bicicleta.getAno());
        b.setTamanho(bicicleta.getTamanho());
        return biciRepo.save(b);
    }

    public Bicicleta comprarBicicleta(Integer id, Bicicleta bicicleta) {
        Bicicleta b = buscarPorId(id);
        b.setPrecoCusto(bicicleta.getPrecoCusto());
        b.setPrecoVenda(bicicleta.getPrecoVenda());
        b.setSaldo(b.getSaldo() + bicicleta.getSaldo());
        return biciRepo.save(b);
    }

    public boolean venderBicicleta(Integer id, Bicicleta bicicleta) {
        Bicicleta b = buscarPorId(id);
        if (b.getSaldo() > bicicleta.getSaldo()) {
            b.setPrecoVenda(bicicleta.getPrecoVenda());
            b.setSaldo(b.getSaldo() - bicicleta.getSaldo());
            biciRepo.save(b);
            return true;
        } else {
            return false;
        }
    }
}
