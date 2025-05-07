<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tìm kiếm</title>
<!-- Add Bootstrap CSS if needed -->
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
    crossorigin="anonymous">
<!-- Add Font Awesome for icons -->
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    integrity="sha512-C5moVFCeYn2FEFpNSFfSMjNfExuW0gZ0Dd1P3mmjGNoRZwFm3lQdbTkvHlL9Kmmu/QlK+2n97fzK0F9wQVc4Ug=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
.content__product {
    display: grid;
    grid-template-columns: 2fr 1fr;
}

.content__main__product {
    display: flex;
    flex-direction: column;
}

.content__product-detail {
    display: flex;
    box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.75);
    margin-bottom: 15px;
    border-radius: 5px;
}

.content__product-image {
    width: 192px;
    height: 100%;
    cursor: pointer;
    transition: transform 0.3s ease;
}

.content__product-image:hover img {
    transform: scale(1.2);
}

.product-image {
    width: 100%;
    height: 100%;
}

.hidden {
    display: none;
}

.content__product-description {
    margin: 20px 10px;
    width: 160px;
}

.product-name {
    font-weight: 500;
    font-size: 16px;
}

.product-description {
    display: none;
}

.product-by {
    text-decoration: none;
    font-weight: 600;
}

.product_info {
    font-size: 14px;
    font-weight: 500;
}

.product-stock,
.product-discount {
    color: #55cf87;
    font-size: 14px;
    font-weight: 500;
}

.product-quantity {
    font-size: 14px;
    font-weight: 500;
}

.product-quantity-id {
    margin: 2px 0 10px 0;
    display: flex;
}

.product-quantity-number {
    background: darkgray;
    color: #0e1516;
    width: 30px;
    text-align: center;
}

.product-date {
    opacity: 0.8;
    color: #69737c;
}

.content__product-action {
    margin-left: 150px;
    border-left: 1px solid #0e1516;
    text-align: center;
    padding: 0 12px;
}

.product-amount {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
}

.product-amount-start {
    background: #9e94ff;
    padding: 4px;
    border-radius: 4px;
    color: #ffffff;
}

.product-price {
    margin: 6px 0;
    font-weight: 600;
}

.product-free {
    margin: 6px 0;
    color: darkgray;
}

.product-wishlist-head,
.product-add-cart {
    margin: 12px 0;
    padding: 10px 30px;
    background: #9e94ff;
    border-radius: 6px;
    color: #ffffff;
    font-weight: 550;
}

.pagination {
    margin-top: 20px;
    text-align: center;
}

.pagination a, .pagination span {
    display: inline-block;
    padding: 5px 10px;
    margin: 0 2px;
    border: 1px solid #ddd;
    color: #007bff;
    border-radius: 3px;
    text-decoration: none;
}

.pagination a:hover {
    background-color: #007bff;
    color: #fff;
}

.pagination span {
    background-color: #007bff;
    color: #fff;
    cursor: default;
}
</style>
</head>
<body class="js">
    <!-- Breadcrumbs -->
    <div class="breadcrumbs">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="bread-inner">
                        <ul class="bread-list">
                            <li><a href="${pageContext.request.contextPath}/home">Trang chủ<i class="ti-arrow-right"></i></a></li>
                            <li class="active"><a href="${pageContext.request.contextPath}/showlistsearch">Tìm kiếm</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Breadcrumbs -->

    <!-- Shopping Cart -->
    <div class="shopping-cart section">
        <div class="container">
            <div class="content__product">
                <div class="content__main__product">
                    <c:forEach var="pet" items="${pets}" begin="1" end="10">
                        <div class="content__product-detail">
                            <div class="content__product-image">
                                <img class="product-image" src="${pageContext.request.contextPath}/assets/user/images/pet/${pet.image}" alt="${pet.petName}">
                            </div>
                            <div class="content__product-description">
                                <div class="product-name">${pet.petName}</div>
                                <div class="product-discount">Giá: ${pet.money} triệu đồng</div>
                                <div class="product-description">${pet.description}</div>
                                <div class="product_info">
                                    Kích thước <a class="product-by" href="#">${pet.petType}</a>
                                    <div class="product-stock">Xuất xứ: ${pet.made}</div>
                                    <div class="product-quantity">Số lượng: ${pet.amount}</div>
                                    <div class="product-date">Ngày sinh: ${pet.petBirthday}</div>
                                </div>
                            </div>
                            <div class="content__product-action">
                                <div class="content__product-action-list">
                                    <div class="product-wishlist-head">
                                        <a href="#">
                                            <i class="fas fa-heart"></i> Thêm vào yêu thích
                                        </a>
                                    </div>
                                    <div class="product_action">
                                        <div class="product-add-cart">
                                            <a href="#">
                                                <i class="fas fa-archive"></i> Thêm vào giỏ hàng
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                   <!-- Pagination -->
<div class="pagination">
    <c:forEach begin="1" end="${numberOfPages}" varStatus="page">
        <c:choose>
            <c:when test="${page.index == currentPage}">
                <span>${page.index}</span> <!-- Hiển thị số trang hiện tại mà không tạo liên kết -->
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/showlistsearch?page=${page.index}">${page.index}</a>
                <!-- Tạo liên kết đến các trang khác -->
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
<!-- End Pagination -->

                </div>
            </div>
        </div>
    </div>
    <!-- End Shopping Cart -->

</body>
</html>
