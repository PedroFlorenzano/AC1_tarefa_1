package com.example.tarefa1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tarefa1.model.Categoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CategoriaRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Categoria inserirCategoria(Categoria categoria) {
        entityManager.merge(categoria);
        return categoria;
    }

    @Transactional
    public List<Categoria> buscarTodasCategorias() {
        return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
    }

    @Transactional
    public Categoria buscarCategoriaPorNome(String nome) {
        String jpql = "select c from Categoria c where c.nome like :nome";
        TypedQuery<Categoria> query = entityManager.createQuery(jpql, Categoria.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getSingleResult();
    }

    @Transactional
    public Categoria buscarCategoriaPorId(Long id) {
        return entityManager.find(Categoria.class, id);
    }

    @Transactional
    public Categoria atualizarCategoria(Categoria categoria, Long id) {
        Categoria categoriaAntiga = this.buscarCategoriaPorId(id);
        categoriaAntiga.setNome(categoria.getNome());
        categoriaAntiga.setDescricao(categoria.getDescricao());
        return this.entityManager.merge(categoriaAntiga);
    }

    @Transactional
    public void excluirCategoriaPorId(Long id) {
        Categoria categoriaAntiga = entityManager.find(Categoria.class, id);
        this.entityManager.remove(categoriaAntiga);
    }

}
