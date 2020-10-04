package br.gov.sp.fatec.springbootprodutora;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootprodutora.entity.Ator;
import br.gov.sp.fatec.springbootprodutora.entity.Diretor;
import br.gov.sp.fatec.springbootprodutora.entity.Duble;
import br.gov.sp.fatec.springbootprodutora.entity.Filme;
import br.gov.sp.fatec.springbootprodutora.entity.Pessoa;
import br.gov.sp.fatec.springbootprodutora.repository.AtorRepository;
import br.gov.sp.fatec.springbootprodutora.repository.DiretorRepository;
import br.gov.sp.fatec.springbootprodutora.repository.DubleRepository;
import br.gov.sp.fatec.springbootprodutora.repository.FilmeRepository;

@SpringBootTest
@Transactional
@Rollback
public class FilmeRepositoryIntegrationTest {

     @Autowired
    private FilmeRepository filmeRepo;

    @Autowired
    private DiretorRepository diretorRepo;

    @Autowired
    private AtorRepository atorRepo;

    @Autowired
    private DubleRepository dubleRepo;

    private static final Logger logger = LoggerFactory.getLogger(FilmeRepositoryIntegrationTest.class);


        @Test
    void testaInsercaoFilme() {
        Diretor diretor = diretorRepo.findByNome("Hop Bowman");
        logger.info("find diretor by name HOP BOWMAN", FilmeRepositoryIntegrationTest.class.getSimpleName());
        Ator ator = atorRepo.findByNome("Owen Simpson");
        logger.info("find ator by name OWEN SIMPSON", FilmeRepositoryIntegrationTest.class.getSimpleName());
        Duble duble = dubleRepo.findByNome("Tyler Briggs");
        logger.info("find duble by name TYLER BRIGGS", FilmeRepositoryIntegrationTest.class.getSimpleName());


        Filme filme = new Filme();
        filme.setNome("O atirador");
        filme.setAno(2002l);
        filme.setDuracao(2.45f);
        filme.setDescricao("Um filme de guerra!");
        filme.setDiretor(diretor);
        filme.setPessoas(new HashSet<Pessoa>());
        filme.getPessoas().add(ator);
        filme.getPessoas().add(duble);
        filmeRepo.save(filme);

        assertNotNull(filme.getId());
    }

     @Test
    void testBuscaFilmePorDiretor() {
        List<Filme> filme = filmeRepo.findByDiretorNome("Luke Winters");
        assertNotNull(filme);
    }

    @Test
    void testBuscaFilmePorDiretorQuery() {
        List<Filme> filme = filmeRepo.buscaFilmePorDiretor("Luke Winters");
        assertNotNull(filme);
    }

    @Test
    void testBuscaFilmePorNomeEDiretorQuery() {
        Filme filme = filmeRepo.buscaFilmePorNomeEDiretor("Uma noite na floresta", "Hop Bowman");
        assertNotNull(filme);
    }
    
}