package com.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.bean.Result;
import com.api.bean.Test;
import com.api.bean.User;
import com.api.repository.Questionrepo;
import com.api.repository.Quizrepo;
import com.api.repository.Testrepo;
import com.api.repository.Userrepo;

@Service
public class UserService {
		
	List<Result> finalList=new ArrayList<>();
	@Autowired
	Questionrepo qu;
	@Autowired
	Userrepo ur;
	@Autowired
	Quizrepo qr;
	@Autowired
	Testrepo tr;
	@Autowired
	User u;
	@Autowired
	Test t;

	Result r= new Result();
	
	public String userLogin(String email,String password)
	{
		u=ur.findByEmailid(email);
		if(u!=null){
				
			if(u.getEmailid().equals(email)&&u.getPassword().equals(password)){
				return "Logged in sucessfully!!!";
			}
			else{
				return "Invalid Credentials!";
			}
			
		}
		else{
			return "User not found!";
		}
	}
		
	public String userRegister(User u)
	{
		if(ur.findByEmailid(u.getEmailid())==null){
			ur.save(u);
			return "Registered Succesfully!!!";
		}
		else{
			return "User already exists!Go to Login Page...";
		}
	}
	
	public List<Object> viewAllQuiz()
	{
		return qr.listOfQuiz();
	}
	
	public String takeTest(Test t)
	{
		if(t!=null){
			tr.save(t);
			return "Test Submitted Succesfully!!!";
		}		
		else{
			return "Submission failed!Please try again... ";
		}			
	}
	
	public List<Test> getTestList()
	{
		return tr.findAll();
	}
	
	public List<Result> result()
	{	
		String email="";
		int mark=0;
		List<Test> obj=tr.findAll();
		List<User> u= ur.findAll();
		for (User user : u) {
			mark=0;
			email=user.getEmailid();
			System.out.println(user.getEmailid());
			
			for(Test ob :obj)
			{
				if(user.getUid()==ob.getUserid().getUid())
				{
					
					if(ob.getTestans()==ob.getQuestionid().getAns())
					{
						mark++;
					}
					System.out.println("inside"+mark);					
				}
			}
			
			System.out.println("outside"+mark);			
			finalList.add(new Result(email,mark));		
		}
		
		System.out.println("Final mark :"+mark);		
		Collections.sort(finalList);	
		return finalList;	
	}
}