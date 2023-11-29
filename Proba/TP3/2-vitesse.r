# Modifier pour mettre votre dossier
setwd("~/Documents/INSA/S5/Proba/TP3")

# Q5 : 
vitesses <- read.csv2(file = "vitesse.csv", header = T, sep = ";", dec = ",")

# Q6 : 
moyennes <- tapply(X = vitesses$vecVitesses, INDEX = vitesses$vecNum, FUN=mean)

# Q7 : 
intervalleConfiance <- sapply(X = moyennes, 
                              FUN = function (moyenne) {
                                min <- qnorm(0.025)*10/sqrt(6) + moyenne
                                max <- qnorm(0.975)*10/sqrt(6) + moyenne
                                return(c(min,max))
                              })

# Q8 :
moyenneReelle <- mean(vitesses$vecVitesses)
horsIntervalle <- 0
for (i in 1:length(moyennes)) {
  if (moyenneReelle < intervalleConfiance[1,i] || intervalleConfiance[2,i] < moyenneReelle)
    horsIntervalle <- horsIntervalle + 1
}
horsIntervalle <- horsIntervalle / length(moyennes)

# Q9 :
nbAff<-40
plot(c(intervalleConfiance[1,1:nbAff],intervalleConfiance[2,1:nbAff]),c(1:nbAff,1:nbAff),pch=4,
     xlab = "intervalles de confiance de la moyenne", ylab="numéro de semaine")
for(i in 1:nbAff){
  segments(intervalleConfiance[1,i],i,intervalleConfiance[2,i],i)
}
abline(v=120,col="red")

# Q10 :
variances <- tapply(X = vitesses$vecVitesses, INDEX = vitesses$vecNum, FUN=var)

# Q11 :
# S² a une loi connue : elle suit une loi du khi-2

# Q12 :
khi2 <- sapply(X = variances,
               function(x) {
                 pchisq(q = x*x, df = 5)
               })

