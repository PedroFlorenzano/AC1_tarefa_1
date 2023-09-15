package com.example.tarefa1.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cat_nome",length = 200, nullable = false)
    private String  nome;

    @Column(name = "cat_descricao", length = 200, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "categoriaProdutos", fetch = FetchType.EAGER)
    private List<Produto> produtos;

    public Categoria(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }
}
