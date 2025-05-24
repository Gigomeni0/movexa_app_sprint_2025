package com.movexa.api.controller;

import com.movexa.api.dto.VeiculoDTO;
import com.movexa.api.model.Veiculo;
import com.movexa.api.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public List<VeiculoDTO> listar() {
        return veiculoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> criar(@Valid @RequestBody VeiculoDTO dto) {
        Veiculo veiculo = toEntity(dto);
        Veiculo salvo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody VeiculoDTO dto) {
        Optional<Veiculo> opt = veiculoRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Veiculo veiculo = opt.get();
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setCor(dto.getCor());
        veiculoRepository.save(veiculo);
        return ResponseEntity.ok(toDTO(veiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (!veiculoRepository.existsById(id)) return ResponseEntity.notFound().build();
        veiculoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares para conversão
    private VeiculoDTO toDTO(Veiculo v) {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(v.getId());
        dto.setPlaca(v.getPlaca());
        dto.setModelo(v.getModelo());
        dto.setCor(v.getCor());
        return dto;
    }

    private Veiculo toEntity(VeiculoDTO dto) {
        Veiculo v = new Veiculo();
        v.setPlaca(dto.getPlaca());
        v.setModelo(dto.getModelo());
        v.setCor(dto.getCor());
        return v;
    }
}