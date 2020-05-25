<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Problema1</title>
    <link rel="stylesheet" type="text/css" href="../mystyle.css">
    <script src="../js/jquery.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $.ajax({
                type: 'POST',
                url: 'getAllInactive',
                success: function (result) {
                    $('#mess').html(result);
                }
            });
        });
    </script>
</head>
<body>

<div class="container">
    <table>
        <tr>
            <td>
                <h2>Authentication</h2>
                <form action="/logout">
                    <button type="submit" >Log out</button>
                </form>
            </td>
            <td>
                <h3>Comentarii in asteptare</h3>
                <table id='mess' border=1px>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
