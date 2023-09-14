package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.exceptions.EntityNotFoundException;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) throws EntityNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        Category entity = category.orElseThrow(()-> new EntityNotFoundException("Id not found"));

        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category = categoryRepository.save(category);
        return new CategoryDTO(category);
    }
}
