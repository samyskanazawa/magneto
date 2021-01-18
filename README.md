# Challenge MeLi - Magneto Mutants

## Documentación oficial

	- Maven   ( Version homologada: 3.6.1 )
	- Java    ( Version homologada: 11.0.9, vendor: Oracle Corporation )
	- STS     ( Version: 4.4.1.RELEASE)
	- Spring boot (Version: 2.4.2)
	

Descargando el repositorio:

    -git clone git@github.com:samyskanazawa/magneto.git
	
Para ejecutar la aplicacion utilizando Maven desde STS:

	clean install
	
Workflow:

	- Descargar repositorio.
	
	- Validar funcionamiento del proyecto descargado ejecutando la aplicacion via Maven.
	
	- Desde el Eclipse o STS, importar el proyecto (importar como proyecto Maven existente).
	
Ambientes disponibles:
	
	Los mismos se encuentran configurados en el archivo "application.yml"
	- dev
	- qa
	
End-Points:

	-  /mutant: en donde se puede detectar si un humano es mutante enviando la secuencia de 
	   ADN mediante un HTTP POST con un Json el cual tenga el siguiente formato:
       
       {"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
    
    - /stats: devuelve un Json con las estadísticas de las verificaciones de ADN: 
    	{"count_mutant_dna":40, "count_human_dna":100: "ratio":0.4} 
    	
