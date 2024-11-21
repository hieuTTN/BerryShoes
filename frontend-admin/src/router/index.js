import layoutAdmin from "../layout/admin/Layout";

//admin
import AdminUser from '../page/admin/user'
import AdminTrademark from '../page/admin/trademark'
import AdminProduct from '../page/admin/product'
import AdminAddProduct from '../page/admin/addproduct'
import AdminInvoice from '../page/admin/order'
import AdminAddNhanVien from '../page/admin/addnhanvien'
import AdminDeGiay from '../page/admin/degiay'
import AdminChatLieu from '../page/admin/chatlieu'
import AdminKichCo from '../page/admin/kichco'
import AdminMauSac from '../page/admin/mausac'
import AdminSanPhamChiTiet from '../page/admin/sanphamchitiet'
import AdminDonHang from '../page/admin/donhang'
import AdminPhieuGiamGia from '../page/admin/phieugiamgia'
import AdminAddPhieuGiamGia from '../page/admin/addphieugiamgia'
import AdminDatTaiQuay from '../page/admin/dattaiquay'
import AdminThongKe from '../page/admin/thongke'

//public
import LoginPage from "../page/public/LoginPage";

const publicRoutes = [
    {path: "/", component: LoginPage},
    {path: "/login", component: LoginPage},
];

const adminRoutes = [
    // { path: "/admin/index", component: homeAdmin, layout: layoutAdmin },
    { path: "/admin/user", component: AdminUser, layout: layoutAdmin },
    { path: "/admin/trademark", component: AdminTrademark, layout: layoutAdmin },
    { path: "/admin/product", component: AdminProduct, layout: layoutAdmin },
    { path: "/admin/add-product", component: AdminAddProduct, layout: layoutAdmin },
    { path: "/admin/order", component: AdminInvoice, layout: layoutAdmin },
    { path: "/admin/add-nhan-vien", component: AdminAddNhanVien, layout: layoutAdmin },
    { path: "/admin/de-giay", component: AdminDeGiay, layout: layoutAdmin },
    { path: "/admin/chat-lieu", component: AdminChatLieu, layout: layoutAdmin },
    { path: "/admin/kich-co", component: AdminKichCo, layout: layoutAdmin },
    { path: "/admin/mau-sac", component: AdminMauSac, layout: layoutAdmin },
    { path: "/admin/sanphamchitiet", component: AdminSanPhamChiTiet, layout: layoutAdmin },
    { path: "/admin/don-hang", component: AdminDonHang, layout: layoutAdmin },
    { path: "/admin/khuyen-mai", component: AdminPhieuGiamGia, layout: layoutAdmin },
    { path: "/admin/add-khuyen-mai", component: AdminAddPhieuGiamGia, layout: layoutAdmin },
    { path: "/admin/dat-tai-quay", component: AdminDatTaiQuay, layout: layoutAdmin },
    { path: "/admin/thong-ke", component: AdminThongKe, layout: layoutAdmin },
];




export {publicRoutes, adminRoutes};
