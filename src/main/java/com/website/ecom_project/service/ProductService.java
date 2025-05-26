package com.website.ecom_project.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.website.ecom_project.model.dto.ProductDto;
import com.website.ecom_project.model.dto.ProductResponse;
import com.website.ecom_project.model.dto.ProductVariationDto;
import com.website.ecom_project.model.dto.ReviewDto;
import com.website.ecom_project.model.entity.Category;
import com.website.ecom_project.model.entity.Color;
import com.website.ecom_project.model.entity.Product;
import com.website.ecom_project.model.entity.ProductVariation;
import com.website.ecom_project.model.entity.Review;
import com.website.ecom_project.model.entity.Size;
import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.model.mapper.ProductMapper;
import com.website.ecom_project.repository.CategoryRepository;
import com.website.ecom_project.repository.ColorRepository;
import com.website.ecom_project.repository.ProductRepository;
import com.website.ecom_project.repository.ProductVariationRepository;
import com.website.ecom_project.repository.ReviewRepository;
import com.website.ecom_project.repository.SizeRepository;
import com.website.ecom_project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductVariationRepository productVarRepo;
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ProductMapper productMapper;
    private final ColorRepository colorRepo; 
    private final SizeRepository sizeRepo;
    private final UserRepository userRepo;
    private final ReviewRepository reviewRepo;
    
    public void create(ProductDto dto){
        Product product = productMapper.toEntity(dto);
        
        if (dto.getCategoriesIds().isEmpty()) {
            throw new RuntimeException("No category IDs provided");
        }
        
        Set<Category> categories = dto.getCategoriesIds().stream()
        .map(id -> {
            //System.out.println("Looking for Category ID: " + id); 
            return categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));
        })
        .collect(Collectors.toSet());       
        
        product.setCategories(categories); 

        productRepo.save(product); 
    }

    public ProductResponse getProduct(Long id){
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        Set<String> categories = product.getCategories().stream().map(Category::getName).collect(Collectors.toSet());
        
        ProductResponse response = productMapper.toResponse(product);
        response.setCategories(categories);
        return response;
    }


    public Page<ProductResponse> getAllProduct(int page, int size){

        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepo.findAll(pageable);
        
        return productPage.map(product -> {
            ProductResponse res = productMapper.toResponse(product);
            Set<String> categories = product.getCategories()
                                            .stream()
                                            .map(Category::getName)
                                            .collect(Collectors.toSet());
            res.setCategories(categories);
            return res;
        });
    }
    
    public void createProductVarition(ProductVariationDto dto){

        Product product = productRepo.findById(dto.getProductId()).
        orElseThrow(() -> new RuntimeException("product not found: " ));
        
        ProductVariation productVar = productMapper.toVariation(dto);
        
        productVar.setProduct(product); 
        
        Size size = sizeRepo.findById(dto.getSizeId())
                .orElseThrow(() -> new RuntimeException("size not found: " + dto.getSizeId()));
        
        Color color = colorRepo.findById(dto.getColorId())
                .orElseThrow(() -> new RuntimeException("color not found: " + dto.getColorId()));

        
        productVar.setSize(size); 
        productVar.setColor(color); 
        

        productVarRepo.save(productVar); 
    }

    
    public Page<ProductVariation> getProductVariation(Long id, int page, int size){
        PageRequest pageable = PageRequest.of(page, size);
        return productVarRepo.findAllByProductId(id, pageable);
    }

    // Review Service

    public void addReview(ReviewDto review){
        Product product = productRepo.findById(review.getProductId())
        .orElseThrow(() -> new RuntimeException("Product not found: " ));

        User user = userRepo.findById(review.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found: " ));

        Review reviewObj = new Review();
        reviewObj.setComment(review.getComment());
        reviewObj.setRate(review.getRate());
        reviewObj.setUser(user);
        reviewObj.setProduct(product);

        reviewRepo.save(reviewObj);
    }

    public List<Review> getAllReviewByProductId(Long id){
        return reviewRepo.findAllByProductId(id);
    }

} 