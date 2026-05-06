/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.gustavo.FastandFuriousFood.dto;

import java.math.BigDecimal;

/**
 *
 * @author sesi3dia
 */
public record ProdutoDTO(
     String nome,
     String descricao,
     BigDecimal preco,
     String categoria) {

}
