<!DOCTYPE html>
<html>
<head>
    <title>Error <%= request.getAttribute("code") %>!</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>Error <%= request.getAttribute("code") %>!</h1>
        <p>Message: <%= request.getAttribute("message") %></p>
        <p>URI: <%= request.getAttribute("uri") %></p>
        <p>Servlet: <%= request.getAttribute("servletName") %></p>
        <p>Exception: <%= request.getAttribute("exceptionName") %></p>
        <p>Exception message: <%= request.getAttribute("exceptionMessage") %></p>
    </div>
</body>
</html>