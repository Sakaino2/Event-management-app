package com.gestion.eventos.api.controller;

import com.gestion.eventos.api.domain.Category;
import com.gestion.eventos.api.dto.CategoryDto;
import com.gestion.eventos.api.mapper.ICategoryMapper;
import com.gestion.eventos.api.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;
    private final ICategoryMapper categoryMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<CategoryDto>> getAlCategories()
    {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id)
    {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(categoryMapper.toDto(category));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {
        Category category = categoryMapper.toEntity(categoryDto);
        Category createdCategory = categoryService.save(category);
        return new ResponseEntity<>(categoryMapper.toDto(createdCategory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto)
    {
        Category category = categoryMapper.toEntity(categoryDto);
        Category updatedCategory = categoryService.save(category);
        return ResponseEntity.ok(categoryMapper.toDto(updatedCategory));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
