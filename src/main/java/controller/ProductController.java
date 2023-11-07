package controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpSession;


import model.service.ImgUpload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


import model.product.BeansDAO;
import model.product.BeansDO;
import model.member.SellerDAO;
import model.product.*;


/*
 * 	구매자 메서드
 * 	1) GET	|	/goProdcutList		->	mainLoginBuyer, mainLoginSeller랑 각각 설정? -> productList.jsp
 *  2) GET	|	/goProdcutListGroup ->	productListGroup.jsp
 *  2) POST	|	/goListDetail		->	각각의 상품 마다 고유 NUM이 있을텐데 ... -> productListDetail.jsp
 *  3) POST	|	/goListDetailGroup	->	그룹 상품도 마찬가지 -> productListDetailGroup.jsp
 *  4) POST	|	/payment			->	상세 페이지에서 결제 누르면 바로이동 -> payment.jsp
 *  5) POST	|	/paymentComplete	->	결제 완료시 -> paymentComplete.jsp
 *  6) POST	|	/mainLogin			->	결제 완료 페이지에서 버튼 누르면 메인으로 이동(멤버 컨트롤러에 메서드 정의되어있음) -> mainLoginBuyer
 *  7) POST	|	/cart				->	어디서 누르는지는 모르겟지만 카트로 이동 -> cart.jsp
 *  
 *  장바구니는 마이페이지로 이동하게 만들면 될듯
 *  
 *  판매자 메서드
 *  8) GET	|	/goRegisterProd		->	상품등록 페이지로 이동 -> registerProduct.jsp
 *  9) GET	|	/goRegisterProdGroup->	공동구매 상품등록 페이지로 이동 -> registerProductGroup.jsp
 *  10)POST	|	/registerProd		->	상품등록 후 -> mainLoginSeller.jsp
 *  11)POST	|	/registerProdGroup	->	공동구매 상품 등록 -> mainLoginSeller.jsp
 *  
 */


@Controller
public class ProductController {
	
	private BeansDO beans;
	private final BeansDAO beansDAO = new BeansDAO();
	private final SellerDAO sellerDAO = new SellerDAO();
	private final ImgUpload imgUpload = new ImgUpload();
	
	public ProductController() {
	}
	


	/*
	 * 
	 * 구매자 메서드
	 * 
	 */
	// 상품 목록 페이지 이동
	@GetMapping("goProductList")
	public String goProductList(Model model) {
		
		BeansDO beansDO = new BeansDO();
		
		beansDO = beansDAO.getBean(10);
		
		System.out.println(beansDO.getBeansNum());
		
		model.addAttribute("beansDO", beansDO);
		

		
		return "productList";
	}
	

	@GetMapping("payment")
	public String payment() {

		
		
		
		return "payment";
	}
 	
	@PostMapping("goListDetail")
	public String productDetail(@RequestParam String beansNum, Model model) {
		System.out.println(beansNum);
		
		int bNum = Integer.parseInt(beansNum);
		
		model.addAttribute("beansNum", bNum);
		
		return "productListDetail";
	}
	
	// 공동판매 상품 목록 페이지 이동
	@GetMapping("goProductListGroup")
	public String goProductListGroup() {
		
		return "productListGroup";
	}
	
	
	// 상품 목록 페이지
//		@GetMapping("/productList")
//		public String productList(Model model, 
//		            @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
//		            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize,
//		            @RequestParam(value = "category", required = false, defaultValue = "0") int categoryNum,
//		            @RequestParam(value = "sort", required = false, defaultValue = "recent") String sort,
//		            @RequestParam(value = "search", required = false) String search) {
//			// 상품 목록을 가져오는 기본 메서드
//	        ArrayList<BeansDO> beansList;
//	        
//	        // 카테고리 번호 0 일 경우 모든 상품 불러오기, 카테고리 선택 시 해당 카테고리에 맞는 상품 정보 제공
//	        if(categoryNum == 0) {
//	        	beansList = beansDAO.getAllBeans(search);
//	        }
//	        else {
//	        	beansList = beansDAO.sortedPage(sort, search, categoryNum); 	
//	        }
//	       
//	              
//	        // 페이징 처리를 위한 전체 상품 수 계산
//	        int totalRows = beansList.size();
//	        int totalPages = (int) Math.ceil((double) totalRows / pageSize);
//
//	        // 페이지 범위를 조정
//	        if (page < 1) {
//	            page = 1;
//	        } else if (page > totalPages) {
//	            page = totalPages;
//	        }
//
//	        // 페이징 처리를 위해 부분 리스트 선택
//	        int startRow = 1 + (page - 1) * pageSize;
//	        int endRow = pageSize * page;
//	        ArrayList<BeansDO> pagedBeansList = new ArrayList<>(beansList.subList(
//	        	    Math.max(startRow - 1, 0), Math.min(endRow, totalRows))); 
//
//	        // 모델에 데이터 추가
//	        model.addAttribute("categoryNum", categoryNum);
//	        model.addAttribute("sortOption", sort);
//	        model.addAttribute("beansList", pagedBeansList);
//	        model.addAttribute("totalPages", totalPages);
//	        model.addAttribute("currentPage", page);
//	        model.addAttribute("search", search);
//
//	        return "productList";
//		}

		// 상품 목록 페이지
		@GetMapping("/beansList")
		public String beansList(Model model, 
		            @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
		            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize,
		            @RequestParam(value = "category", required = false, defaultValue = "0") int categoryNum,
		            @RequestParam(value = "sort", required = false, defaultValue = "recent") String sort,
		            @RequestParam(value = "search", required = false) String search) {
		// 상품 목록을 가져오는 기본 메서드
        ArrayList<BeansDO> beansList;
        
        // 카테고리 번호 0 일 경우 모든 상품 불러오기, 카테고리 선택 시 해당 카테고리에 맞는 상품 정보 제공
        if(categoryNum == 0) {
        	beansList = beansDAO.getAllBeans(search);
        }
        else {
        	beansList = beansDAO.sortedPage(sort, search, categoryNum);     	
        }
                     
        // 페이징 처리를 위한 전체 상품 수 계산
        int totalRows = beansList.size();
        int totalPages = (int) Math.ceil((double) totalRows / pageSize);

        // 페이지 범위를 조정
        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        // 페이징 처리를 위해 부분 리스트 선택
        int startRow = 1 + (page - 1) * pageSize;
        int endRow = pageSize * page;
        ArrayList<BeansDO> pagedBeansList = new ArrayList<>(beansList.subList(
        	    Math.max(startRow - 1, 0), Math.min(endRow, totalRows))); 

        // 모델에 데이터 추가
        model.addAttribute("categoryNum", categoryNum);
        model.addAttribute("sortOption", sort);
        model.addAttribute("beansList", pagedBeansList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("search", search);

        return "productList";
	}
	
	// 공동 구매 상품 목록 페이지
	@GetMapping("/groupBeansList")
	public String groupBeansList(Model model, 
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
	            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize,
	            @RequestParam(value = "category", required = false, defaultValue = "0") int categoryNum,
	            @RequestParam(value = "sort", required = false, defaultValue = "recent") String sort, 
	            @RequestParam(value = "search", required = false) String search) {
	    
	    ArrayList<BeansDO> groupBeansList;
	    
	    if(categoryNum == 0) {
	    	groupBeansList = beansDAO.getAllGroupBeans(search);
        }
        else {
        	groupBeansList = beansDAO.sortedPage2(sort, search, categoryNum);     	
        }
	    
	    int totalRows = groupBeansList.size();
	    int totalPages = (int) Math.ceil((double) totalRows / pageSize);

	    if (page < 1) {
	        page = 1;
	    } else if (page > totalPages) {
	        page = totalPages;
	    }

	    int startRow = 1 + (page - 1) * pageSize;
	    int endRow = pageSize * page;
	    ArrayList<BeansDO> pagedGroupBeansList = new ArrayList<>(groupBeansList.subList(
	            Math.max(startRow - 1, 0), Math.min(endRow, totalRows))); 

	    model.addAttribute("sortOption", sort);
	    model.addAttribute("groupBeansList", pagedGroupBeansList);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("search", search);

	    return "groupBeansList";
	}
	
//	@GetMapping("/navigatePage")
//	public String navigatePage(@RequestParam("currentPage") int currentPage, 
//	                            @RequestParam("totalPages") int totalPages,
//	                            @RequestParam("direction") String direction, 
//	                            @RequestParam("categoryNum") int categoryNum, 
//	                            @RequestParam("search") String search,
//	                            @RequestParam("sort") String sort) {
//	    
//	    String url = "?search=" + search + "&sort=" + sort + "&category=" + categoryNum;	   
//	    if (!"recent".equals(sort)) {
//	        return "?page=" + currentPage + url;
//	    }
//	    if ("previous".equals(direction) && currentPage > 1) {
//	        return "?page=" + (currentPage - 1) + url;
//	    } else if ("next".equals(direction) && currentPage < totalPages) {
//	        return "?page=" + (currentPage + 1) + url;
//	    }
//	    return "?page=" + currentPage + "&search=" + search + "&sort=" + sort + "&category=" + categoryNum;
//	}

	
	// 이전, 다음 페이지 처리
	@GetMapping("/navigatePage")
	public String navigatePage(@RequestParam("currentPage") int currentPage, 
	                            @RequestParam("totalPages") int totalPages,
	                            @RequestParam("direction") String direction, 
	                            @RequestParam("category") int categoryNum, 
	                            @RequestParam("search") String search,
	                            @RequestParam("sort") String sort) {
		
		
		String category = "&category=" + categoryNum;
		
		if (!"recent".equals(sort)) {
	        return "?page=" + currentPage + 
	        		"&search=" + search + 
	        		"&sort=" + sort + category;
	    }
		
	    if ("previous".equals(direction) && currentPage > 1) {
	        return "?page=" + (currentPage - 1) + 
	        		"&search=" + search + 
	        		"&sort=" + sort + category;
	    } else if ("next".equals(direction) && currentPage < totalPages) {
	        return "?page=" + (currentPage + 1) + 
	        		"&search=" + search + 
	        		"&sort=" + sort + category;
	    }
	    return "?page=" + currentPage + "&search=" + search + category;
	}
	
	// 상품 상세 페이지로 이동
		@GetMapping("/productListDetail")
		public String productListDetail(@RequestParam("beansNum") int beansNum, Model model) {
			beans = beansDAO.getBean(beansNum);
			
			if(beans != null) {
				model.addAttribute("productListDetail", beansDAO.getBean(beansNum));
				return "productListDetail";
			}
			else {
				return "redirect:/productListDetail";
			}
		}
	
	// 공동 상품 상세 페이지로 이동
	@GetMapping("/groupBeanDetail")
	public String groupBeanDetail(@RequestParam("beansNum") int beansNum, Model model) {
		beans = beansDAO.getBean(beansNum);
		
		if(beans != null) {
			model.addAttribute("beans", beans);
			return "groupBeanDetail";
		}
		else {
			return "redirect:/groupBeansList";
		}
	}


	/*
	 * 
	 * 판매자 메서드
	 * 
	 */
	// 판맴 등록 페이지로 이동 ( mainLoginSeller.jsp -> registerProduct.jsp )
	@GetMapping("/goResisterProd")
	public String goResisterProduct(@RequestParam("action") String command) {

//	//상품 등록 후 상품 목록 페이지로 이동
//	@PostMapping("/insertBeans")
//	public String insertBeans(@ModelAttribute BeansDO command, Model model) {
//		String viewName = "";
//		try {
//			beansDAO.insertBean(command);
//			viewName = "redirect:/beansList";
//		}
//		catch(Exception e) {
//			model.addAttribute("msg", e.getMessage());
//			model.addAttribute("beansList", beansDAO.getAllBeans());
//			
//			viewName = "memberList";
//		}
//		return viewName;
//	}
//	//공동 상품 등록 후 상품 목록 페이지로 이동
//		
//	@PostMapping("/insertGroupBeans")
//	public String insertGroupBeans(@ModelAttribute BeansDO command, Model model) {
//		String viewName = "";
//		
//		try {
//			beansDAO.insertGroupBean(command);
//			viewName = "redirect:/beansList";
//		}
//		catch(Exception e) {
//			model.addAttribute("msg", e.getMessage());
//			model.addAttribute("beansList", beansDAO.getAllBeans());
//			
//			viewName = "memberList";
//		}
//		return viewName;
//	}
//	/*
//	//판매자 마이페이지 이동 - 판매중 게시물 이동 미완성
//	@PostMapping("/sellerMyPage/sellingBean")
//	public String SellingBean(@ModelAttribute SellerDO command, Model model) {
//		
//	}
//	*/
//	// 상품 정보 수정 하기, 수정한 후 마이페이지로 돌아오기 - 미완성
//	@PostMapping("/modifyBeans")
//	public String modifyBeans(@RequestParam("beansNum") int beansNum, Model model) {
//		String viewName;
//		try {
//			model.addAttribute("beans", beansDAO.getBeansDO(beansNum));
//			viewName = "redirect:/판매자 마이페이지";
//		}
//		catch(Exception e) {
//			model.addAttribute("msg", e.getMessage());
//			model.addAttribute("판매자 마이페이지", beansDAO.getAllBeans());
//			
//			viewName = "memberList";
//		}
//		
//		return viewName;
//	}
	

	
	@GetMapping("/productListDetail")
	public String productListDetail(@RequestParam("beansNum") int beansNum, Model model) {
		beans = beansDAO.getBean(beansNum);
		
		if(beans != null) {
			model.addAttribute("productListDetail", beansDAO.getBean(beansNum));
			return "productListDetail";
		}
		else {
			return "redirect:/productListDetail";
		}
	}

	
	
	//상품등록


//	// 상품 목록 페이지 테스트
//	@GetMapping("/beansList")
//	public String beansList(Model model, 
//	            @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
//	            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize,
//	            @RequestParam(value = "category", required = false, defaultValue = "0") int categoryNum,
//	            @RequestParam(value = "sortCol", required = false, defaultValue = "beans_num") String sort,
//	            @RequestParam(value = "desc", required = false) boolean desc,
//	            @RequestParam(value = "search", required = false) String search) {
//		// 상품 목록을 가져오는 기본 메서드
//        ArrayList<BeansDO> beansList;
//        
//        // 카테고리 번호 0 일 경우 모든 상품 불러오기, 카테고리 선택 시 해당 카테고리에 맞는 상품 정보 제공
//        beansList = beansDAO.getBeansList(categoryNum, search, sort, desc);
//                     
//        // 페이징 처리를 위한 전체 상품 수 계산
//        int totalRows = beansList.size();
//        int totalPages = (int) Math.ceil((double) totalRows / pageSize);
//
//        // 페이지 범위를 조정
//        if (page < 1) {
//            page = 1;
//        } else if (page > totalPages) {
//            page = totalPages;
//        }
//
//        // 페이징 처리를 위해 부분 리스트 선택
//        int startRow = 1 + (page - 1) * pageSize;
//        int endRow = pageSize * page;
//        ArrayList<BeansDO> pagedBeansList = new ArrayList<>(beansList.subList(
//        	    Math.max(startRow - 1, 0), Math.min(endRow, totalRows))); 
//
//        // 모델에 데이터 추가
//        model.addAttribute("categoryNum", categoryNum);
//        model.addAttribute("sortOption", sort);
//        model.addAttribute("beansList", pagedBeansList);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("search", search);
//
//        return "beansList";
//	}

	@GetMapping("/registerProductPage")
	public String resisterProduct(HttpSession session, Model model) {

		if(command.equals("normal")) {
			return "registerProduct";
		} 
		else if(command.equals("group")) {
			return "registerProductGroup";
		} 
		else {
			return "error";
		}
	}
	
	// 상품 등록
	@PostMapping("/registerProd")
	public String resisterProduct(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		int categoryNum = 1;
		String categoryName = beansDAO.getCategoryName(categoryNum);
       // String directory = "C:\\\\Users\\Jun\\Desktop\\finalproject\\coffee-please\\src\\main\\webapp\\uploadTest";
		String directory = "C:/Users/jsseo/git/coffee-please/src/main/webapp/registerData/sellerData/beans/" + categoryName;
        int sizeLimit = 1024 * 1024 * 5;
        MultipartRequest multi = new MultipartRequest(request, directory, sizeLimit,
                "UTF-8", new DefaultFileRenamePolicy());
        
    	
    	String action = multi.getParameter("action");
        if (action != null && action.equals("register")) {

            // 파일 업로드 액션
            System.out.println(directory);
            // 디렉토리 생성w
            File uploadDir = new File(directory);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String savedName = "";
            String savedName1 = "";
            @SuppressWarnings("unchecked")
            Enumeration<String> fileNames = multi.getFileNames();
            if (fileNames.hasMoreElements()) {
                String paramName = fileNames.nextElement();
                savedName = multi.getFilesystemName(paramName);
            }
            if (fileNames.hasMoreElements()) {
            	String paramName = fileNames.nextElement();
            	savedName1 = multi.getFilesystemName(paramName);
            }
            // 파일 정보를 photo 변수에 저장
            String beanImg = "\\finalProject\\uploadTest" + savedName; // 웹 경로로 수정
            String descript = "\\finalProject\\uploadTest" + savedName1; // 웹 경로로 수정
            // 세션이메일을 받아서 판매자 이메일로 저장
            String sellerEmail = String.valueOf(session.getAttribute("sellerEmail"));

            String beanName = multi.getParameter("beanName");
            int beanPrice = Integer.parseInt(multi.getParameter("beanPrice"));
            int categoryNum = Integer.parseInt(multi.getParameter("categoryNum"));;

            int deliveryCharge = Integer.parseInt(multi.getParameter("deliveryCharge"));
            
            String beanImg = "/coffee/registerData/sellerData/beans/" + categoryName + "/" + savedName; // 웹 경로로 수정
            String descript = "/coffee/registerData/sellerData/beans/" + categoryName + "/" + savedName1; // 웹 경로로 수정
            // 게시물 생성
            BeansDO newBeans = new BeansDO();
            newBeans.setBeanImg(beanImg);
            newBeans.setDescript(descript);
            newBeans.setSellerEmail(sellerEmail);
            newBeans.setBeanName(beanName);
            newBeans.setBeanPrice(beanPrice);
            newBeans.setCategoryNum(categoryNum);
            newBeans.setDeliveryCharge(deliveryCharge);
            
            // id 설정 - 필요한 경우 수정
//            newBeans.setId((String)request.getSession().getAttribute("userId"));
            // 데이터베이스에 새 게시물 추가
            beansDAO.insertBeans(newBeans);
           
//            request.setAttribute("uploadedPhoto", photo);
        }
        return "redirect:/mainLoginSeller";
    }
	
	// 공동구매 판매등록 페이지로 이동
	@GetMapping("/goRegisterProdGroup")
	public String goRegisterProdGroup() {
		return "registerProductGroup";
	}
	
	// 임시 공동구매 판매 등록
	@PostMapping("/registerProdGroup")
	public String resisterProductGroup(HttpSession session, Model model) {
		
		String sessionSeller = String.valueOf(session.getAttribute("sellerEmail"));
		model.addAttribute("seller", sellerDAO.getSeller(sessionSeller));
		
		return "redirect:/mainLogin";
	}
	
	
}

