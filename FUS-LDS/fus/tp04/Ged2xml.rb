#!/usr/bin/ruby

# Message donnant la syntaxe d'utilisation
syntaxe="Usage : #{$0} FichierOrigine.ged FichierResultat.txt"

# Vérification du nombre de paramètres
# Pour calculer le nombre de paramètres, on utilise la fonction ...
nbarg=ARGV.size
if (nbarg!=2) then abort syntaxe ; end

# Renommage des paramètres
(entree, sortie)=ARGV

# Vérification de l'existence des fichiers
if (! File.exist?(entree))
	abort "\t[Erreur: Le fichier d'entrée #{entree} n'existe pas]\n";
end

if (File.exist?(sortie))
	abort "\t[Erreur: Le fichier de sortie #{sortie} existe déjà]\n";
end

# Ouverture du fichier d'entrée
begin
	fe=File.open(entree,"r") 
	rescue Errno::ENOENT
		abort "\t[Erreur: Echec d'ouverture du fichier d'entrée #{entree}]\n"
end

# Ouverture du fichier de sortie

begin
	fs=File.open(sortie,"w") 
	rescue Errno::ENOENT
		abort "\t[Erreur: Echec d'ouverture du fichier de sortie #{sortie}]\n"
end

# Redirection du fichier de sortie vers la sortie standard dans la phase de test
#fs=STDOUT
tab=[]
oldlevel=0
level=0

fs.print("<?xml-stylesheet href=\"style.css\" type=\"text/css\"?>\n")
fs.print "<GEN>\n"
# Boucle de lecture du fichier d'entrée
begin
	while line=fe.readline
		#fs.print line

		# Retrait des blancs en fin de ligne
		line = line.sub(/\s*$/,"")

		# Séparation des lignes en 2 parties : le chiffre et le reste
		if (line =~ /(\d) (.*)/)
			level=$1.to_i
			line=$2
		end

		#fs.print level
	
		# Fermeture des balises (à faire en dernier)
		if (level<oldlevel)
			diff = oldlevel-level
			for i in 1..diff+1 do
				fs.print(tab.pop())
			end
		end
		
		# Sélection des lignes de la classe des identificateurs @...@ (INDI,FAM)
		if (line =~ /@(.*)@\s*(INDI|FAM)$/)
			id=$1
			balise=$2
			fs.print("<#{balise} ID=\"#{id}\">\n")
			tab.push("</#{balise}>\n")
		# Sélections des lignes de la classe NAME
		elsif (line =~ /(NAME)\s+(.*)\/(.*)\/$/)
			balise=$1
			firstname=$2
			lastname=$3
			fs.print("<#{balise}>#{firstname}<S>#{lastname}</S></#{balise}>\n")
		# Sélection des lignes avec un identificateur de fin (FAMS, FAMC, HUSB, WIFE, CHIL...)
		elsif (line =~ /(FAMS|FAMC|HUSB|WIFE|CHIL|ASSO)\s*@(.*)@$/)
			balise=$1
			id=$2
			fs.print("<#{balise} REF=\"#{id}\"></#{balise}>\n")
		# ...
		elsif (line =~ /(BIRT|DEAT|MARR|MARC|TRLR|CHR|BURI|HEAD|RESI|GEDC)/)
			balise=$1
			fs.print("<EVEN EV='#{balise}'>\n")
			tab.push("</EVEN>\n")

		
		elsif (line =~ /(SEX|OCCU|DATE|PLAC|AGE|REFN|NOTE|SOUR|TYPE|RELA|CHAR|LANG|CORP|VERS|CONT)\s*(.*)$/)
			balise=$1
			value=$2
			fs.print("<#{balise}>#{value}</#{balise}>\n")
			
		#Cas non prévus
		else
			print("Ligne non prévue: #{line}\n") 	
		end

		# Mémorisation de l'ancien niveau
		oldlevel=level
		
	end
	rescue EOFError
end
# Fermeture des balises non fermées
for i in 0..tab.size
	fs.print(tab.pop)
end
fs.print("</GEN>\n")

fe.close
fs.close





