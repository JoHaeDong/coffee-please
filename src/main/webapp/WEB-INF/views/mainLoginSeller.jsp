<%@ page contentType="text/html; charset=UTF-8"
         import="java.util.*"
%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%@ include file = "/WEB-INF/views/header.jsp" %>
<!-- Main -->
<section id="mainIntro" class="section">
    <div class="max-container">
        <div class="mainIntro">
            <div id="slideShow" class="mainIntro__intro">
                <ul class="slides">
                  <li><img class="introImg" src="images/main1.gif" alt=""></li>
                  <li><img class="introImg" src="images/main2.png" alt=""></li>
  	              <li><img class="introImg" src="images/main3.png" alt=""></li>
                </ul>  
                <div class="controller">
                    <span class="prev">&lang;</span>  
                    <span class="next">&rang;</span>
                </div>
            </div>
            <div class="mainIntro__loginAll">
                <div class="mainIntro__loginAfter">
                    <div class="user">
                        <!--       <img class="mainIntro__loginImg" src="${pageContext.request.contextPath }/images/userImginit.png" alt="">-->
                        <img class="mainIntro__loginImg" src="${ seller.sellerImg}" alt="">
                        <p class="mainIntro__loginId">${ seller.nickname }<span class="mainIntro__loginIdText">님</span></p>
                    </div>
                    <div class="mainIntro__buyer">
                        <p class="mainIntro__loginPoint">${ seller.point }<span class="mainIntro__loginPointText"> point</span></p>
                        <div class="mainIntro__button">
                            <form method="Get" action="loginAfter">
                                  <button class="mainIntro__AfterButton" type="submit" name="action" value="logout">로그아웃</button>
                            	  <button class="mainIntro__AfterButton" type="submit" name="action" value="buyerModify">정보수정</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Category -->
<section id="mainCategory" class="section">
       <div class="max-container">
        <h1 class="mainCategory__title">Category</h1>
            	<div id="categoryList" class="mainCategory">
                    <c:forEach items="${categoryList}" var="categoryDO" >
        			<div class="mainCategory__detail">
        				<img id="categoryName"  alt="" class="mainCategory__detailImg" src="${categoryDO.categoryImg}"/>
        				<p class="mainCategory__detailTitle">${categoryDO.categoryName}</p>
        			</div>
        			</c:forEach>
            	</div>
        </div>
    </section>
<!-- BeanBest -->
<section id="mainBeanBest" class="section">
    <div class="max-container">
        <h1 class="mainBeanBest__title">Best</h1>
        <div class="mainBeanBest">
  			<c:forEach items="${bestBean}" var="beansDO" >
      	        <div class="mainBeanBest__product">
   	            	<img src="" alt="" class="beanBest__number">
   					<img src="${beansDO.beanImg}"  alt="" class="mainBeanBest__productImg" >
      				<div class="likeButton">
      	               	<button class="myPageLike__button"><i class="fa-solid fa-heart"></i></button>
      	               	<p class="mainBeanBest__productLikeCount">${beansDO.likeCount}</p>
      	   	    	</div>
      	    	</div>
   			</c:forEach>
      	</div>
        <div class="mainBeanBest__button">
            <h2 class="mainBeanBest__buttonTitle">더 많은 원두를 보려면?</h2>
            <form method="get" action="goProductList">
                <button class="mainBeanBest__plusButton"><i class="fa-solid fa-angles-right"></i></button>
            </form>
        </div>
    </div>
</section>
<!-- ResisterProduct -->
<section id="resisterProduct" class="section">
    <div class="max-container">
        <div class="resisterProduct">
            <img class="resisterProductImg" src="images/test1.jpg" alt="">
            <div class="resisterProductButton">
                <form method="get" action="goRegisterProd">
                    <button name="action" value="normal" class="resisterProductButton__detail">일반상품판매등록</button>
                    <button name="action" value="group" class="resisterProductButton__detail">공동상품판매등록</button>
                </form>
            </div>
        </div>
    </div>
</section>
<div class="sources">
    출처 <a href="https://kr.freepik.com/free-vector/number-collection-with-golden-style_2304153.htm#query=%EC%88%9C%EC%9C%84&from_query=%EB%93%B1%EC%88%98&position=7&from_view=search&track=sph">Freepik</a>
</div>

<!-- Javascript -->
<script type="module" src="${pageContext.request.contextPath }/js/slideShow.js" defer></script>
<%@ include file = "/WEB-INF/views/footer.jsp" %>