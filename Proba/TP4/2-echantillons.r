# Test d'homogénéité
# H0 : les moyennes sont égales (les différences ne sont dues qu'au hasard)
# H1 : les moyennes sont bien distinctes

# Sigma inconnu mais sigma1 = sigma2
n1 <- 12
s1 <- 0.95
mu1 <- 1.5
n2 <- 8
s2 <- 1.35
mu2 <- 2.35

sigChapCarre <- ((n1-1)*s1^2 + (n2-1)*s2^2) / (n1 + n2 - 2)
sigDChapCarre <- sigChapCarre*(1/n1 + 1/n2)
t <- (mu1-mu2) / sqrt(sigDChapCarre)

confInf <- qt(p = 0.025, df = n1+n2-2)
confSup <- qt(p = 0.975, df = n1+n2-2)

# t est dans [confInf, confSup] donc l'hypothèse H0 est retenue.

# On change les effectifs
n1 <- 120
n2 <- 80

sigChapCarre2 <- ((n1-1)*s1^2 + (n2-1)*s2^2) / (n1 + n2 - 2)
sigDChapCarre2 <- sigChapCarre2*(1/n1 + 1/n2)
t2 <- (mu1-mu2) / sqrt(sigDChapCarre2)

confInf2 <- qt(p = 0.025, df = n1+n2-2)
confSup2 <- qt(p = 0.975, df = n1+n2-2)

# H1 est retenu cette fois
