<%@ page contentType="text/html; charset=UTF-8" 
	import="java.util.*"
    	  %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Seo -->
    <title>Bean2B</title>
	<%@ include file = "/WEB-INF/views/header.jsp" %>
	<!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/signupStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/productStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/myPageStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/mainStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/cartStyle.css">
    <!-- Javascript -->
    <script type="module" src="${pageContext.request.contextPath }/js/slideShow.js" defer></script>
<%--     <script type="module" src="${pageContext.request.contextPath }/js/slideShow.js" defer></script> --%>
    
<%--     <script type="module" src="${pageContext.request.contextPath }/js/login.js" defer></script> --%>
    <%-- <script type="module" src="${pageContext.request.contextPath }/js/signup.js" defer></script> --%>
     <%--<script type="module" src="${pageContext.request.contextPath }/js/myPage.js" defer></script>
    <script type="module" src="${pageContext.request.contextPath }/js/main.js" defer></script> --%>
</head>
<body>
<%@ include file = "/WEB-INF/views/headerNav.jsp" %>

<<!-- Cart -->
    <section id="cart" class="section">
        <div class="max-container">
            <div class="cart">
                <h2 class="signup__title">${buyerDO.buyerName}님의 구매 내역</h2>
                <c:forEach items="${test}" var="test">
                	<div class="paymentProduct">
                    	<img class="cartProduct__img" src="${beansDO.beanImg}" alt="">
                    	<div class="cartProductInfo">
                        	<p class="cartProductInfo__Name">${beansDO.beanName}</p>
                        	<div class="cartProductInfo__QtyDiv">
                            	<button class="cartProductInfo__QtyButton"><i class="fa-solid fa-plus"></i></button>
                            	<input type="text" class="cartProductInfo__QtyText" value="${beansDO.beanPrice}" />	
                            	<button class="cartProductInfo__QtyButton"><i class="fa-solid fa-minus"></i></button>
                        	</div>
                        	<p class="cartProductInfo__price">${beansDO.beanPrice}원</p>
                    	</div>
                	</div>
                </c:forEach>

                <div class="paymentCompletePrice">
                    <p class="cartTotalPriceText">결제 금액 : <p class="cartTotalPrice">${beansDO.beanPrice}</p></p>
                    <p class="cartTotalPriceText">현재 잔액 : <p class="cartTotalPrice">${buyerDO.point}</p></p>
                </div>
                <div class="paymentCompletePrice">
                    <button class="cartPayment">확인</button>
                </div>
            </div>
        </div>
    </section>

<%@ include file = "/WEB-INF/views/footer.jsp" %>
</body>
</html>