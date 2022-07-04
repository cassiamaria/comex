package br.com.alura.comex;

import br.com.alura.comex.model.projecao.PedidosPorCategoriaProjecao;
import br.com.alura.comex.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PedidoRepositoryTest {
    @Autowired
    private PedidoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaRetornarUmRegistroParaCadaCategoria() {
        List<PedidosPorCategoriaProjecao> resultado = repository.findPedidosPorCategoria();

        assertThat(resultado)
                .hasSize(2)
                .extracting(PedidosPorCategoriaProjecao::getNome,
                        PedidosPorCategoriaProjecao::getQuantidadeProdutos,
                        PedidosPorCategoriaProjecao::getMontanteVendido)
                .containsExactly(
                        tuple(("JOGOS"), (7L), new BigDecimal("213.50")),
                        tuple(("FILMES"), (2L), new BigDecimal("60.00"))
                );
    }
}