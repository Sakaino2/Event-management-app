package com.gestion.eventos.api.service;

import com.gestion.eventos.api.domain.Category;
import com.gestion.eventos.api.exception.ResourceNotFoundException;
import com.gestion.eventos.api.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private final ICategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(
                       ()-> new ResourceNotFoundException("Categoría no encontrada con ID: " + id)
                );
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category update(Long id, Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Categoría no encontrada con ID: " + id)
        );

        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());

        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Categoría no encontrada con ID: " + id)
        );
        categoryRepository.deleteById(id);
    }
}
