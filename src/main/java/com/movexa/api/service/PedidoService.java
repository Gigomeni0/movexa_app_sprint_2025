package com.movexa.api.service;

import com.movexa.api.model.Pedido;
import com.movexa.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Cria um novo pedido (coleta)
    public Pedido criarPedido(Pedido pedido) {
        // Define o status inicial como "pendente" (ou "senderial", conforme seu diagrama)
        pedido.setStatus("pendente");
        pedido.setData(LocalDate.now()); // Define a data atual se não for fornecida
        return pedidoRepository.save(pedido);
    }

    // Conclui um pedido existente
    public Pedido concluirPedido(Long id, Long finalizadoPor, String observacoes) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);

        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setStatus("concluido");
            pedido.setFinalizadoPor(finalizadoPor);
            pedido.setObservacoes(observacoes);
            return pedidoRepository.save(pedido);
        } else {
            throw new RuntimeException("Pedido não encontrado com ID: " + id);
        }
    }

    // Outros métodos úteis (opcional)
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }
}