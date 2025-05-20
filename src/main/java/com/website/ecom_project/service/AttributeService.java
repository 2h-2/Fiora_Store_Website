package com.website.ecom_project.service;

import java.util.List;

import org.springframework.stereotype.*;

import com.website.ecom_project.model.dto.AttributeDto;
import com.website.ecom_project.model.entity.Attribute;
import com.website.ecom_project.model.entity.Color;
import com.website.ecom_project.model.entity.Size;
import com.website.ecom_project.model.mapper.AttributeMapper;
import com.website.ecom_project.repository.AttributeRepository;
import com.website.ecom_project.repository.ColorRepository;
import com.website.ecom_project.repository.SizeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttributeService {
    private final ColorRepository colorRepo;
    private final SizeRepository sizeRepo;
    private final AttributeRepository attributeRepo;
    private final AttributeMapper attributeMapper;

    public void addColor(AttributeDto dto){
        colorRepo.save(attributeMapper.toColorEntity(dto));
    }

    public List<Color> getAllColors(){
        return colorRepo.findAll();
    }

    public void deleteColor(Long id){
        if (!colorRepo.existsById(id)) {
        throw new RuntimeException("Color not found with ID: " + id);
    }
        colorRepo.deleteById(id);
    }

    public void addSize(AttributeDto dto){
        sizeRepo.save(attributeMapper.toSizeEntity(dto));
    }

    public List<Size> getAllsizes(){
        return sizeRepo.findAll();
    }

    public void deleteSize(Long id){
        if (!sizeRepo.existsById(id)) {
        throw new RuntimeException("Size not found with ID: " + id);
    }
        sizeRepo.deleteById(id);
    }


    public void addAttribute(AttributeDto dto){
        attributeRepo.save(attributeMapper.toAttribute(dto));
    }

    public List<Attribute> getAllAttributes(){
        return attributeRepo.findAll();
    }

    public void deleteAttribute(Long id){
        if (!attributeRepo.existsById(id)) {
        throw new RuntimeException("Attribute not found with ID: " + id);
    }
        attributeRepo.deleteById(id);
    }


    
}
