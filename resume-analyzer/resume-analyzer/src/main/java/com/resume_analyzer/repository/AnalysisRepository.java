
package com.resume_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resume_analyzer.entity.Analysis;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {

}
