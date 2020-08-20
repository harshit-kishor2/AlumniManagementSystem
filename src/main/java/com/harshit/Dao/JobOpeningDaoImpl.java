package com.harshit.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.harshit.Mapper.CompanyListMapper;
import com.harshit.Mapper.JobOpeningMapper;
import com.harshit.model.Company;
import com.harshit.model.InternshipJobOpening;

@Repository
public class JobOpeningDaoImpl implements JobOpeningDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	



	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	@Override
	public InternshipJobOpening postJobOpening(InternshipJobOpening newJob) {
		// TODO Auto-generated method stub
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		ArrayList<String> companiesName = new ArrayList<>();
		for (int i = 0; i < getAllCompany().size(); i++) {
			companiesName.add(getAllCompany().get(i).getCompanyName());
		}
		try {
			String postJobSql = "insert into stayconnected.jobopening(jobid,position,location,startdate,payrate,jobdescription,hoursperweek,enddate,companyname) values(?,?,?,?,?,?,?,?,?)";
			String insertCompanySql = "insert into stayconnected.company(name,address,phone_number) values(?,?,?)";
			if (!(companiesName.contains(newJob.getCompanyName().getCompanyName()))) {
				jdbcTemplate.update(insertCompanySql, newJob.getCompanyName().getCompanyName(), newJob.getLocation(),
						newJob.getCompanyName().getPhoneNumber());
			}
			if (!newJob.getHoursPerWeek().equals("")) {
				jdbcTemplate.update(postJobSql, newJob.getJobID(), newJob.getPosition(), newJob.getLocation(),
						newJob.getStartDate(), newJob.getPayrate(), newJob.getJobDescription(),
						Integer.parseInt(newJob.getHoursPerWeek()), newJob.getEndDate(),
						newJob.getCompanyName().getCompanyName());
			} else {
				jdbcTemplate.update(postJobSql, newJob.getJobID(), newJob.getPosition(), newJob.getLocation(),
						newJob.getStartDate(), newJob.getPayrate(), newJob.getJobDescription(), null,
						newJob.getEndDate(), newJob.getCompanyName().getCompanyName());
			}
			String jobQualificationSql = "insert into stayconnected.jobqualification(proficiancy,jobid,skillid) values(?,?,?)";
			for (int i = 0; i < newJob.getSkills().size(); i++) {
				jdbcTemplate.update(jobQualificationSql, newJob.getProficiancy(), newJob.getJobID(),
						newJob.getSkills().get(i).getSkillID());
			}
			transactionManager.commit(status);
		} catch (Exception e) {
			System.out.println("Error in posting Job, rolling back"+e);
			transactionManager.rollback(status);
			return null;
		}
		return newJob;
	}

	@Override
	public List<InternshipJobOpening> getAllJobOpenings() {
		// TODO Auto-generated method stub
		//String jobOpeningSQL = "select p.*, q.proficiancy as proficiancy from stayconnected.jobopening as p, stayconnected.jobqualification as q where p.jobid=q.jobid group by p.jobid,q.proficiancy order by p.jobid";
		String jobOpeningSQL = "select p.*, q.proficiancy as proficiancy,r.phone_number from stayconnected.jobopening as p, stayconnected.jobqualification as q,stayconnected.company as r where p.jobid=q.jobid and p.companyName=r.name group by p.jobid,q.proficiancy order by p.jobid";
		List<InternshipJobOpening> jobOpenings = jdbcTemplate.query(jobOpeningSQL, new JobOpeningMapper());
		System.out.println(jobOpenings);
		return jobOpenings;

	}

	@Override
	public List<Company> getAllCompany() {
		// TODO Auto-generated method stub

		String jobOpeningSQL = "select * from stayconnected.company";
		List<Company> companies = jdbcTemplate.query(jobOpeningSQL, new CompanyListMapper());
		return companies;

	}

	@Override
	public InternshipJobOpening getJobOpening(int jobID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeJobOpening(int jobID) {
		// TODO Auto-generated method stub
		return false;
	}
}