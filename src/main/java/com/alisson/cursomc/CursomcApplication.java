package com.alisson.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alisson.cursomc.domain.Categoria;
import com.alisson.cursomc.domain.Cidade;
import com.alisson.cursomc.domain.Estado;
import com.alisson.cursomc.domain.Produto;
import com.alisson.cursomc.repositories.CategoriaRepository;
import com.alisson.cursomc.repositories.CidadeRepository;
import com.alisson.cursomc.repositories.EstadoRepository;
import com.alisson.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// instanciando as categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		// instanciando os produtos
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		// adcionando produtos as categorias
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		// adcionando categorias aos produtos
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		// salvando as categorias e produtos
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		// instanciando os estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		// instanciando as cidades 
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		// adcionando as cidades aos estados
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
	}

}
