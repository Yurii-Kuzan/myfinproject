<%--
  Created by IntelliJ IDEA.
  User: Yurii
  Date: 15.02.2021
  Time: 15:03
  To change this template use File | Settings | File Templates.<a href="${pageContext.request.contextPath}/newUser">Зарегистрироваться</a>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<section class="home">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 home__wrapper">
                <div class="home__card card">
                    <div class="home__info">
                        <h1>Чтобы воспользоваться нашими услугами необходимо войти или зарегестрироваться</h1>
                        <nav>
                            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
                                        data-bs-target="#nav-profile" type="button" role="tab"
                                        aria-controls="nav-profile" aria-selected="false">
                                    Зарегестрироваться
                                </button>
                                <button class="nav-link active" id="nav-contact-tab" data-bs-toggle="tab"
                                        data-bs-target="#nav-contact" type="button" role="tab"
                                        aria-controls="nav-contact" aria-selected="false">
                                    Войти
                                </button>
                            </div>
                        </nav>
                    </div>
                    <div class="home__action">
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                                 aria-labelledby="nav-profile-tab">
                                <form action="${pageContext.request.contextPath}/newUser" method="post">
                                    <div class="mb-3">
                                        <label for="reg_input_login">Email</label>
                                        <input type="email" class="form-control" id="reg_input_login" name="login" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="reg_first_name">First Name</label>
                                        <input type="text" class="form-control" id="reg_first_name" name="firstname" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="reg_last_name">Last Name</label>
                                        <input type="text" class="form-control" id="reg_last_name" name="lastname" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="reg_password">Password</label>
                                        <input type="password" class="form-control" id="reg_password" name="prepassword" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Register</button>
                                </form>
                            </div>
                            <div class="tab-pane fade show active" id="nav-contact" role="tabpanel"
                                 aria-labelledby="nav-contact-tab">
                                <form action="${pageContext.request.contextPath}/login" method="post">
                                    <div class="mb-3">
                                        <label for="exampleInputEmail1" class="form-label">Email address</label>
                                        <input type="email" name="user_login" class="form-control"
                                               id="exampleInputEmail1" aria-describedby="emailHelp" required>
                                        <div id="emailHelp" class="form-text">We'll never share your email with anyone
                                            else.
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="exampleInputPassword1" class="form-label">Password</label>
                                        <input type="password" name="user_password" class="form-control"
                                               id="exampleInputPassword1" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Login</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>