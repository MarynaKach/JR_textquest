<html>
<head>
    <meta charset="utf-8">
    <title>SPACE QUEST</title>
    <style>
        h1 {
            background: darkblue; /* Цвет фона под заголовком */
            color: goldenrod; /* Цвет текста */
            padding: 2px; /* Поля вокруг текста */
            text-align:  center;
        }
        div {
            text-align:  center;
        }

    </style>
</head>
<body>
<h1> *** WELCOME TO A SPACE QUEST *** </h1>
<h1>INTRO</h1>
<p>You woke up in the desert. There are only endless sands around you.
    You don't understand how you got here. You remember that you lost the battle with the space pirates.
    And now there are looking for you. You don't know what planet you're on. Suddenly a spaceship appears.
    Aliens are ready to help you if you answer all the questions correctly.
    Start the quest!</p>
<div>
    <form method="post" action="/quest">
        <label>Name:
            <input type="text" name="name"><br />
        </label>
        <input type="submit" value="Start"><br />
    </form>
</div>

<div>
    <img src=${pageContext.request.contextPath}"/images/startpicture.jpeg"  alt="Start picture"/>
</div>
</body>

</html>
