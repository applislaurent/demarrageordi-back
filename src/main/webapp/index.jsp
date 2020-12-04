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
		  <p>Vous en avez marre de cliquer 50 fois après chaque démarrage de votre ordinateur, pour ouvrir vos sites web et logiciel préférés?</p>
		  <p> Alors prenez quelques minutes aujourd'hui, pour gagner du temps ensuite.</p>
		  <p> 1. Renseigner ci-dessous les logiciels et sites que vous souhaitez ouvrir dès que votre ordinateur est allumé.</p>
		  <p> 2. Cliquer sur "Créer le fichier", puis enregistrer le fichier sur votre ordinateur (par exemple sur votre bureau).</p>
		  <p> 3. Chaque matin, après avoir allumé votre ordinateur,
		  double-cliquez sur le fichier et allez boire votre café: à votre retour vos sites et logiciels seront ouverts!</p>
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