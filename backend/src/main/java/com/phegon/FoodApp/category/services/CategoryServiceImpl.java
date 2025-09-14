package com.phegon.FoodApp.category.services;


import com.phegon.FoodApp.category.dtos.CategoryDTO;
import com.phegon.FoodApp.category.entity.Category;
import com.phegon.FoodApp.category.repository.CategoryRepository;
import com.phegon.FoodApp.exceptions.NotFoundException;
import com.phegon.FoodApp.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public Response<CategoryDTO> addCategory(CategoryDTO categoryDTO) {

        log.info("Inside addCategory()");

        Category category = modelMapper.map(categoryDTO, Category.class);

        categoryRepository.save(category);


        return Response.<CategoryDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Category added successfully")
                .build();
    }


    @Override
    public Response<CategoryDTO> updateCategory(CategoryDTO categoryDTO) {

        log.info("Inside updateCategory()");

        Category category = categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(()-> new NotFoundException("Category Not Found"));

        if (categoryDTO.getName() != null && !categoryDTO.getName().isEmpty()) category.setName(categoryDTO.getName());
        if (categoryDTO.getDescription() != null) category.setDescription(categoryDTO.getDescription());

        categoryRepository.save(category);


        return Response.<CategoryDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Category updated successfully")
                .build();
    }


    @Override
    public Response<CategoryDTO> getCategoryById(Long id) {

        log.info("Inside getCategoryById()");


        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Category Not Found"));

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);


        return Response.<CategoryDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Category retrieved successfully")
                .data(categoryDTO)
                .build();
    }

    @Override
    public Response<List<CategoryDTO>> getAllCategories() {

        log.info("Inside getAllCategories()");
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();


        return Response.<List<CategoryDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("All categories retrieved successfully")
                .data(categoryDTOS)
                .build();
    }

    @Override
    public Response<?> deleteCategory(Long id) {

        log.info("Inside deleteCategory()");
        if (!categoryRepository.existsById(id)){
            throw  new NotFoundException("Category Not Found");
        }

        categoryRepository.deleteById(id);


        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Category deleted successfully")
                .build();

    }
}







