package com.movexa.api.controller;

import com.movexa.api.model.Veiculo;
import com.movexa.api.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> listar() {
        return veiculoRepository.findAll();
    }

    @PostMapping
    public Veiculo criar(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }
}