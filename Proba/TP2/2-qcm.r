# Réponse à la ième question :
# Loi de bernouilli

# Réponse à n questions :
# Loi binomiale

# Probabilité de réussir
psucces <- sum(dbinom(6:10, 
                      size = 10, 
                      prob = 0.25))

nbQuestionOk <- rbinom(5000, size = 10, prob = 0.25)

lesSucces <- nbQuestionOk >= 6

plot(x = 1:length(lesSucces),
     y = cumsum(lesSucces)/1:length(lesSucces),
     type = 'l')

# On peut approximer X10 par une loi normale de paramètres
# mu=E[Xi] et sd=sqrt(p*(1-p)) par le TCL

x10 <- rnorm(length(nbQuestionOk)/10,
             mean = 0.25,
             sd = sqrt(0.25*0.75))
hist(x = x10,
     col = 'yellow',
     freq = F)
