package com.example.tarefa1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.tarefa1.model.Categoria;
import com.example.tarefa1.model.Produto;
import com.example.tarefa1.repository.CategoriaRepository;
import com.example.tarefa1.repository.ProdutoRepository;

@SpringBootApplication
public class Tarefa1Application {

	public static void main(String[] args) {
		SpringApplication.run(Tarefa1Application.class, args);
	}

	@Bean
	public CommandLineRunner initRunner(@Autowired ProdutoRepository produtoRepository, @Autowired CategoriaRepository categoriaRepository){
		return args -> {

			Produto produto1 = new Produto(1L, "Garrafa Pet", 2);
			Produto produto2 = new Produto(2L, "Galaxy S10", 1);
			Produto produto3 = new Produto(3L, "Notebook", 1);
			
			produtoRepository.inserirEAtualizarProduto(produto1);
			produtoRepository.inserirEAtualizarProduto(produto2);
			produtoRepository.inserirEAtualizarProduto(produto3);

			List<Produto> listaDeProdutos = produtoRepository.selecionarTodosProdutos();
			listaDeProdutos.forEach(System.out::println);

			produtoRepository.obterProdutoPorId(3L);

			produtoRepository.excluirProdutoPorId(3L);

			categoriaRepository.inserirEAtualizarCategoria(new Categoria(1L, "Plástico", "Produtos feitos de plástico"));
			categoriaRepository.inserirEAtualizarCategoria(new Categoria(2L, "Eletrônicos", "Produtos eletrônicos"));
		};
	}

}
