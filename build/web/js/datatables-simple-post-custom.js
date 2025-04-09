window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple, {
            searchable: true, // Cho phép tìm kiếm
            fixedHeight: true, // Thiết lập chiều cao cố định với cuộn dọc
            perPage: 5, // Số dòng mỗi trang
            columns: [
                // Vô hiệu hóa sắp xếp cho một số cột
                {select: 0, sortable: false, searchable: false}, // Vô hiệu hóa sắp xếp cho cột đầu tiên (ID)
                {select: 1, sortable: false, searchable: false},
                {select: 2, sortable: true, searchable: true},
                {select: 3, sortable: true, searchable: true},
                {select: 4, sortable: true, searchable: true}, 
                {select: 5, sortable: true, searchable: false},
                {select: 6, sortable: true, searchable: false},
                {select: 7, sortable: false, searchable: false},
                {select: 8, sortable: true, searchable: false},
                {select: 9, sortable: true, searchable: false},
            ],
        });
    }

    // Gọi hàm lọc khi thay đổi trạng thái
    document.getElementById("categoryFilter").addEventListener('change', filterTable);
    document.getElementById("authorFilter").addEventListener('change', filterTable);
    document.getElementById("statusFilter").addEventListener('change', filterTable);
});
