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
      
		<div>
			<img src="assets/images/repos.png" />
		</div>
		
		<div>
		  <p>Vous en avez marre de cliquer 50 fois apr�s chaque d�marrage de votre ordinateur, pour ouvrir vos sites web et logiciel pr�f�r�s?</p>
		  <p> Alors prenez quelques minutes aujourd'hui, pour gagner du temps ensuite.</p>
		  <p> 1. Renseigner ci-dessous les logiciels et sites que vous souhaitez ouvrir d�s que votre ordinateur est allum�.</p>
		  <p> 2. Cliquer sur "Cr�er le fichier", puis enregistrer le fichier sur votre ordinateur (par exemple sur votre bureau).</p>
		  <p> 3. Chaque matin, apr�s avoir allum� votre ordinateur,
		  double-cliquez sur le fichier et allez boire votre caf�: � votre retour vos sites et logiciels seront ouverts!</p>
		</div>

        <form action="/demarrageordi/creer.batch"  method="post">
           <p>nomLogiciel1 : <input type="text" name="nomLogiciel1" /></p>
           <p>repertoireLogiciel1 : <input type="text" name="repertoireLogiciel1" /></p>
           <p>urlSiteweb1 : <input type="text" name="urlSiteweb1" /></p>
           <p><input type="submit"  value="valider" /></p>
        </form>
       
     
         
      <!-- <a href="${pageContext.request.contextPath}/personList">Person List</a>    -->
       
   </body>
   
</html>