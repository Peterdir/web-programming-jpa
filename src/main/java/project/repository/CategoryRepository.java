package project.repository;

import java.util.List;

import project.entity.Category;

public interface CategoryRepository {
	List<Category> findAll();
    Category findById(int id);
    void insert(Category category);
    void update(Category category);
    void delete(int id);
}
