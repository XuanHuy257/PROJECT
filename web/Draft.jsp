<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <title>Xóa ??a ch?</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }

            /* Background for popup */
            .popup-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.5);
                z-index: 1;
            }

            /* Popup box */
            .popup-box {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: #fff;
                width: 300px;
                padding: 20px;
                border-radius: 10px;
                text-align: center;
                z-index: 2;
            }

            .popup-box h3 {
                margin-top: 0;
                color: #007bff;
            }

            .popup-box p {
                margin: 20px 0;
            }

            /* Buttons */
            .popup-box .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }

            .popup-box .btn-close {
                background: #007bff;
                color: white;
            }

            .popup-box .btn-confirm {
                background: #dc3545;
                color: white;
            }

            /* Styling the buttons */
            .popup-box .btn-close:hover,
            .popup-box .btn-confirm:hover {
                opacity: 0.9;
            }
        </style>
    </head>
    <body>

        <!-- Popup structure -->
        <div class="popup-overlay" id="popupOverlay">
            <div class="popup-box">
                <h3>Xóa ??a ch?</h3>
                <p>B?n mu?n xóa ??a ch? này ra kh?i s? ??a ch??</p>
                <button class="btn btn-close" onclick="closePopup()">Không</button>
                <button class="btn btn-confirm" onclick="confirmDeletion()">??ng ý</button>
            </div>
        </div>

        <button onclick="openPopup()">Hi?n th? pop-up</button>

        <script>
            // Function to open popup
            function openPopup() {
                document.getElementById('popupOverlay').style.display = 'block';
            }

            // Function to close popup
            function closePopup() {
                document.getElementById('popupOverlay').style.display = 'none';
            }

            // Function to confirm deletion
            function confirmDeletion() {
                alert('??a ch? ?ã ???c xóa.');
                closePopup();
            }
        </script>

    </body>
</html>
