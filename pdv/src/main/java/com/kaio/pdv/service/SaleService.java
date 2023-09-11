package com.kaio.pdv.service;

import com.kaio.pdv.Entity.ItemSale;
import com.kaio.pdv.Entity.Product;
import com.kaio.pdv.Entity.Sale;
import com.kaio.pdv.Entity.User;
import com.kaio.pdv.dto.ProductDTO;
import com.kaio.pdv.dto.ProductInfoDTO;
import com.kaio.pdv.dto.SaleDTO;
import com.kaio.pdv.dto.SaleInfoDTO;
import com.kaio.pdv.exception.InvalidOperationException;
import com.kaio.pdv.exception.NoItemException;
import com.kaio.pdv.repository.ItemSaleRepository;
import com.kaio.pdv.repository.ProductRepository;
import com.kaio.pdv.repository.SaleRepository;
import com.kaio.pdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;

    public SaleService(@Autowired UserRepository userRepository, ProductRepository productRepository, SaleRepository saleRepository, ItemSaleRepository itemSaleRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.itemSaleRepository = itemSaleRepository;
    }

    public List<SaleInfoDTO> findAll() {
        return saleRepository.findAll().stream().map(sale -> getSaleInfo(sale)).collect(Collectors.toList());
    }

    private SaleInfoDTO getSaleInfo(Sale sale) {

        List<ProductInfoDTO> products = getProductInfo(sale.getItems());

        BigDecimal total = getTotal(products);

        SaleInfoDTO saleInfoDTO = new SaleInfoDTO();
        saleInfoDTO.setUser(sale.getUser().getName());
        saleInfoDTO.setDate(sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        saleInfoDTO.setProduts(products);
        saleInfoDTO.setTotal(total);

        return saleInfoDTO;
    }

    private BigDecimal getTotal(List<ProductInfoDTO> products) {
        BigDecimal total = new BigDecimal(0);
        for(int i = 0; i < products.size(); i++) {
            total = total.add(products.get(i).getPrice().multiply(new BigDecimal(products.get(i).getQuantity())));
        }

        return total;
    }

    private List<ProductInfoDTO> getProductInfo(List<ItemSale> items) {

        if(CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(item -> {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            productInfoDTO.setId(item.getId());
            productInfoDTO.setPrice(item.getProduct().getPrice());
            productInfoDTO.setDescription(item.getProduct().getDescription());
            productInfoDTO.setQuantity(item.getQuanquity());
            return productInfoDTO;
        }).collect(Collectors.toList());
    }

    @Transactional
    public long save(SaleDTO sale) {

        User user = userRepository.findById(sale.getUserid()).orElseThrow(() -> new NoItemException("Usuário não encontrado"));

        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.setDate(LocalDate.now());
        List<ItemSale> items = getItemSale(sale.getItems());

        newSale = saleRepository.save(newSale);

        saveItemSale(items, newSale);

        return newSale.getId();

    }

    private void saveItemSale(List<ItemSale> items, Sale newSale) {
        for(ItemSale item: items) {
            item.setSale(newSale);
            itemSaleRepository.save(item);
                 }
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products) {

        if(products.isEmpty()) {
            throw new InvalidOperationException("Não é possível adicionar a venda sem itens!");
        }

        return products.stream().map(item -> {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new NoItemException("Item da venda não encontrando"));

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuanquity(item.getQuantity());

            if(product.getQuantity() == 0) {
                throw new NoItemException("Produto sem estoque.");
            } else if(product.getQuantity() < item.getQuantity()) {
                throw new InvalidOperationException(String.format("A quantidade itens de venda (%s) " + "é maior do que a quantidade disponível no estoque (%s).", item.getQuantity(), product.getQuantity()));
            }

            int total = product.getQuantity() - item.getQuantity();

            product.setQuantity(total);
            productRepository.save(product);

            return itemSale;


        }).collect(Collectors.toList());
    }

    public SaleInfoDTO getById(long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NoItemException("Venda não encontrada."));
        return getSaleInfo(sale);
    }
}
