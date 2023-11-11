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
    <script type="module" src="${pageContext.request.contextPath }/js/cart.js" defer></script>
<%--     <script type="module" src="${pageContext.request.contextPath }/js/slideShow.js" defer></script> --%>
    
<%--     <script type="module" src="${pageContext.request.contextPath }/js/login.js" defer></script> --%>
    <%-- <script type="module" src="${pageContext.request.contextPath }/js/signup.js" defer></script> --%>
     <%--<script type="module" src="${pageContext.request.contextPath }/js/myPage.js" defer></script>
    <script type="module" src="${pageContext.request.contextPath }/js/main.js" defer></script> --%>
</head>
<body>
<%@ include file = "/WEB-INF/views/headerNav.jsp" %>

<!-- Cart -->
    <section id="cart" class="section">
        <div class="max-container">
            <div class="cart" id="cartList">
                <h2 class="cartTitle">${buyer.nickname}님의 장바구니</h2>
                <c:forEach items="${cart}" var="cartList" varStatus="index">

                	<div class="cartProduct" id="checkBoxes">
                    	<input type="checkbox" class="cartProduct__check" id="checkBox${index.count}" value="${cartList.beansDO.beanPrice}" data-index="${index.count}" checked/>
                    	<img class="cartProduct__img" src="${cartList.beansDO.beanImg}" alt="">
                    	<div class="cartProductInfo">
                        	<p class="cartProductInfo__Name">${cartList.beansDO.beanName}</p>
                        	<div class="cartProductInfo__QtyDiv" id="qtyBtn">
                            	<button class="cartProductInfo__plus cartProduct__check" id="plus${index.count}" data-index="${index.count}" disabled><i class="fa-solid fa-plus"></i></button>
                            	<span class="qty cartProduct__check">${cartList.cartDO.qty}</span>개
                            	<button class="cartProductInfo__minus cartProduct__check" id="minus${index.count}" data-index="${index.count}" disabled><i class="fa-solid fa-minus"></i></button>
                        	</div>
                        	<p class="cartProductInfo__price">${cartList.beansDO.beanPrice}</p>
                    	</div>
                        <input type="hidden" name="beansNum" id="num" value="${cartList.beansDO.beansNum}" />
                    	<button type="submit" class="cartProductInfo__delete" id="delete${index.count}" data-index="${index.count}" data-beans-num="${cartList.beansDO.beansNum}"><i class="fa-solid fa-trash"></i></button>
                	</div>
                </c:forEach>

                <div class="cartPrice">
                    <p class="cartTotalPriceText">합산 금액: <span id="totalPrice">${totalPrice}</span>원</p>

                    <button class="cartPayment">결제</button>
                </div>
            </div>
        </div>
    </section>
    
<%@ include file = "/WEB-INF/views/footer.jsp" %>
</body>
</html>
