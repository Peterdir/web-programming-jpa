package project.service.impl;

import java.util.List;

import project.entity.Category;
import project.repository.CategoryRepository;
import project.repository.impl.CategoryRepositoryImpl;
import project.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepo = new CategoryRepositoryImpl();

	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	@Override
	public Category findById(int id) {
		return categoryRepo.findById(id);
	}

	@Override
	public void insert(Category category) {
		categoryRepo.insert(category);
	}

	@Override
	public void update(Category category) {
		categoryRepo.update(category);
	}

	@Override
	public void delete(int id) {
		categoryRepo.delete(id);
	}
}
