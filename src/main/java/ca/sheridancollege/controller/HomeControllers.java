package ca.sheridancollege.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Student;
import ca.sheridancollege.database.DatabaseAccess;


@Controller
public class HomeControllers {

	@Autowired
	@Lazy
	private DatabaseAccess da;

	@GetMapping("/")
	public String goHome() {
		return "index.html";		
	}


	@GetMapping("/professor")
	public String goProfessorHome() {
		return "/professor/index.html";		
	}

	@GetMapping("/student")
	public String goStudentHome() {
		return "/student/index.html";		
	}

	@GetMapping("/login")
	public String goLoginPage() {
		return "login.html";		
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied.html";		
	}

	@GetMapping("/add")
	public String doAddStudent(Model model, @ModelAttribute Student s) {
		da.addStudent(s);	
		model.addAttribute("s", new Student());
		return "add.html";
	}

	@GetMapping("/view")
	public String goViewStudentInfo(Model model) {

		double	classAvg = 0;
		for(int i=0; i < da.getStudent().size(); i++) {
			classAvg +=Math.round( da.getStudent().get(i).getWA()/da.getStudent().size());
		}
		System.out.print(classAvg);

		model.addAttribute("classAvg", classAvg);



		double	classExsAvg = 0;
		for(int i=0; i < da.getStudent().size(); i++) {
			classExsAvg += Math.round( da.getStudent().get(i).getExs()/da.getStudent().size());
		}
		System.out.print(classExsAvg);

		model.addAttribute("classExsAvg", classExsAvg);


		double	classAssign1Avg = 0;
		for(int i=0; i < da.getStudent().size(); i++) {
			classAssign1Avg += Math.round(da.getStudent().get(i).getAssign1()/da.getStudent().size());
		}
		System.out.print(classAssign1Avg);

		model.addAttribute("classAssign1ExsAvg", classAssign1Avg);


		double	classAssign2Avg = 0;
		for(int i=0; i < da.getStudent().size(); i++) {
			classAssign2Avg += Math.round(da.getStudent().get(i).getAssign2()/da.getStudent().size());
		}
		System.out.print(classAssign2Avg);

		model.addAttribute("classAssign2ExsAvg", classAssign2Avg);


		double	classAssign3Avg = 0;
		for(int i=0; i < da.getStudent().size(); i++) {
			classAssign3Avg += Math.round(da.getStudent().get(i).getAssign3()/da.getStudent().size());
		}
		System.out.print(classAssign3Avg);

		model.addAttribute("classAssign3ExsAvg", classAssign3Avg);


		double	classMidtermAvg = 0;
		for(int i=0; i < da.getStudent().size(); i++) {
			classMidtermAvg += Math.round(da.getStudent().get(i).getMidterm()/da.getStudent().size());
		}
		System.out.print(classMidtermAvg);

		model.addAttribute("classMidtermExsAvg", classMidtermAvg);


		double	classFinAvg = 0;
		for(int i=0; i < da.getStudent().size(); i++) {
			classFinAvg += Math.round(da.getStudent().get(i).getFinalEx()/da.getStudent().size());
		}
		System.out.print(classFinAvg);

		model.addAttribute("classFinAvg", classFinAvg);

		model.addAttribute("s", da.getStudent());
		return "view.html";
	}

	//		@GetMapping("/view")
	//		public String goViewStudentInfo(Model model) {
	//			model.addAttribute("s", da.getStudent());
	//			return "view.html";
	//		}

	@GetMapping("/veiwS")
	public String goViewStudentInfo(Model model,  Authentication authentication) {
		String name = authentication.getName();
		System.out.println(name);		
		ArrayList<Student> st = new ArrayList<Student>();

		for (int i=0; i< da.getStudent().size(); i++) {


			if (da.getStudent().get(i).getName().equals(name)) {
				st.add(da.getStudent().get(i));
				model.addAttribute("s", st.get(0));
			}

		}
		return "veiwS.html";
	}


	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable int id, Model model) {
		da.deleteStudent(id);
		return "redirect:/view";
	} 

	@GetMapping("/edit/{id}")
	public String editStudent(@PathVariable int id, Model model) {
		Student st = da.getStudentById(id);
		model.addAttribute("st", st);
		return "modify.html";
	}

	@GetMapping("/modify")
	public String modifyStudent(Model model, @ModelAttribute Student st) {
		da.editStudent(st);
		return "redirect:/view";
	}


	@GetMapping("/register")
	public String goRegistration() {
		return "registration.html";		
	}

	@GetMapping("/registration")
	public String goReg() {
		return "registration.html";		
	}


	@PostMapping("/register")
	public String processRegistration(@RequestParam String name, 	
			@RequestParam String password, 			
			@RequestParam(required=false, defaultValue = "false") boolean student) {

		da.createNewUser(name, password);
		long userId = da.findUserAccount(name).getUserId();

		if(student==true) {
			da.addRole(userId, 2);//Role student
		}


		System.out.println(student);


		return "redirect:/";		
	}



	//	@PostMapping("/register")
	//	public String processRegistration(@RequestParam String name, @RequestParam String password) {
	//		da.createNewUser(name, password);
	//		long userId = da.findUserAccount(name).getUserId();
	//		da.addRole(userId, 1);
	//		da.addRole(userId,2);
	//	return "redirect:/";		
	//	}
	//	
}
