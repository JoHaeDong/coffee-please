<%@ page contentType="text/html; charset=UTF-8" 
	import="java.util.*"
    	  %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%@ include file = "/WEB-INF/views/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

<!-- SignupBuyer -->
    <section id="signup" class="section">
        <div class="max-container">
            <div class="signup">
                <div class="signup__form-div">
                    <h2 class="signup__title">Bean2B 구매자 회원 가입을 진행합니다.</h2> 
                    <h4 class="signup__description">맛있는 커피를 찾고 계신가요? 커피도시 부산의 신선한 원두로 여러분을 기다리고 있습니다.</h4> 
                    <form id="signup__form" >
                        <label for="new-userId" class="signup__label">
                            <i class="fa-regular fa-envelope signup__icon"></i>
                            <input
                                class="signup__input"
                                type="email"
                                id="new-userId"
                                name="buyerEmail"
                                placeholder="email@example.com"
                            />
                        </label> 
                        <p id="idCheckMsg"></p>
                        <label for="new-username" class="signup__label">
                            <i class="fa-regular fa-id-card signup__icon"></i>
                            <input
                                class="signup__input"
                                type="text"
                                id="new-username"
                                name="buyerName"
                                placeholder="이름을 입력해주세요."
                            />
                        </label>
                        <label for="new-password" class="signup__label">
                            <i class="fa-solid fa-key signup__icon"></i>
                            <input
                                class="signup__input"
                                type="password"
                                id="new-password"
                                name="passwd"
                                placeholder="4자리 이상 입력해주세요."
                            />
                        </label>
                         <div class="passwdMsg">
                            <p id="strengthDisp"></p>
                            <p id="strengthMsg"></p>
                        </div>
                        <label for="new-passwordConfirm" class="signup__label">
                            <i class="fa-solid fa-check signup__icon"></i>
                            <input
                                class="signup__input"
                                type="password"
                                id="new-passwordConfirm"
                                name="passwdConfirm"
                                placeholder="비밀번호 확인"
                            />
                        </label>
                        <p id="confirmMsg"></p>
                        <label for="new-nickname" class="signup__label">
                            <i class="fa-regular fa-circle-user signup__icon"></i>
                            <input
                                class="signup__input"
                                type="text"
                                id="new-nickname"
                                name="nickname"
                                placeholder="2자리 이상 입력해주세요."
                            />
                        </label>
                        <label for="new-phoneNumber" class="signup__label">
                            <i class="fa-solid fa-phone signup__icon"></i>
                            <input
                                class="signup__input"
                                type="text"
                                id="new-tel"
                                name="tel"
                                placeholder="휴대폰번호를 입력해주세요."
                                maxlength="13"
                            />
                        </label>
                        <label for="new-address" class="signup__label">
                            <i class="fa-solid fa-map-location-dot signup__icon"></i>
                            <input
                                class="signup__input"
                                type="text"
                                id="new-address"
                                name="address"
                                placeholder="주소를 입력해주세요."
                            />
                        </label>
                        <button class="signup__button" type="submit">회원가입</button>
                    </form>
                    <div id="signUpMsg">${msg}</div>
                </div>
                
            </div>
        </div>
    </section>
    <!-- Javascript -->
   	<script type="module" src="${pageContext.request.contextPath }/js/signup.js" defer></script>

<%@ include file = "footer.jsp" %>



















