package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.DetalhesPedidoDto;
import br.com.alura.comex.controller.dto.PedidoDto;
import br.com.alura.comex.entity.Pedido;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listarTodos(@RequestParam(defaultValue = "0") int pagina){
        Pageable pageable = PageRequest.of(pagina, 5, Sort.Direction.DESC, "data");
        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
        Page<PedidoDto> paginaPedidos = PedidoDto.converter(pedidos);

        return ResponseEntity.ok().body(paginaPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPedidoDto> buscarPedidoPorId(@PathVariable Long id){
        Optional<Pedido> optionalPedido= pedidoRepository.findById(id);
        if (optionalPedido.isPresent()){
            return ResponseEntity.ok().body(new DetalhesPedidoDto(optionalPedido.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
