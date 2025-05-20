package com.website.ecom_project.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.website.ecom_project.model.dto.CategoryDto;
import com.website.ecom_project.model.entity.Category;
import com.website.ecom_project.model.mapper.CategoryMapper;
import com.website.ecom_project.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepo;
    private final CategoryMapper categoryMapper;

    public void addCategory(CategoryDto dto){

        Category category = categoryMapper.toEntity(dto);

        if(dto.getCategoryId() != null){
            Category cat= categoryRepo.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("product not found: " ));
            category.setParentCategory(cat);
        }
        
        categoryRepo.save(category);
    }

    public List<Category> getAll(){
        return categoryRepo.findAll();
    }

    public void delete(Long id){
        if (!categoryRepo.existsById(id)) {
        throw new RuntimeException("Category not found with ID: " + id);
    }
        categoryRepo.deleteById(id);
    }
}

