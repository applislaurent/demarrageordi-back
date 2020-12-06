

<!DOCTYPE HTML>
<html>
   <head>
      <meta charset="UTF-8" />
      <title>Welcome</title>
      <link rel="stylesheet" type="text/css"
                href="css/styles.css"/>

   </head>
	<body>
      <h1>Démarrez tous vos sites web et vos logiciels favoris en un clic...</h1>
      
		<div>
			<img src="img/repos.png" />
		</div>
		
		<div>
		  <p>Vous en avez marre de cliquer 50 fois après chaque démarrage de votre ordinateur, pour ouvrir vos sites web et logiciel préférés?</p>
		  <p> Alors prenez quelques minutes aujourd'hui, pour gagner du temps ensuite.</p>
		  <p> 1. Renseigner ci-dessous les logiciels et sites que vous souhaitez ouvrir dès que votre ordinateur est allumé.</p>
		  <p> 2. Cliquer sur "Créer le fichier", puis enregistrer le fichier sur votre ordinateur (par exemple sur votre bureau).</p>
		  <p> 3. Chaque matin, après avoir allumé votre ordinateur,
		  double-cliquez sur le fichier et allez boire votre café: à votre retour vos sites et logiciels seront ouverts!</p>
		</div>
       
		<p><%=request.getAttribute("errorMessage")%></p>
 
 
 
 <!--    
     
        <form action="/demarrageordi/creer.batch"  method="post">
                <h2>Logiciels</h2>
                	 <div class="aligne">
                     <div class="logiciel">
                       Logiciel 1:
						           <div class="paddingLeft1Em">   
						              <label for="nomLogiciel1"> Nom: </label>
						               <input type="text" name="nomLogiciel1" placeholder=" Par exemple 'Firefox'" class="input100">
						           </div>
					             <div class="paddingLeft1Em">   
						              <label for="repertoireLogiciel1"> Répertoire: </label>
						              <input type="text" name="repertoireLogiciel1" placeholder=" Par exemple 'C:\Program Files'" class="input300">
                       </div>
                     </div>
                   </div>
                	 <div class="aligne">
                     <div class="logiciel">
                       Logiciel 2:
						           <div class="paddingLeft1Em">   
						              <label for="nomLogiciel2"> Nom: </label>
						               <input type="text" name="nomLogiciel2" placeholder=" Par exemple 'Firefox'" class="input100">
						           </div>
					             <div class="paddingLeft1Em">   
						              <label for="repertoireLogiciel2"> Répertoire: </label>
						              <input type="text" name="repertoireLogiciel2" placeholder=" Par exemple 'C:\Program Files'" class="input300">
						           </div>
                     </div>
                   </div>
                	 <div class="aligne">
                     <div class="logiciel">
                       Logiciel 3:
						           <div class="paddingLeft1Em">   
						              <label for="nomLogiciel3"> Nom: </label>
						               <input type="text" name="nomLogiciel3" placeholder=" Par exemple 'Firefox'" class="input100">
						           </div>
					             <div class="paddingLeft1Em">   
						              <label for="repertoireLogiciel3"> Répertoire: </label>
						              <input type="text" name="repertoireLogiciel3"placeholder=" Par exemple 'C:\Program Files'" class="input300">
						           </div>
                     </div>
                   </div>
                	 <div class="aligne">
                     <div class="logiciel">
                       Logiciel 4:
						           <div class="paddingLeft1Em">   
						              <label for="nomLogiciel4"> Nom: </label>
						               <input type="text" name="nomLogiciel4" placeholder=" Par exemple 'Firefox'" class="input100">
						           </div>
					             <div class="paddingLeft1Em">   
						              <label for="repertoireLogiciel4"> Répertoire: </label>
						              <input type="text" name="repertoireLogiciel4" placeholder=" Par exemple 'C:\Program Files'" class="input300">
						           </div>
                     </div>
                   </div>
                	 <div class="aligne">
                     <div class="logiciel">
                       Logiciel 5:
						           <div class="paddingLeft1Em">   
						              <label for="nomLogiciel5"> Nom: </label>
						               <input type="text" name="nomLogiciel5" placeholder=" Par exemple 'Firefox'" class="input100">
						           </div>
					             <div class="paddingLeft1Em">   
						              <label for="repertoireLogiciel5"> Répertoire: </label>
						              <input type="text" name="repertoireLogiciel5" placeholder=" Par exemple 'C:\Program Files'" class="input300">
						           </div>
                     </div>
                   </div>               
                <h2>Sites</h2>
					<div class="aligne">
						<label for="urlSiteweb1" type="url"> Adresse du site 1: </label>
						<input type="url" name="urlSiteweb1" placeholder=" Par exemple 'http://www.blablabla.fr'" class="input300">
					</div>
					<div class="aligne">
						<label for="urlSiteweb2" type="url"> Adresse du site 2: </label>
						<input type="url" name="urlSiteweb2" placeholder=" Par exemple 'http://www.blablabla.fr'" class="input300">
					</div>
					<div class="aligne">
						<label for="urlSiteweb3" type="url"> Adresse du site 3: </label>
						<input type="url" name="urlSiteweb3" placeholder=" Par exemple 'http://www.blablabla.fr'" class="input300">
					</div>
					<div class="aligne">
						<label for="urlSiteweb4" type="url"> Adresse du site 4: </label>
						<input type="url" name="urlSiteweb4" placeholder=" Par exemple 'http://www.blablabla.fr'" class="input300">
                     </div>
					</div>
						<div class="aligne">
							<label for="urlSiteweb5" type="url"> Adresse du site 5: </label>
						<input type="url" name="urlSiteweb5" placeholder=" Par exemple 'http://www.blablabla.fr'" class="input300">
					</div>
					<br>
						<p><input type="submit"  value="valider" /></p>
					<br>
            </form>
   
-->   
   
   
   
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="  crossorigin="anonymous"></script>


<form action="/demarrageordi/creer.batch"  method="post">
	<h2>Logiciels</h2>
	<script>
		$(document).ready(function() {
		    var wrapper         = $(".logiciels"); 
		    $(add_logiciel).click(function(e){ 
		        e.preventDefault();
		        $(wrapper).append('<div class="form-group"><label class="col-md-2" for="nomLogiciels" >Nom du logiciel</label><input type="text" name="nomLogiciels" class="col-md-2" placeholder=" Par exemple \'Firefox\'"/></a><label class="col-md-2" for="repertoireLogiciels">Répertoire du logiciel</label><input type="text" name="repertoireLogiciels" class="col-md-4" placeholder=" Par exemple \'C:\\Program Files\'"/><a href="#"	class="btn btn-danger btn-sm delFld"><span class="fas fa-trash-alt"></span>Supprimer</a></div>'); //add input box
		    });
		    
		    $(wrapper).on("click",".delFld", function(e){ 
		        e.preventDefault(); $(this).parent('div').remove();
		    })
		});
	</script>
	
	<div class="container">
	<div class="logiciels"></div>
		<button id="add_logiciel" class="addNew btn btn-success btn-sm"><span class="fa fa-plus"></span>Ajouter un logiciel</button>
	</div>
	
	<h2>Sites</h2>
	<script>
		$(document).ready(function() {
		    var wrapper         = $(".sites"); 
		    $(add_siteweb).click(function(e){ 
		        e.preventDefault();
		        $(wrapper).append('<div class="form-group"><label class="col-md-2" for="urls" >Adresse du site</label><input type="text" name="urls" class="col-md-4" placeholder=" Par exemple \'http://www.blablabla.fr\'"/><a href="#"	class="btn btn-danger btn-sm delFld"><span class="fas fa-trash-alt"></span>Supprimer</a></div>'); //add input box
		    });
		    
		    $(wrapper).on("click",".delFld", function(e){ 
		        e.preventDefault(); $(this).parent('div').remove();
		    })
		});
	</script>
	
	<div class="container">
	<div class="sites"></div>
		<button id="add_siteweb" class="addNew btn btn-success btn-sm"><span class="fa fa-plus"></span>Ajouter un site web</button>
	</div>	
	<br>
		<p><input type="submit"  value="valider" /></p>
	<br>
</form>

   
</html>