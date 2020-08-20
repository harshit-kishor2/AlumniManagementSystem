package com.harshit.Dao;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.harshit.model.ForgotPasswordForm;
import com.harshit.model.JobHistory;
import com.harshit.model.RegistrationForm;
import com.harshit.model.Skill;
import com.harshit.model.UpdateAccountForm;
import com.harshit.model.UserAccount;
import com.harshit.model.UserActivation;

@Repository
public interface UserAccountDao {

	public UserAccount createNewAccount(RegistrationForm account);

	public UserAccount getAccountByRoyalID(String royalID);

	public ArrayList<UserAccount> getAllAccounts();

	public UserAccount getFullUserProfileByRoyalID(String royalID);

	public int deleteAccount(String royalID);


	public List<String> getRoles();

	public List<Skill> getSkills();

	public UserActivation getSpecialCodeByRoyalID(String royalID);

	public boolean activateAccountByRoyalID(String royalID);

	public void update(UpdateAccountForm update, Principal user);

	public Boolean isAccountActivated(String royalID);

	public ArrayList<String> getRolesByID(String royalID);

	public List<Skill> getSkillsByID(String royalID);

	public List<JobHistory> getJobHistoryByID(String royalID);

	public int updateUserAccount(UserAccount userAccount);

	public ForgotPasswordForm getPasswordByRoyalID(String royalID);

	public void update(String id, ForgotPasswordForm webAccount);
}