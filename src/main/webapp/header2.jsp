<!DOCTYPE html>
<html lang="en">
<head>
    <title>How To Create Bootstrap 5 Header For eCommerce Website</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.10.2/css/all.css" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css" />

    <style>
        header {
            background-color: #2196F3;
            padding: 10px 15px;
        }
        header nav .navbar-nav .nav-link {
            color: #efefef !important;
            font-size: 18px;
            font-weight: 500;
            padding: 0px 18px !important;
        }
        header .navbar-brand {
            max-width: 200px;
        }


        /* Header Search Bar */
        .header-search input[type="text"] {
            border: none;
        }
        .header-search .input-group-addon button {
            color: #fff;
            background: #ef2d2c;
            padding: 10px 15px;
            border: none;
            font-size: 12px;
        }
        .select-style {
            width: auto;
            padding: 0;
            margin: 0;
            display: inline-block;
            vertical-align: middle;
        }
        .select-style select {
            width: 100%;
            background: #efefef;
            color: #000;
            height: 38px;
            padding: 0px 9px;
            border: none;
            outline: none;
        }
        .select-style select option.topshow {
            padding: 10px 0;
            position: relative;
            top: 15px;
        }
        /* End Header Search Bar */


        /* Header cart CSS */
        header .cart a {
            color: #fff;
            text-decoration: none;
        }
        header .cart {
            position: relative;
            left: -15px;
        }
        header .cart a span.number {
            position: relative;
            left: 38px;
            top: -4px;
            color: #ef2d2c;
        }
        header .cart a span.number {
            position: relative;
            left: 38px;
            top: -4px;
            color: #ffffff;
        }
        header .cart a {
            color: #fff;
            text-decoration: none;
        }
        /* End Header cart CSS */


        /* choose store checkbox CSS */
        header [type="radio"]:checked + label,
        header [type="radio"]:not(:checked) + label {
            position: relative;
            padding-left: 5px;
            cursor: pointer;
            line-height: 20px;
            display: inline-block;
            color: #fff;
            font-family: "Conv_VisueltPro-Regular";
            font-size: 14px;
        }


        /* Responsive CSS */
        @media (max-width: 991px) {
            header .cart a img {
                width: 30px;
                margin-left: 10px;
            }
            header .cart a span.number {
                left: 40px;
                top: -7px;
                font-size: 14px;
            }
            header {
                padding: 10px 0px;
            }
            .cart-login {
                position: absolute;
                right: 70px;
            }
            header .navbar-brand {
                max-width: 150px;
            }
        }
    </style>
</head>
<body>
<header>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-6 col-md-6">
                <nav class="navbar navbar-expand-xl navbar-light">
                    <div class="container-fluid">
                        <a href="#" class="navbar-brand"><img src="/images/markuptag-white-logo.png" alt="logo" class="img-fluid" /></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavDropdown">
                            <ul class="navbar-nav mx-auto">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Beer</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Wine</a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        Liquor
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                        <li><a class="dropdown-item" href="#">Cider</a></li>
                                        <li><a class="dropdown-item" href="#">Mead</a></li>
                                        <li><a class="dropdown-item" href="#">Others</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>

            <div class="col-lg-4 col-md-3">
                <div class="header-search mt-2">
                    <div class="search-form">
                        <form method="get">
                            <div class="input-group">
                                <div class="select-style">
                                    <select name="order">
                                        <option class="topshow" value="1">All</option>
                                        <option value="a">Beer</option>
                                        <option value="b">Wine</option>
                                        <option value="c">Liquor</option>
                                        <option value="d">Cider</option>
                                        <option value="e">Mead</option>
                                        <option value="f">Others</option>
                                    </select>
                                </div>
                                <input type="text" name="search" id="search" class="form-control" placeholder="Search Here" />
                                <div class="input-group-addon">
                                    <button type="submit"><i class="fas fa-search"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-lg-2 col-md-3 cart-login">
                <div class="float-end cart mt-2">
                    <a href="#">
                        <span class="number">10</span>
                        <img class="img-fluid" src="/images/cart.png" alt="cart" />
                        <span>Cart</span>
                    </a>
                </div>

                <div class="float-end">
                    <button type="button" class="btn btn-light mt-2 btn-sm">Login</button>
                </div>
            </div>

            <div class="col-12 mt-sm-2 mt-lg-0">
                <div class="store-location p-0 text-end">
                    <div class="form-check-inline">
                        <input type="radio" id="test1" name="radio-group" data-toggle="modal" data-target=".bd-example-modal-lg" />
                        <label for="test1">Choose a Store</label>
                    </div>

                    <div class="form-check-inline">
                        <img class="img-fluid" src="/images/or.png" alt="Esplanda" width="20" />
                    </div>

                    <div class="form-check-inline">
                        <input type="radio" id="test2" name="radio-group" data-toggle="modal" data-target=".bd-example-modal-lg1" />
                        <label for="test2">Show Local Availblity</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Bootstrap JS -->
<script src="https://www.markuptag.com/bootstrap/5/js/bootstrap.bundle.min.js"></script>
</body>
</html>