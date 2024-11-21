import layoutLogin from '../layout/loginlayout/login'

//public
import LoginPage from "../page/login";
import Home from "../page/home";
import ChiTietSanPham from "../page/chitietsanpham";
import GioHang from "../page/giohang";
import TaiKhoan from "../page/taikhoan";
import Payment from "../page/payment";
import Product from "../page/product";
import DangKy from "../page/dangky";
import QuenMatKhau from "../page/quenmatkhau";

const publicRoutes = [
    {path: "/login", component: LoginPage, layout: layoutLogin },
    {path: "/", component: Home },
    {path: "/chitietsanpham", component: ChiTietSanPham },
    {path: "/giohang", component: GioHang },
    {path: "/taikhoan", component: TaiKhoan },
    {path: "/payment", component: Payment },
    {path: "/product", component: Product },
    {path: "/regis", component: DangKy },
    {path: "/forgot", component: QuenMatKhau },
];


export {publicRoutes};
