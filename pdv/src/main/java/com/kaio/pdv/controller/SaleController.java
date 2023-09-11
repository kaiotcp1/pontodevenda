package com.kaio.pdv.controller;

import com.kaio.pdv.dto.ResponseDTO;
import com.kaio.pdv.dto.SaleDTO;
import com.kaio.pdv.dto.SaleInfoDTO;
import com.kaio.pdv.exception.InvalidOperationException;
import com.kaio.pdv.exception.NoItemException;
import com.kaio.pdv.service.SaleService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(@Autowired SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity(saleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(saleService.getById(id), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity post(@Valid  @RequestBody SaleDTO saleDTO) {
        try {
            saleService.save(saleDTO);
            return new ResponseEntity<>(new ResponseDTO("Venda realiza com sucesso: "), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
