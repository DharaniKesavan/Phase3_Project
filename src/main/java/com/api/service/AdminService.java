package com.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.bean.Admin;
import com.api.bean.Question;
import com.api.bean.Quiz;
import com.api.bean.Statistics;
import com.api.repository.AdminRepo;
import com.api.repository.Questionrepo;
import com.api.repository.Quizrepo;
import com.api.repository.Userrepo;

@Service
public class AdminService {
	
	@Autowired
	Questionrepo qr;
	@Autowired
	Quizrepo qur;
	@Autowired
	Userrepo ur;
	@Autowired
	Statistics stat;
	@Autowired
	AdminRepo adr;
	
	public String adminLogin(Admin u)
	{
		Admin ad= adr.findById(1).get();
		if(u.getUsername().equals(ad.getUsername())&&u.getPassword().equals(ad.getPassword())){
			return "Welcome Admin!!!";
		}
		else{
			return "Invalid Credentials!";
		}
	}
	
	public String adminupdate(Admin a)
	{
		Admin ad= adr.findById(1).get();
		ad.setUsername(a.getUsername());
		ad.setPassword(a.getPassword());
		adr.saveAndFlush(ad);
		return "Updated Successfully!!!";
	}
	
	public String addQuestion(Question q)
	{
		if(q!=null){
			qr.save(q);
			return "Question added Successfully!!!";
		}
		else{
			return "Failed to add the question!Please try again...";
		}		
	}
	
	public String addQuiz(Quiz q)
	{
		if(q!=null){
			qur.save(q);
			return "Quiz added Successfully!!!";
		}
		else{
			return "Failed to add the quiz!Please try again...";
		}
	}
	
	public List<Quiz> viewAllQuiz()
	{
		return qur.findAll();
	}
	
	public Statistics quizInfo()
	{
		stat.setUsers(ur.findAll().size());
		stat.setQuestions(qr.findAll().size());
		stat.setQuiz(qur.listOfQuiz());		
		return stat;
	}
	
}
