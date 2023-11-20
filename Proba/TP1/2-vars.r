txt <- "33"
print(class(txt))
nbr <- as.integer(txt)
print(is.numeric(nbr))

mot <- "petite"
text1 <- paste("une", mot, "phrase")
text2 <- paste(text1, "compte", nchar(text1), "lettres")

tmp <- 3 / 0
nsp <- NA
resultat <- paste(tmp, tmp+1, tmp+nsp)

vecteur1 <- c(1,3,5,7,9)
# print(vecteur1)
vecteur2 <- seq(from=0, to=10, by=2)
# print(vecteur2)
vecteur3 <- 0:10
# print(vecteur3)
vecteur4 <- rep(1:2, 5)
# print(vecteur4)

# print(vecteur1)
# print(vecteur1[1])
# print(vecteur1[0])
# print(rnorm(5))

v1 <- c(175, 182, 165, 187, 158)
v2 <- c(19, 18, 21, 22, 20)
v3 <- c("Louis", "Paule", "Pierre", "Rémi", "Claude")
tableau <- data.frame(prenom=v3, taille=v1, age=v2)
print(names(tableau))
print(tableau$prenom)
write.table(tableau, "sortie.csv", sep=";",row.names = FALSE, col.names =FALSE)
