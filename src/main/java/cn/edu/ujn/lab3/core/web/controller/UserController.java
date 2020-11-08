package cn.edu.ujn.lab3.core.web.controller;


import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ujn.lab3.core.service.IBaseDictService;
import cn.edu.ujn.lab3.core.service.ICustomerService;
import cn.edu.ujn.lab3.core.service.UserService;
import cn.edu.ujn.lab3.dao.Customer;
import cn.edu.ujn.lab3.dao.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IBaseDictService baseDictService;
	//用户登录
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(String usercode, String password, Model model,HttpSession session) {
		
		//通过账号和密码查询用户
		
		User user =userService.findUser(usercode,password);
		//String name;
		//BaseDict bd=new BaseDict();
		System.out.println(user);
		if(user != null){		
			// 将用户对象添加到Session
			session.setAttribute("USER_SESSION", user);
			// 跳转到主页面
			return "redirect:/customer/list.action";
		}else {
			model.addAttribute("msg", "账号或密码错误，请重新输入！");
			
			 // 返回到登录页面
			return "login";
		}
		
       
	}
	
	@RequestMapping(value="/customer/getCustomerById.action",method=RequestMethod.GET)
	@ResponseBody
	public Customer editCustomer(HttpServletRequest request,HttpServletResponse response,Model model) {
		
		int id=Integer.valueOf(request.getParameter("id"));
//		System.out.println("*****************");
//		System.out.println("**************id="+id);
		Customer customer=customerService.getCustomerById(id);
		customer=baseDictService.customerIdTurnName(customer);
		//BaseDict bd=new BaseDict();
		//bd=baseDictService.selectNameById(customer.getCustSource());
		//String s=bd.getDictItemName();
		System.out.println(customer);
		//model.addAttribute("custSource", s);
		
		model.addAttribute("custSource",customer.getCustSource());
		model.addAttribute("custIndustry", customer.getCustIndustry());
		model.addAttribute("custLevel",customer.getCustLevel());
		//request.setAttribute("custSource", baseDictService.selectNameById(customer.getCustSource()).getDictItemName());
		System.out.println("*+++++************");
		//System.out.println(baseDictService.selectNameById(customer.getCustSource()).getDictItemName());
	    return customer;
	}
	
	@RequestMapping(value="/customer/update.action",method=RequestMethod.POST)
	@ResponseBody
	public String updateCustomer(Customer customer) {
		System.out.println(customer);
		customer=baseDictService.customerNameTurnId(customer);
		System.out.println(customer);
		int updateCustomer = customerService.updateCustomer(customer);
		System.out.println("-----------------"+updateCustomer);
		if(updateCustomer==1)
			return "OK";
		else
			return "False";
	}
	@RequestMapping(value="/customer/delete.action")
	@ResponseBody
	public String deleteCustomer(HttpServletRequest request,HttpServletResponse response,Model model) {
		int id=Integer.valueOf(request.getParameter("id"));
		int deleteCustomer = this.customerService.deleteCustomer(id);
		if(deleteCustomer==1)
			return "OK";
		else
			return "False";
	}
	@RequestMapping(value="/customer/list.action")
	//@ResponseBody
	public String findCustomerByCon(HttpServletRequest request,HttpServletResponse response,Model model) {
		
	    List<Customer> customerList=customerService.findAllCustomer();
		for(Customer ct:customerList) {
			ct=baseDictService.customerIdTurnName(ct);
		}
		
		List<String> collect1 = customerList.stream().map(Customer::getCustSource).distinct().collect(Collectors.toList());
		List<String> collect2 = customerList.stream().map(Customer::getCustIndustry).distinct().collect(Collectors.toList());
		List<String> collect3 = customerList.stream().map(Customer::getCustLevel).distinct().collect(Collectors.toList());
		//List<String> collect4 = customerList.stream().map(Customer::getCustSource).distinct().collect(Collectors.toList());
		Collections.sort(collect1);
		Collections.sort(collect2);
		Collections.sort(collect3);
		//System.out.println(collect1);
		model.addAttribute("fromType",collect1);
	    model.addAttribute("industryType",collect2);
	    model.addAttribute("levelType",collect3);
	    String name=request.getParameter("custName");
		String source=request.getParameter("custSource");
		String industy=request.getParameter("custIndustry");
		String level=request.getParameter("custLevel");
		//System.out.println("1111111111111111111111");
		//System.out.println(name);
		if(name!=null||source!=null||industy!=null||level!=null) {
	    Customer cus=new Customer();
	    cus.setCustName(name);
	    cus.setCustSource(source);
	    cus.setCustIndustry(industy);
	    cus.setCustLevel(level);
	    cus=baseDictService.customerNameTurnId(cus);
	   // System.out.println(cus);
	   // System.out.println("2222222222222");
	    List<Customer> cusList=customerService.findCustomerByCondition(cus);
	   // System.out.println(cusList);
	   // System.out.println("333333333333333333");
	    for(Customer ct:cusList) {
			ct=baseDictService.customerIdTurnName(ct);
		}
	   // System.out.println("4444444444444444444444");
	   // System.out.println(cusList);
	    model.addAttribute("customerList",cusList);}
		else {
			model.addAttribute("customerList",customerList);
		}
	    return "customer";
	}
	//public createCustomer
	@RequestMapping(value = "/customer/create.action",method=RequestMethod.POST)  
	@ResponseBody
    public String createCustomer(Customer customer){
       // int iRetVal = 0;
		customer=baseDictService.customerNameTurnId(customer);
		Date currentTime = new Date();
		customer.setCustCreatetime(currentTime);
        int insert=customerService.addCustomer(customer);
        //System.out.println(customer);
        //System.out.println(insert);
        if(insert==1)
        	return "OK";
        else
        	return "FALSE";
    }
	@RequestMapping(value = "/lg")
	public String lg( Model model) {
	    return "login";
	}
	
	/**
	 * 模拟其他类中跳转到客户管理页面的方法
	 */
	@RequestMapping(value = "/toCustomer.action")
	public String toCustomer( Model model) {
	    return "customer";
	}
	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/logout.action")
	public String logout(HttpSession session) {
	    // 清除Session
	    session.invalidate();
	    // 重定向到登录页面的跳转方法
	    return "redirect:login.action";
	}
	/**
	 * 向用户登陆页面跳转
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String toLogin() {
	    return "login";
	}
}
