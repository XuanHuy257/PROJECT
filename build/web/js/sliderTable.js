window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesSimple = document.getElementById('sliderTable');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple, {
            searchable: true, // Cho phép tìm kiếm
            fixedHeight: true, // Thiết lập chiều cao cố định với cuộn dọc
            perPage: 5, // Số dòng mỗi trang
            columns: [
                // Vô hiệu hóa sắp xếp cho một số cột
                {select: 0, sortable: true, searchable: false}, // Vô hiệu hóa sắp xếp cho cột đầu tiên (ID)
                {select: 1, sortable: false, searchable: false},
                {select: 2, sortable: true, searchable: false},
                {select: 3, sortable: true, searchable: false},
                {select: 4, sortable: false, searchable: false}, 
            ],
        });
    }
});
