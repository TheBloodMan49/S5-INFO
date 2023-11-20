library(MASS)
# print(michelson$Speed)
ecartType <- sd(michelson$Speed)
moyenne <- mean(michelson$Speed)



plot(x = 1:100,
     y = cumsum(michelson$Speed)/1:100,
     type = 'l')

# On garde la valeur de la vitesse la plus proche de X100

hist(x = michelson$Speed,
     col = 'yellow',
     freq = F)
curve(dnorm(x, mean = moyenne, sd = ecartType),
      from = min(michelson$Speed),
      to = max(michelson$Speed),
      col = 'blue',
      add = T)

expts <- tapply(FUN = mean,
       X = michelson$Speed,
       INDEX = michelson$Expt)

hist(x = expts,
     col = 'red',
     add = T)
curve(dnorm(x, mean = mean(expts), sd = sd(expts)),
      from = min(michelson$Speed),
      to = max(michelson$Speed),
      col = 'green',
      add = T)