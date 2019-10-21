package com.example.simpleapp.repositories;

import com.example.simpleapp.models.Installation;
import org.springframework.data.repository.CrudRepository;

public interface LocalRepository extends CrudRepository<Installation, String> {
}
