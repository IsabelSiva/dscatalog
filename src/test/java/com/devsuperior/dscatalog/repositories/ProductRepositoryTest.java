package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;
    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingId = 1000L;
    }
    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){

        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultNotFoundExceptionWhenIdDoesntExists(){

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }
}
