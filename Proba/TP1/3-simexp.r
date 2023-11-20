x <- seq(from=0, to=5, by=0.1)

curve(pexp(x,2), from=0, to=5, col="red")
curve(pexp(x,1), add=T, col="blue")
curve(pexp(x,0.5), add=T, col="green")

vals <- rexp(80,2)
curve(dexp(x,2), from=0, to=3, col="blue")
hist(vals,add=T,freq=F)
