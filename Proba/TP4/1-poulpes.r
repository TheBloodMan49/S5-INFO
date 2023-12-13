poids <- c(1150,1500,1700,1800,1800, 1850,
           2200,2700,2900,3000,3100,3500,3900,4000,5400)

mu <- mean(poids)
sigma <- sd(poids)

confianceInf <- mu + qt(p = 0.05, df = 14) * sigma / sqrt(15)
confianceSup <- mu + qt(p = 0.95, df = 14) * sigma / sqrt(15)

# Test de conformité
# H0 : le poids moyen est 3000g
# H1 : le poids moyen n'est pas 3000g
# Test par p-valeur

s <- (mu - 3000) / (sigma / sqrt(15))

test <- t.test(poids, mu = 3000, conf.level = 0.9)
t <- test$statistic
confianceInfTest <- qt(p = 0.05, df = 14)
confianceSupTest <- qt(p = 0.95, df = 14)
# H0 est validé car la valeur est dans l'intervalle de confiance
