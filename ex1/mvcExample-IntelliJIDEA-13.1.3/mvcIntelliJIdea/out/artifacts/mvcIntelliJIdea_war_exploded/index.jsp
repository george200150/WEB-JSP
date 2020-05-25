<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Problema1</title>
    <link rel="stylesheet" type="text/css" href="mystyle.css">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js">  </script>
    <script>

        $(document).ready(function(){
            $.ajax({
                type:'POST',
                url : 'getAllActive',
                success: function(result){
                    $('#myTable').html(result);
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
                <form action="/login" method="POST">
                    <input type='hidden' name='tip' value='login'>
                    <label>Username</label><br>
                    <input type="text" name='username' placeholder="Enter Username" required><br>
                    <label>Password</label><br>
                    <input type="password" name='password' placeholder="Enter Password" required>
                    <br><br>
                    <button type="submit">Login</button>
                </form>
            </td>
            <td>
                <article>CHEC

                    <br/><br/>

                    INGREDIENTE
                    <br/>6 oua
                    <br/>6 linguri faina
                    <br/>3 linguri ulei
                    <br/>6 linguri zahar
                    <br/>2 linguri de cacao
                    <br>1 lingurita praf de copt
                    <br/>un praf sare
                </article>

                <br/><br/>

                <p>Comentarii: </p>
                <table id = 'myTable'>
                </table>
                <br>
                <h3> Adauga un nou comentariu </h3>
                <form action="/comment" method="POST">
                    <input type='hidden' name='tip' value='comentariu'>
                    <input type='text' name='username' placeholder='Username'><br>
                    <input type='text' name='message' placeholder='Comentariu'><br>

                    <button type='submit'>Submit</button>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
