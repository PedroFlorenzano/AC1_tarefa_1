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

			// Instanciando os Produtos
			Produto produtoTeclado = new Produto( "Teclado Gamer", 2);
			Produto produtoCelular = new Produto( "Celular", 6);
			Produto produtoNotebook = new Produto( "Notebook", 10);
			Produto produtoMesa = new Produto( "Mesa", 4);
			
			// Salvando produtos dentro do banco de dados
			produtoRepository.inserirProduto(produtoTeclado);
			produtoRepository.inserirProduto(produtoCelular);
			produtoRepository.inserirProduto(produtoNotebook);
			produtoRepository.inserirProduto(produtoMesa);

			// Listando todos os produtos salvos
			List<Produto> listaDeProdutos = produtoRepository.buscarTodosProdutos();
			listaDeProdutos.forEach(System.out::println);

			// Instanciando um produto novo para substituir (atualizar) por um antigo
			Produto produtoMouse = new Produto("Mouse Gamer", 20);
			produtoRepository.atualizarProduto(produtoMouse, 4L);

			// // Excluindo um produto
			produtoRepository.excluirProdutoPorId(4L);
			
			// Instanciando categorias
			Categoria categoriaJogo = new Categoria( "Categoria de Jogos", "Categoria específica para jogos de qualidade!");
			Categoria categoriaEletronico = new Categoria( "Categoria Eletrônicos", "Categoria específica equipamentos eletrônicos!");
			Categoria categoriaUtilitarios = new Categoria( "Categoria de Utilitários", "Categoria específica para utilitários!");
			
			// Salvando categorias dentro do banco de dados
			categoriaRepository.inserirCategoria(categoriaJogo);
			categoriaRepository.inserirCategoria(categoriaEletronico);
			categoriaRepository.inserirCategoria(categoriaUtilitarios);
			
			// Listando todas as categorias salvas
			List<Categoria> listaDeCategorias = categoriaRepository.buscarTodasCategorias();
			listaDeCategorias.forEach(System.out::println);

			// Atualizando Categoria
			Categoria categoriaGeral = new Categoria("Categoria Geral", "Categoria Geral!");
			categoriaRepository.atualizarCategoria(categoriaGeral, 3L);
			
			// Excluindo uma categoria
			categoriaRepository.excluirCategoriaPorId(3L);
			
			// Buscando um produto para adicionar uma categoria e atualiza-la
			Produto produtoEncontradoTeclado = produtoRepository.buscarProdutoPorId(1L);
			System.out.println("Produto encontrado no banco: " + produtoEncontradoTeclado);
			produtoRepository.vinculaProdutoEmCategoria(categoriaJogo, produtoEncontradoTeclado.getId());

			Produto produtoEncontradoCelular = produtoRepository.buscarProdutoPorId(2L);
			System.out.println("Produto encontrado no banco: " + produtoEncontradoTeclado);
			produtoRepository.vinculaProdutoEmCategoria(categoriaEletronico, produtoEncontradoCelular.getId());

			Produto produtoEncontradoNotebook = produtoRepository.buscarProdutoPorId(3L);
			System.out.println("Produto encontrado no banco: " + produtoEncontradoNotebook);
			produtoRepository.vinculaProdutoEmCategoria(categoriaEletronico, produtoEncontradoNotebook.getId());
		};
	}
}
