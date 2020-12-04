<!DOCTYPE HTML>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Welcome</title>
      <link rel="stylesheet" type="text/css"
                href="${pageContext.request.contextPath}/css/style.css"/>
   </head>
	<body>
      <h1>Welcome blabla2</h1>

        <form action="/demarrageordi/creer.batch"  method="post">
           <p>nomLogiciel1 : <input type="text" name="nomLogiciel1" /></p>
           <p>repertoireLogiciel1 : <input type="text" name="repertoireLogiciel1" /></p>
           <p>urlSiteweb1 : <input type="text" name="urlSiteweb1" /></p>
           <p><input type="submit"  value="valider" /></p>
        </form>
       
     
         
      <!-- <a href="${pageContext.request.contextPath}/personList">Person List</a>    -->
       
   </body>
   
</html>