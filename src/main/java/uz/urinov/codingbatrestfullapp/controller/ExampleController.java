package uz.urinov.codingbatrestfullapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.codingbatrestfullapp.payload.ExampleDTO;
import uz.urinov.codingbatrestfullapp.service.ExampleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/example")
public class ExampleController {
    private final ExampleService service;

    @Autowired
    public ExampleController(ExampleService service) {
        this.service = service;
    }

    @GetMapping
    private ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getAllExampleForPage(page, size);
    }

    @GetMapping("/id={exampleId}")
    private ResponseEntity<?> getOne(@PathVariable("exampleId") Integer id) {
        return service.getOneExample(id);
    }

    @GetMapping("/category={categoryId}/language={languageId}")
    private ResponseEntity<?> getAllForCategoryAndLanguage(@PathVariable("categoryId") Integer categoryId,
                                                           @PathVariable("languageId") Integer languageId) {
        return service.getAll(languageId, categoryId);
    }

    @PostMapping
    private ResponseEntity<?> save(@Valid @RequestBody ExampleDTO dto) {
        return service.addExample(dto);
    }

    @PutMapping("/id={exampleId}")
    private ResponseEntity<?> update(@PathVariable("exampleId") Integer id,@Valid @RequestBody ExampleDTO exampleDTO) {
        return service.editExample(id, exampleDTO);
    }

    @DeleteMapping("/id={exampleId}")
    private ResponseEntity<?> delete(@PathVariable("exampleId") Integer id) {
        return service.deleteExample(id);
    }
}
