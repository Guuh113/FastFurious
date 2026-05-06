/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.gustavo.FastandFuriousFood.dto;

import java.util.List;

/**
 *
 * @author sesi3dia
 */
public record PedidoDTO(
        List<ItemPedidoDTO> itens
        ) {

}
