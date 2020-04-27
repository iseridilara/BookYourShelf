package com.oak.bookyourshelf.service.admin_panel;

import com.oak.bookyourshelf.model.Category;
import com.oak.bookyourshelf.model.Payment;
import com.oak.bookyourshelf.repository.admin_panel.AdminPanelCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminPanelCategoryService {
    final
    AdminPanelCategoryRepository adminPanelCategoryRepository;

    public AdminPanelCategoryService(AdminPanelCategoryRepository adminPanelCategoryRepository) {
        this.adminPanelCategoryRepository = adminPanelCategoryRepository;
    }

    public Iterable<Category> listAll() {
        return adminPanelCategoryRepository.findAll();
    }

    public Iterable<Category> getAllByCategory(String productType) {
        switch (productType) {
            case "Book":
                return adminPanelCategoryRepository.getAllByCategory(Category.ProductType.values()[0]);
            case "E-Book":
                return adminPanelCategoryRepository.getAllByCategory(Category.ProductType.values()[1]);
            case "Audio Book":
                return adminPanelCategoryRepository.getAllByCategory(Category.ProductType.values()[2]);
        }
        return null;
    }

    public void save(Category category) {
        adminPanelCategoryRepository.save(category);
    }

    public Category get(int id) {
        return adminPanelCategoryRepository.findById(id).get();
    }

    public Category getByName(String name) {
        return adminPanelCategoryRepository.findCategoryByName(name);
    }

    public List<Category> getAllByName(String name) {
        return adminPanelCategoryRepository.findAllByName(name);
    }

    public void delete(int id) {
        adminPanelCategoryRepository.deleteById(id);
    }
}
