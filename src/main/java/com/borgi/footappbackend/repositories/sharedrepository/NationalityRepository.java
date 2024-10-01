package com.borgi.footappbackend.repositories.sharedrepository;

import com.borgi.footappbackend.entities.shared.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalityRepository extends JpaRepository<Nationality, Number> {
}
