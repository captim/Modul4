<html>
<body>
<script>
    var request = new XMLHttpRequest();
    function redirect() {
        request.open("POST", "utils.jsp?redirect=questions&user=" + document.getElementById("name"));
        request.send();
    }
</script>
<form>
    <p1>Print name:</p1><input id="name" type="text">
    <input type="submit" onclick="redirect()">
</form>
</body>
</html>
