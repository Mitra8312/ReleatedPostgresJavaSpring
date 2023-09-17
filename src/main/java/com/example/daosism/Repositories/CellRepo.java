package com.example.daosism.Repositories;

import com.example.daosism.Models.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepo extends JpaRepository<Cell, Integer> {
    Cell findCellByHash(String hash);
}
