window.addEventListener('DOMContentLoaded', event => {
    const datatablesSimple = document.getElementById('orderTable'); // Sử dụng ID của bảng
    if (datatablesSimple) {
        const dataTable = new simpleDatatables.DataTable(datatablesSimple, {
            searchable: false, // Tắt chức năng tìm kiếm
            perPage: false, // Đặt giá trị lớn để không phân trang
            perPageSelect: false, // Ẩn dropdown cho số lượng entries per page
            columns: [
                {select: 0, sortable: false}, // ID
                {select: 1, sortable: true}, // Customer Name
                {select: 2, sortable: false}, // Product(s)
                {select: 3, sortable: true}, // Total
                {select: 4, sortable: false}, // Payment Method
                {select: 5, sortable: true}, // Date
                {select: 6, sortable: true}, // Sales Name
                {select: 7, sortable: true}, // Status
                {select: 8, sortable: false},
            ],
        });

        // Xóa thông báo "Showing 1 to 5 of 5 entries"
        const infoElement = datatablesSimple.querySelector('.dataTable-info');
        if (infoElement) {
            infoElement.remove(); // Xóa phần tử thông báo
        }
    }
});