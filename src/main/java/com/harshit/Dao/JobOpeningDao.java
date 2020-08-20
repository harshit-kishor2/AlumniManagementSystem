package com.harshit.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.harshit.model.Company;
import com.harshit.model.InternshipJobOpening;

@Repository
public interface JobOpeningDao {
	public InternshipJobOpening postJobOpening(InternshipJobOpening newJob);

	public List<InternshipJobOpening> getAllJobOpenings();

	public InternshipJobOpening getJobOpening(int jobID);

	public boolean removeJobOpening(int jobID);

	public List<Company> getAllCompany();
}