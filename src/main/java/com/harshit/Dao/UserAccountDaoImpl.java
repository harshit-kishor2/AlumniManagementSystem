package com.harshit.Dao;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.harshit.Mapper.UserAccountStatusMapper;
import com.harshit.Mapper.UserActivationMapper;
import com.harshit.Mapper.UserPasswordMapper;
import com.harshit.Mapper.accountMapper;
import com.harshit.Mapper.authorityMapper;
import com.harshit.Mapper.jobhistoryMapper;
import com.harshit.Mapper.roleMapper;
import com.harshit.Mapper.skillMapper;
import com.harshit.Mapper.userskillsMapper;
import com.harshit.model.ForgotPasswordForm;
import com.harshit.model.JobHistory;
import com.harshit.model.RegistrationForm;
import com.harshit.model.Skill;
import com.harshit.model.UpdateAccountForm;
import com.harshit.model.UserAccount;
import com.harshit.model.UserActivation;

@Repository
public class UserAccountDaoImpl implements UserAccountDao {

	@Autowired
	private DataSourceTransactionManager transactionManager;

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	private String SQL;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	@Override
	public UserAccount createNewAccount(final RegistrationForm account) {
		UserAccount accountForm = new UserAccount();
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			String userAccountSQL = "insert into stayconnected.userAccount(RID,Fname,Lname,email,address,phone_number) values(?,?,?,?,?,?)";
			jdbcTemplate.update(userAccountSQL, account.getAccount().getRoyalID(), account.getAccount().getFirstName(),
					account.getAccount().getLastName(), account.getAccount().getEmail(),
					account.getAccount().getUserAddress(), account.getAccount().getPhoneNumber());
			String jobHistorySQL = "insert into stayconnected.jobHistory(position,companyName,address,startDate,endDate,currentlyemplyed,RID) values(?,?,?,?,?,?,?)";
			ArrayList<JobHistory> usersWorkExp = account.getAccount().getWorkExperience();
			for (int i = 0; i < usersWorkExp.size(); i++) {
				if (!usersWorkExp.get(i).getPosition().equals("")) {
					jdbcTemplate.update(jobHistorySQL, usersWorkExp.get(i).getPosition(),
							usersWorkExp.get(i).getCompanyName(), usersWorkExp.get(i).getAddress(),
							usersWorkExp.get(i).getStartDate(), usersWorkExp.get(i).getEndDate(),
							usersWorkExp.get(i).isCurrentlyEmployed(), account.getAccount().getRoyalID());
				}
			}
			String userSkillsSQL = "INSERT INTO stayconnected.UserSkills(RID,skillID,proficiency) VALUES(?,?,?)";
			for (int i = 0; i < account.getAccount().getSkill().size(); i++) {
				jdbcTemplate.update(userSkillsSQL, account.getAccount().getRoyalID(),
						account.getAccount().getSkill().get(i).getSkillID(),
						account.getAccount().getSkill().get(i).getProficiency());
			}
			String userAuthoritySQL = "INSERT INTO stayconnected.Authority(RID,UserRoleID) VALUES(?,?)";
			ArrayList<String> usersRoles = account.getAccount().getRoles();
			for (int i = 0; i < usersRoles.size(); i++) {
				int userRoleId = 0;
				if (usersRoles.get(i).equalsIgnoreCase("ROLE_CURR")) {
					userRoleId = 1;
				} else if (usersRoles.get(i).equalsIgnoreCase("ROLE_ALUM")) {
					userRoleId = 2;
				} else if (usersRoles.get(i).equalsIgnoreCase("ROLE_FACULTY")) {
					userRoleId = 3;
				}
				jdbcTemplate.update(userAuthoritySQL, account.getAccount().getRoyalID(), userRoleId);
			}
			if (!account.getSpecialCode().equals("")) {
				String userActivationSQL = "INSERT INTO stayconnected.UserActivation(code,expiration,RID) values(?,?,?)";
				jdbcTemplate.update(userActivationSQL, account.getSpecialCode(), LocalDate.now(),
						account.getAccount().getRoyalID());
			}
			String userLoginSQL = "INSERT INTO stayconnected.UserLogin(RID,password) values(?,?)";
			jdbcTemplate.update(userLoginSQL, account.getAccount().getRoyalID(), account.getPassword());
			transactionManager.commit(status);
			return accountForm;
		} catch (DataAccessException e) {
			System.out.println("Error in creating product record, rolling back");
			transactionManager.rollback(status);
			return null;
		}

	}

	@Override
	public UserActivation getSpecialCodeByRoyalID(String royalID) {
		try {
			String SQL = "SELECT RID, code, expiration from stayconnected.UserActivation where RID = ?";
			UserActivation userActivation = jdbcTemplate.queryForObject(SQL, new Object[] { royalID },
					new UserActivationMapper());
			return userActivation;
		} catch (DataAccessException e) {
			System.out.println("Can't find special code");
		}
		return null;
	}

	@Override
	public ForgotPasswordForm getPasswordByRoyalID(String royalID) {
		
		try {
			
			String SQL = "SELECT q.RID,q.email,p.password from stayconnected.userlogin as p,stayconnected.useraccount as q where p.RID=q.RID and q.RID = ?";
			ForgotPasswordForm pass = jdbcTemplate.queryForObject(SQL, new Object[] { royalID },
					new UserPasswordMapper());
			return pass;
		} catch (DataAccessException e) {
			System.out.println("Can't find password"+e);
		}
		return null;
	}
	@Override
	public boolean activateAccountByRoyalID(String royalID) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			String SQL = "INSERT INTO stayconnected.AccountStatus(RID,status) values(?,?)";
			jdbcTemplate.update(SQL, royalID, true);
			transactionManager.commit(status);
			return true;
		} catch (DataAccessException e) {
			System.out.println("Can't activate Account");
			transactionManager.rollback(status);
			return false;
		}
	}

	@Override
	public UserAccount getAccountByRoyalID(String royalID) {
		UserAccount user = new UserAccount();
		try {
			String SQL = "SELECT * from stayconnected.useraccount WHERE rid= ?";

			user = jdbcTemplate.queryForObject(SQL, new Object[] { royalID }, new accountMapper());
			SQL = "select q.role as role from stayconnected.authority as p,stayconnected.userroles as q where p.rid=? and p.userroleid=q.uid";
			List<String> assignedRoles;
			assignedRoles = jdbcTemplate.query(SQL, new Object[] { royalID }, new roleMapper());
			for (int i = 0; i < assignedRoles.size(); i++) {
				user.addRoles(assignedRoles.get(i));
			}
			SQL = " select * from stayconnected.jobhistory where rid=?";
			List<JobHistory> JobHistories;
			JobHistories = jdbcTemplate.query(SQL, new Object[] { royalID }, new jobhistoryMapper());
			for (int i = 0; i < JobHistories.size(); i++) {
				user.addWorkHistory(JobHistories.get(i));
			}
			return user;
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public ArrayList<UserAccount> getAllAccounts() {
		ArrayList<UserAccount> users = new ArrayList<UserAccount>();
		String SQL = " select a.* from stayconnected.useraccount as a, stayconnected.authority as p,stayconnected.userroles as q where a.rid=p.rid  and  p.userroleid=q.uid and (p.userroleid='1' or p.userroleid='2') group by a.rid";
		List<UserAccount> userResults;
		userResults = jdbcTemplate.query(SQL, new accountMapper());

		SQL = "SELECT stayconnected.userskills.rid," + "stayconnected.skills.skillname,"
				+ "stayconnected.userskills.proficiency" + " FROM stayconnected.userskills," + "stayconnected.skills  "
				+ "WHERE userskills.skillid" + " = skills.skillid;";

		List<UserSkill> userskillResults;
		userskillResults = jdbcTemplate.query(SQL, new userskillsMapper());

		SQL = "SELECT * from stayconnected.jobhistory;";
		List<JobHistory> jobhistoryResults;
		jobhistoryResults = jdbcTemplate.query(SQL, new jobhistoryMapper());

		SQL = "SELECT stayconnected.authority.rid , " + "stayconnected.userroles.role "
				+ " FROM stayconnected.authority , stayconnected.userroles "
				+ "WHERE authority.userroleid = userroles.uid;";
		List<Authority> authorityResults;
		authorityResults = jdbcTemplate.query(SQL, new authorityMapper());

		for (int i = 0; i < userResults.size(); i++) {
			for (int skill = 0; skill < userskillResults.size(); skill++) {
				if (userResults.get(i).getRoyalID().equals(userskillResults.get(skill).getRid())) {
					Skill newSkill = new Skill(-1, userskillResults.get(skill).getProficiency(),
							userskillResults.get(skill).getSkillName());
					userResults.get(i).addSkills(newSkill);
				}
			}
			for (int history = 0; history < jobhistoryResults.size(); history++) {
				if (userResults.get(i).getRoyalID().equals(jobhistoryResults.get(history).getRid())) {
					userResults.get(i).addWorkHistory(jobhistoryResults.get(history));
				}
			}
			for (int authority = 0; authority < authorityResults.size(); authority++) {
				if (userResults.get(i).getRoyalID().equals(authorityResults.get(authority).getRid())) {
					userResults.get(i).addRole(authorityResults.get(authority));
				}
			}
		}

		for (int user = 0; user < userResults.size(); user++) {
			users.add(userResults.get(user));
		}

		return users;
	}

	@Override
	public UserAccount getFullUserProfileByRoyalID(String royalID) {
		UserAccount user = new UserAccount();
		user.setRoyalID("-1");
		try {
			String userSQL = "SELECT * from stayconnected.useraccount where rid = '" + royalID + "';";
			user = jdbcTemplate.queryForObject(userSQL, new accountMapper());
		} catch (DataAccessException e) {
			// No user exists for ID
			return user;
		}

		List<UserSkill> userskillResults = new ArrayList<>();
		try {
			String skillSQL = "SELECT stayconnected.userskills.rid," + "stayconnected.skills.skillname,"
					+ "stayconnected.userskills.proficiency" + " FROM stayconnected.userskills,"
					+ "stayconnected.skills  " + "WHERE userskills.skillid" + " = skills.skillid and userskills.rid = '"
					+ royalID + "';";
			userskillResults = jdbcTemplate.query(skillSQL, new userskillsMapper());
		} catch (DataAccessException e) {
			System.out.println("No Skills found");
		}

		List<JobHistory> jobhistoryResults = new ArrayList<>();
		try {
			String jobSQL = "SELECT * from stayconnected.jobhistory where rid = '" + royalID + "';";
			jobhistoryResults = jdbcTemplate.query(jobSQL, new jobhistoryMapper());
		} catch (DataAccessException e) {
			System.out.println("NO JOB HISTORY FOUND");
		}

		List<Authority> authorityResults = new ArrayList<>();
		try {
			String authoritySQL = "SELECT stayconnected.authority.rid , " + "stayconnected.userroles.role "
					+ " FROM stayconnected.authority , stayconnected.userroles "
					+ "WHERE authority.userroleid = userroles.uid and " + "stayconnected.authority.rid = '" + royalID
					+ "';";
			authorityResults = jdbcTemplate.query(authoritySQL, new authorityMapper());
		} catch (DataAccessException e) {
			System.out.println("NO Authorities Found");
		}

		for (int skill = 0; skill < userskillResults.size(); skill++) {
			if (user.getRoyalID().equals(userskillResults.get(skill).getRid())) {
				Skill newSkill = new Skill(-1, userskillResults.get(skill).getProficiency(),
						userskillResults.get(skill).getSkillName());
				user.addSkills(newSkill);
			}
		}

		for (JobHistory history : jobhistoryResults) {
			user.addWorkHistory(history);
		}

		for (Authority authority : authorityResults) {
			authority.setRole(authority.getRole().replaceAll("ROLE_", ""));
			user.addRole(authority);
		}

		return user;
	}

	@Override
	public int deleteAccount(String RID) {
		String SQL = "DELETE FROM stayconnected.useraccount WHERE useraccount.rid = ?";
		jdbcTemplate.update(SQL, RID);
		try {
			jdbcTemplate.execute(SQL);
		}

		catch (DataAccessException e) {

		}
		return 0;
	}


	@Override
	public ArrayList<String> getRoles() {
		String SQL = "SELECT * from stayconnected.UserRoles";
		List<String> roleresults;
		ArrayList<String> roles = new ArrayList<String>();
		roleresults = jdbcTemplate.query(SQL, new roleMapper());
		for (int i = 0; i < roleresults.size(); i++) {
			roles.add(roleresults.get(i));
		}
		return roles;
	}

	@Override
	public ArrayList<String> getRolesByID(String royalID) {
		String SQL = "select q.role as role from stayconnected.authority as p,stayconnected.userroles as q where p.rid=? and p.userroleid=q.uid";
		List<String> roleresults;
		ArrayList<String> roles = new ArrayList<String>();
		roleresults = jdbcTemplate.query(SQL, new Object[] { royalID }, new roleMapper());
		for (int i = 0; i < roleresults.size(); i++) {
			roles.add(roleresults.get(i));
		}
		return roles;
	}

	@Override
	public List<Skill> getSkills() {
		String SQL = "SELECT * from stayconnected.skills";
		List<Skill> skillresults;
		ArrayList<Skill> skills = new ArrayList<Skill>();
		skillresults = jdbcTemplate.query(SQL, new skillMapper());
		for (int i = 0; i < skillresults.size(); i++) {
			skills.add(skillresults.get(i));
		}

		return skills;
	}

	@Override
	public List<Skill> getSkillsByID(String royalID) {
		String SQL = "select p.skillname as skillname , p.skillid  from stayconnected.skills as p ,stayconnected.userskills as q where q.rid=? and p.skillid=q.skillid";
		List<Skill> skillresults;
		ArrayList<Skill> skills = new ArrayList<Skill>();
		skillresults = jdbcTemplate.query(SQL, new Object[] { royalID }, new skillMapper());
		for (int i = 0; i < skillresults.size(); i++) {
			skills.add(skillresults.get(i));
		}

		return skills;
	}

	@Override
	public List<JobHistory> getJobHistoryByID(String royalID) {
		String SQL = " select * from stayconnected.jobhistory where rid=?";
		List<JobHistory> JobHistoryresults;
		ArrayList<JobHistory> jobHistories = new ArrayList<JobHistory>();
		JobHistoryresults = jdbcTemplate.query(SQL, new Object[] { royalID }, new jobhistoryMapper());
		for (int i = 0; i < JobHistoryresults.size(); i++) {
			jobHistories.add(JobHistoryresults.get(i));
		}

		return jobHistories;
	}

	@Override
	public void update(UpdateAccountForm update, Principal user) {
		String SQL;
		if (update.getFirstName() != "") {
			SQL = "UPDATE stayconnected.useraccount SET fname = " + "'" + update.getFirstName() + "'" + " WHERE rid = "
					+ "'" + user.getName() + "'";
			jdbcTemplate.update(SQL);
		}
		if (update.getLastName() != "") {
			SQL = "UPDATE stayconnected.useraccount SET lname = " + "'" + update.getLastName() + "'" + " WHERE rid = "
					+ "'" + user.getName() + "'";
			jdbcTemplate.update(SQL);
		}
		if (update.getEmail() != "") {
			SQL = "UPDATE stayconnected.useraccount SET email = " + "'" + update.getEmail() + "'" + " WHERE rid = "
					+ "'" + user.getName() + "'";
			jdbcTemplate.update(SQL);
		}
		if (update.getPhoneNumber() != "") {
			SQL = "UPDATE stayconnected.useraccount SET phone_number = " + "'" + update.getPhoneNumber() + "'"
					+ " WHERE rid = " + "'" + user.getName() + "'";
			jdbcTemplate.update(SQL);
		}
		if (update.getAddress() != "") {
			SQL = "UPDATE stayconnected.useraccount SET address = " + "'" + update.getAddress() + "'" + " WHERE rid = "
					+ "'" + user.getName() + "'";
			jdbcTemplate.update(SQL);
		}
	}
	
	//Forgot Password
	@Override
	public void update(String id,ForgotPasswordForm webAccount) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String SQL;
		
	//	String rawPassword = accountForm.getPassword();
		//accountForm.setPassword(encodePassword(accountForm.getPassword()));
		
		String password=passwordEncoder.encode(webAccount.getPassword());
		if (webAccount.getPassword()!= "") {
			SQL = "UPDATE stayconnected.userlogin SET password = " + "'" + password + "'" + " WHERE rid = "
					+ "'" + id+ "'";
			System.out.println(SQL);
			jdbcTemplate.update(SQL);
		}
	}

	@Override
	public int updateUserAccount(UserAccount userAccount) {
		UserAccount matchedAccount = new UserAccount();
		matchedAccount = getAccountByRoyalID(userAccount.getRoyalID());
		if (!userAccount.getFirstName().equals(matchedAccount.getFirstName())) {
			SQL = "UPDATE stayconnected.useraccount SET fname= ? WHERE rid = ?";
			jdbcTemplate.update(SQL, userAccount.getFirstName(), userAccount.getRoyalID());
		}
		if (!userAccount.getLastName().equals(matchedAccount.getLastName())) {
			SQL = "UPDATE stayconnected.useraccount SET lname = ? WHERE rid = ?";
			jdbcTemplate.update(SQL, userAccount.getLastName(), userAccount.getRoyalID());
		}
		if (!userAccount.getEmail().equals(matchedAccount.getEmail())) {
			SQL = "delete from stayconnected.accountstatus where rid=?";
			jdbcTemplate.update(SQL, userAccount.getRoyalID());
			SQL = "delete from stayconnected.UserActivation where rid=?";
			jdbcTemplate.update(SQL, userAccount.getRoyalID());
			String userActivationSQL = "INSERT INTO stayconnected.UserActivation(code,expiration,RID) values(?,?,?)";
			jdbcTemplate.update(userActivationSQL, userAccount.getSpecialCode(), LocalDate.now(),
					userAccount.getRoyalID());
			SQL = "UPDATE stayconnected.useraccount SET email = ? WHERE rid = ?";
			jdbcTemplate.update(SQL, userAccount.getEmail(), userAccount.getRoyalID());
		}
		if (!userAccount.getPhoneNumber().equals(matchedAccount.getPhoneNumber())) {
			SQL = "UPDATE stayconnected.useraccount SET phone_number = ? WHERE rid = ?";
			jdbcTemplate.update(SQL, userAccount.getPhoneNumber(), userAccount.getRoyalID());
		}
		if (!userAccount.getUserAddress().equals(matchedAccount.getUserAddress())) {
			SQL = "UPDATE stayconnected.useraccount SET address = ? WHERE rid = ?";
			jdbcTemplate.update(SQL, userAccount.getUserAddress(), userAccount.getRoyalID());
		}
		Collection<JobHistory> list = getJobHistoryByID(userAccount.getRoyalID());
		userAccount.getWorkExperience().removeAll(list);
		SQL = "delete from stayconnected.jobhistory where rid=?";
		jdbcTemplate.update(SQL, userAccount.getRoyalID());
		/*
		 * if (!userAccount.getWorkExperience().isEmpty()){ ArrayList<JobHistory>
		 * usersWorkExp = userAccount.getWorkExperience(); for (int i = 0; i <
		 * usersWorkExp.size(); i++) { if (userAccount.getWorkExperience().get(i) !=
		 * null) { SQL =
		 * " update stayconnected.jobhistory set position=?,companyName=?,address=?,startdate=?,currentlyemplyed=?,enddate=? where rid=?"
		 * ; jdbcTemplate.update(SQL, usersWorkExp.get(i).getPosition(),
		 * usersWorkExp.get(i).getCompanyName(), usersWorkExp.get(i).getAddress(),
		 * usersWorkExp.get(i).getStartDate(),
		 * usersWorkExp.get(i).isCurrentlyEmployed(), usersWorkExp.get(i).getEndDate(),
		 * usersWorkExp.get(i).getRid()); } }
		 */
		if (!userAccount.getWorkExperience().isEmpty()) {
			ArrayList<JobHistory> usersWorkExp = userAccount.getWorkExperience();
			SQL = "insert into stayconnected.jobHistory(position,companyName,address,startDate,endDate,currentlyemplyed,RID) values(?,?,?,?,?,?,?)";
			for (int i = 0; i < usersWorkExp.size(); i++) {
				if (usersWorkExp.get(i) != null) {
					jdbcTemplate.update(SQL, usersWorkExp.get(i).getPosition(), usersWorkExp.get(i).getCompanyName(),
							usersWorkExp.get(i).getAddress(), usersWorkExp.get(i).getStartDate(),
							usersWorkExp.get(i).getEndDate(), usersWorkExp.get(i).isCurrentlyEmployed(),
							userAccount.getRoyalID());
				}
			}
		}
		if (userAccount.getSpecialCode() != null)

		{
			SQL = "delete from stayconnected.accountstatus where rid=?";
			jdbcTemplate.update(SQL, userAccount.getRoyalID());
			SQL = "delete from stayconnected.UserActivation where rid=?";
			jdbcTemplate.update(SQL, userAccount.getRoyalID());
			String userActivationSQL = "INSERT INTO stayconnected.UserActivation(code,expiration,RID) values(?,?,?)";
			jdbcTemplate.update(userActivationSQL, userAccount.getSpecialCode(), LocalDate.now(),
					userAccount.getRoyalID());
		}
		if (userAccount.getRoles() != null) {
			ArrayList<String> usersRoles = userAccount.getRoles();
			SQL = "delete  from stayconnected.authority where rid=?";
			jdbcTemplate.update(SQL, userAccount.getRoyalID());
			for (int i = 0; i < usersRoles.size(); i++) {
				int userRoleId = 0;
				if (usersRoles.get(i).equalsIgnoreCase("ROLE_CURR")) {
					userRoleId = 1;
				} else if (usersRoles.get(i).equalsIgnoreCase("ROLE_ALUM")) {
					userRoleId = 2;
				} else if (usersRoles.get(i).equalsIgnoreCase("ROLE_FACULTY")) {
					userRoleId = 3;
				}
				SQL = "INSERT INTO stayconnected.Authority(RID,UserRoleID) VALUES(?,?)";
				jdbcTemplate.update(SQL, userAccount.getRoyalID(), userRoleId);
			}
		}
		return 1;
	}

	@Override
	public Boolean isAccountActivated(String royalID) {
		try {
			String SQL = "select status from stayconnected.AccountStatus where RID = ?";
			Boolean result = jdbcTemplate.queryForObject(SQL, new Object[] { royalID }, new UserAccountStatusMapper());
			return result;
		} catch (DataAccessException e) {
			return false;
		}
	}
}