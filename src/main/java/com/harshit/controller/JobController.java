package com.harshit.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harshit.Dao.JobOpeningDao;
import com.harshit.Dao.UserAccountDao;
import com.harshit.model.InternshipJobOpening;
import com.harshit.model.Skill;
import com.harshit.model.SortJobOpening;

@Controller
public class JobController {

	@Autowired
	UserAccountDao dao;

	@Autowired
	private JobOpeningDao jobOpenningDao;

	@GetMapping("/browsejobopenings")
	public String browseJobOpenings(@Valid SortJobOpening sortJobOpening, Model model, Principal principal) {
		if (principal != null) {
			boolean isActivated = dao.isAccountActivated(principal.getName());
			if (!isActivated) {
				return "redirect:/activateAccount";
			}
		}
		List<InternshipJobOpening> jobs = jobOpenningDao.getAllJobOpenings();

		SortJobOpening sortedOpenings = populateDropDownOptions(jobs);

		model.addAttribute("jobopenings", jobs);
		model.addAttribute("companies", sortedOpenings.getCompanyName());
		model.addAttribute("positions", sortedOpenings.getPositions());
		model.addAttribute("proficiencies", sortedOpenings.getProficiency());
		model.addAttribute("locations", sortedOpenings.getlocations());
		model.addAttribute("workhours", sortedOpenings.getHoursPerWeek());
		return "jobManagement/browsejobopenings";
	}

	@PostMapping("/browsejobopenings")
	public String browseJobOpeningsResult(@Valid SortJobOpening sortJobOpening, final BindingResult bindingResult,
			Model model) {
		List<InternshipJobOpening> allJobs = jobOpenningDao.getAllJobOpenings();
		List<InternshipJobOpening> trimmedJobs = new ArrayList<InternshipJobOpening>();

		SortJobOpening sortedOpenings = populateDropDownOptions(allJobs);

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getFieldError());
			model.addAttribute("jobopenings", allJobs);
			model.addAttribute("companies", sortedOpenings.getCompanyName());
			model.addAttribute("positions", sortedOpenings.getPositions());
			model.addAttribute("proficiencies", sortedOpenings.getProficiency());
			model.addAttribute("locations", sortedOpenings.getlocations());
			model.addAttribute("workhours", sortedOpenings.getHoursPerWeek());
			return "jobManagement/browsejobopenings";
		}

		// Pruning List by Company Name
		if (!sortJobOpening.getJobOpening().getCompanyName().getCompanyName().equals("0")) {
			for (int i = 0; i < allJobs.size(); i++) {
				if (allJobs.get(i).getCompanyName().getCompanyName()
						.equals(sortJobOpening.getJobOpening().getCompanyName().getCompanyName())) {
					trimmedJobs.add(allJobs.get(i));
				}
			}
			allJobs.clear();
			allJobs.addAll(trimmedJobs);
		}

		// Pruning List by Position
		if (!sortJobOpening.getJobOpening().getPosition().equals("0")) {
			trimmedJobs.clear();
			for (int i = 0; i < allJobs.size(); i++) {
				if (allJobs.get(i).getPosition().equals(sortJobOpening.getJobOpening().getPosition())) {
					trimmedJobs.add(allJobs.get(i));
				}
			}
			allJobs.clear();
			allJobs.addAll(trimmedJobs);
		}

		// Pruning List by Proficiancy
		if (!sortJobOpening.getJobOpening().getProficiancy().equals("0")) {
			trimmedJobs.clear();
			for (int i = 0; i < allJobs.size(); i++) {
				if (allJobs.get(i).getProficiancy().equals(sortJobOpening.getJobOpening().getProficiancy())) {
					trimmedJobs.add(allJobs.get(i));
				}
			}
			allJobs.clear();
			allJobs.addAll(trimmedJobs);
		}

		// Pruning List by Location
		if (!sortJobOpening.getJobOpening().getLocation().equals("0")) {
			trimmedJobs.clear();
			for (int i = 0; i < allJobs.size(); i++) {
				if (allJobs.get(i).getLocation().equals(sortJobOpening.getJobOpening().getLocation())) {
					trimmedJobs.add(allJobs.get(i));
				}
			}
			allJobs.clear();
			allJobs.addAll(trimmedJobs);
		}

		// Pruning List by hoursPerWeek
		if (!sortJobOpening.getJobOpening().getHoursPerWeek().equals("0")) {
			trimmedJobs.clear();
			for (int i = 0; i < allJobs.size(); i++) {
				System.out.println("hours:" + allJobs.get(i).getHoursPerWeek());
				if (allJobs.get(i).getHoursPerWeek() != null) {
					if (allJobs.get(i).getHoursPerWeek().equals(sortJobOpening.getJobOpening().getHoursPerWeek())) {
						trimmedJobs.add(allJobs.get(i));
					}
				}
			}
			allJobs.clear();
			allJobs.addAll(trimmedJobs);
		}
		model.addAttribute("jobopenings", allJobs);
		model.addAttribute("companies", sortedOpenings.getCompanyName());
		model.addAttribute("positions", sortedOpenings.getPositions());
		model.addAttribute("proficiencies", sortedOpenings.getProficiency());
		model.addAttribute("locations", sortedOpenings.getlocations());
		model.addAttribute("workhours", sortedOpenings.getHoursPerWeek());
		return "jobManagement/browsejobopenings";
	}

	@GetMapping("/postJobOpenings")
	public String postJobOpeningForm(InternshipJobOpening jobOpening, Model model, Principal principal) {
		if (principal != null) {
			boolean isActivated = dao.isAccountActivated(principal.getName());
			if (!isActivated) {
				return "redirect:/activateAccount";
			}
		}
		model.addAttribute("postjob", jobOpening);
		model.addAttribute("jobIDError", false);
		model.addAttribute("skills", dao.getSkills());
		return "jobManagement/postjobopening";
	}

	@PostMapping("/postJobOpenings")
	public String postJobOpening(@RequestParam(value = "ski", required = false) int[] ski,
			@Valid InternshipJobOpening jobOpening, final BindingResult result, Model model, Principal principal) {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError e : errors) {
				if (e.getCode().equals("typeMismatch")) {
					model.addAttribute("idError", true);
				} else {
					model.addAttribute("idError", false);
				}
			}
			model.addAttribute("jobIDError", false);
			model.addAttribute("postjob", jobOpening);
			model.addAttribute("skills", dao.getSkills());
			return "jobManagement/postjobopening";
		}
		ArrayList<Skill> skills = (ArrayList<Skill>) dao.getSkills();
		if (ski != null) {
			Skill skill = null;
			for (int i = 0; i < ski.length; i++) {
				for (int j = 0; j < skills.size(); j++) {
					if (skills.get(j).getSkillID() == ski[i]) {
						skill = new Skill();
						skill.setSkillName(skills.get(j).getSkillName());
						skill.setSkillID(skills.get(j).getSkillID());
						jobOpening.addSkills(skill);
					}
				}
			}
		}
		InternshipJobOpening jobs = jobOpenningDao.postJobOpening(jobOpening);
		if (jobs == null) {
			model.addAttribute("postjob", jobOpening);
			model.addAttribute("jobIDError", true);
			model.addAttribute("skills", dao.getSkills());
			return "jobManagement/postjobopening";
		}
		return "redirect:/browsejobopenings";
	}

	private SortJobOpening populateDropDownOptions(List<InternshipJobOpening> jobs) {
		SortJobOpening sortedOpenings = new SortJobOpening();
		for (InternshipJobOpening internshipJobOpening : jobs) {
			if (!sortedOpenings.getCompanyName().contains(internshipJobOpening.getCompanyName().getCompanyName()))
				sortedOpenings.addCompanyName(internshipJobOpening.getCompanyName().getCompanyName());
			if (!sortedOpenings.getHoursPerWeek().contains(internshipJobOpening.getHoursPerWeek()))
				if (internshipJobOpening.getHoursPerWeek() != "" && internshipJobOpening.getHoursPerWeek() != null) {
					sortedOpenings.addHoursPerWeek(internshipJobOpening.getHoursPerWeek());
				}
			if (!sortedOpenings.getlocations().contains(internshipJobOpening.getLocation()))
				sortedOpenings.addLocation(internshipJobOpening.getLocation());
			if (!sortedOpenings.getPositions().contains(internshipJobOpening.getPosition()))
				sortedOpenings.addPosition(internshipJobOpening.getPosition());
			if (!sortedOpenings.getProficiency().contains(internshipJobOpening.getProficiancy()))
				sortedOpenings.addProficency(internshipJobOpening.getProficiancy());
		}
		return sortedOpenings;
	}
}