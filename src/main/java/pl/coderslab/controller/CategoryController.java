package pl.coderslab.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.domain.Category;
import pl.coderslab.repository.CategoryRepository;

@CrossOrigin(origins = { "http://localhost:4200", "http://127.0.0.1:8081" })
@RestController
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		System.out.println("getAllCategories");

		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(categories::add);

		return categories;
	}

	@PostMapping(value = "/categories")
	public Category postCategory(@RequestBody Category category) {
		Category newCategory = new Category();
		newCategory.setCategoryName(category.getCategoryName());
		Category resultCategory = categoryRepository.save(newCategory);
		return resultCategory;
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") long id) {
		System.out.println("delete category - id:" + id);

		categoryRepository.deleteById(id);

		return new ResponseEntity<>("Category deleted.", HttpStatus.OK);
	}

	@DeleteMapping("/categories/delete")
	public ResponseEntity<String> deleteAllCategories() {
		System.out.println("delete all categories");

		categoryRepository.deleteAll();

		return new ResponseEntity<>("No more categories.", HttpStatus.OK);
	}

	@GetMapping(value = "/categories/{id}")
	public ResponseEntity<Category> findById(@PathVariable long id) {

		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if (optionalCategory.isPresent()) {
			Category resultCategory = optionalCategory.get();
			return new ResponseEntity<>(resultCategory, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
		System.out.println("update song - id:" + id);

		Optional<Category> optionalCategory = categoryRepository.findById(id);

		if (optionalCategory.isPresent()) {
			Category resultCategory = optionalCategory.get();
			resultCategory.setCategoryName(category.getCategoryName());

			return new ResponseEntity<>(categoryRepository.save(resultCategory), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}