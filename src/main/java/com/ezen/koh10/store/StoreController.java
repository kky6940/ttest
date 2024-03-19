package com.ezen.koh10.store;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class StoreController {
	@Autowired
	SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	@RequestMapping(value = "/storeinput")
	public String storeinput() {
		
		return "storeinput";
	}
	
	@RequestMapping(value = "/storesave")
	public String storesave(HttpServletRequest request) {
		String place = request.getParameter("place");
		String carname = request.getParameter("carname");
		int su = Integer.parseInt(request.getParameter("su"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		Service ss = sqlSession.getMapper(Service.class);
		ss.insert(place, carname, su, price);
		return "redirect:/main";
	}
	
	
	//기본출력
	@RequestMapping(value = "/storepage")
	public String storepage(Model mo) {
		Service ss = sqlSession.getMapper(Service.class);
		ArrayList<StoreDTO>list= ss.sort();
		mo.addAttribute("list", list);
		return "storepage";
	}
	//지점별매출액
		@RequestMapping(value = "/storepage1")
		public String storepage1(Model mo) {
			Service ss = sqlSession.getMapper(Service.class);
			ArrayList<StoreDTO>list= ss.sort1();
			mo.addAttribute("list", list);
			return "storepage1";
		}
	//지점별매출액
				@RequestMapping(value = "/storepage2")
				public String storepage2(Model mo) {
					Service ss = sqlSession.getMapper(Service.class);
					ArrayList<StoreDTO>list= ss.sort2();
					mo.addAttribute("list", list);
					return "storepage2";
				}
	//판매금액이 최다인 사원번호
	@RequestMapping(value = "/storepage3")
	public String storepage3(Model mo) {
			Service ss = sqlSession.getMapper(Service.class);
			ArrayList<StoreDTO>list= ss.sort3();
			mo.addAttribute("list", list);
			return "storepage3";
				}
	
	@RequestMapping(value = "/storepage4")
	public String storepage4(Model mo) {
			Service ss = sqlSession.getMapper(Service.class);
			ArrayList<StoreDTO>list= ss.sort4();
			mo.addAttribute("list", list);
			return "storepage4";
				}
	
	/*기본자료 페이징처리 2단계  */
	@RequestMapping(value = "/storepagepp")
	public String storepage(HttpServletRequest request, PageDTO dto, Model mo) {
	   String nowPage=request.getParameter("nowPage");
       String cntPerPage=request.getParameter("cntPerPage");
       Service ss = sqlSession.getMapper(Service.class);
      
       int total=ss.total();//전체 레코드 갯수
       if(nowPage==null && cntPerPage == null) { 
          nowPage="1";
          cntPerPage="5";
       }
       else if(nowPage==null) {
          nowPage="1";
       }
       else if(cntPerPage==null) {
          cntPerPage="5";
       }      
       dto=new PageDTO(total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
       mo.addAttribute("paging",dto);
       mo.addAttribute("list",ss.page(dto));
		
       return "storepagepp";
	}	
	/*판매액 내림차순 페이징처리 2단계  */
	@RequestMapping(value = "/storepage4pp")
	public String storepage1(HttpServletRequest request, PageDTO dto, Model mo) {
	   String nowPage=request.getParameter("nowPage");
       String cntPerPage=request.getParameter("cntPerPage");
       Service ss = sqlSession.getMapper(Service.class);
      
       int total=ss.total4();//전체 레코드 갯수
       if(nowPage==null && cntPerPage == null) { 
          nowPage="1";
          cntPerPage="5";
       }
       else if(nowPage==null) {
          nowPage="1";
       }
       else if(cntPerPage==null) {
          cntPerPage="5";
       }      
       dto=new PageDTO(total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
       mo.addAttribute("paging",dto);
       mo.addAttribute("list",ss.page4(dto));
		
       return "storepage4pp";
	}	//
	//검사
	@RequestMapping(value = "/input")
	public String valko1() {		
		return "input";
	}
	@RequestMapping(value = "/inputsave")
	public String valko2(@ModelAttribute  VinfoDTO vinfoDTO,BindingResult br,HttpServletResponse response ) throws IOException {		
		 PrintWriter pwr = response.getWriter();
	      String viewPage="out";
	      VinfoDTOValidator vdto = new VinfoDTOValidator();
	      vdto.validate(vinfoDTO, br);
	      if(br.hasErrors()) 
	      {
	         System.out.println(br.getFieldErrorCount());
	         viewPage="input";
	      }
	      if(br.getFieldErrorCount()==3)
	      {
	         response.setContentType("text/html;charset=utf-8");
	         pwr.print("<script> alert('이름,나이 공백!');</script>");
	         pwr.print("<script> window.location.href='input'</script>");
	         viewPage="input";
	         pwr.close();	         
	      }
	      if(br.getFieldErrorCount()==2)
	      {
	         response.setContentType("text/html;charset=utf-8");
	         pwr.print("<script> alert('나이 공백 이거나 너무 큰숫자!!!');</script>");
	         pwr.print("<script> window.location.href='input'</script>");
	         viewPage="input";
	         pwr.close();	         
	      }
	      if(br.getFieldErrorCount()==1)
	      {
	         response.setContentType("text/html;charset=utf-8");
	         pwr.print("<script> alert('이름 공백!');</script>");
	         pwr.print("<script> window.location.href='input'</script>");
	         viewPage="input";
	         pwr.close();	         
	      }
		return viewPage;
	}	
}
