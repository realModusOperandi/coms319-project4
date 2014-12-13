<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Collaborative White Board</title>
        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    </head>
    <body>
        <h1>Web based tron</h1>
        <div class="col-md-offset-1">
        <h2>Players</h2>
        <ul id="playerList">
        </ul>
        </div>
        <div class="col-md-offset-1">
          <canvas id="myCanvas" width="800" height="800" style="border:1px solid #000000;"></canvas><br><br>
          <button type="button" class="btn btn-success" onclick="startGame()">Start Game</button>
          <button type="button" class="btn btn-danger" onclick="pauseGame()">Pause Game</button>
        </div>

<div id="output"></div>
<script type="text/javascript" src="websocket.js"></script>
<script type="text/javascript" src="whiteboard.js"></script>

</body>
</html>