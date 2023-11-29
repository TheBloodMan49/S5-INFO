# Q1 : Loi binomiale, espérence 0.75*n, écart type 0.75*0.25*n

# Q2 : 
nbPersonnes <- qbinom(p = 0.95, prob = 0.75, size = 150)

# Q3 : 
ticketsVendus <- 150
while (nbPersonnes < 150) {
  ticketsVendus <- ticketsVendus + 1
  nbPersonnes <- qbinom(p = 0.95, prob = 0.75, size = ticketsVendus)
}

# Q4 : 
ticketsVendus2 <- 150
nbPersonnes <- 0
while (nbPersonnes < 300) {
  ticketsVendus2 <- ticketsVendus2 + 1
  nbPersonnes <- qbinom(p = 0.95, prob = 0.5, size = ticketsVendus2)
}

ticketsVendus3 <- 150
nbPersonnes <- 0
while (nbPersonnes < 300) {
  ticketsVendus3 <- ticketsVendus3 + 1
  nbPersonnes <- qbinom(p = 0.95, prob = 0.8, size = ticketsVendus3)
}
