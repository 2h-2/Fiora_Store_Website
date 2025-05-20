package com.website.ecom_project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.ecom_project.model.dto.AttributeDto;
import com.website.ecom_project.model.entity.Attribute;
import com.website.ecom_project.model.entity.Color;
import com.website.ecom_project.model.entity.Size;
import com.website.ecom_project.service.AttributeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AttributeController {
    
    private final AttributeService attributeService;
    
    
    @PostMapping("/color")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addColor(@RequestBody AttributeDto dto){
        try {
        
            attributeService.addColor(dto);
            return new ResponseEntity<>("Color Added Successsfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/color")
    public ResponseEntity<List<Color>> getAllColors(){
        return new ResponseEntity<>(attributeService.getAllColors(), HttpStatus.OK);
    }

    @DeleteMapping("/color/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteColorById(@PathVariable Long id){
        try {            
            attributeService.deleteColor(id);
            return new ResponseEntity<>("Color Deleted Successfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/size")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addSize(@RequestBody AttributeDto dto){
        try {
        
            attributeService.addSize(dto);
            return new ResponseEntity<>("Size Added Successsfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/size")
    public ResponseEntity<List<Size>> getAllSizes(){
        return new ResponseEntity<>(attributeService.getAllsizes(), HttpStatus.OK);
    }

    @DeleteMapping("/size/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSizeById(@PathVariable Long id){
        try {            
            attributeService.deleteSize(id);
            return new ResponseEntity<>("Size Deleted Successfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    
    @PostMapping("/attribute")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAttribute(@RequestBody AttributeDto dto){
        try {
        
            attributeService.addAttribute(dto);
            return new ResponseEntity<>("Attribute Added Successsfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/attribute")
    public ResponseEntity<List<Attribute>> getAllAttributes(){
        return new ResponseEntity<>(attributeService.getAllAttributes(), HttpStatus.OK);
    }

    @DeleteMapping("/attribute/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAttributeById(@PathVariable Long id){
        try {            
            attributeService.deleteAttribute(id);
            return new ResponseEntity<>("Attribute Deleted Successfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    
}
